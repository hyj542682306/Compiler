package optimize;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.inst.*;
import ASM.operand.ASMregister;
import ASM.operand.immediate;
import ASM.operand.physicalRegister;
import ASM.operand.virtualRegister;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class GraphColoring {
    public ASMmodule Module;
    public ASMfunction nowFunction;
    public physicalRegister zero, s0;
    public virtualRegister EX;

    private final int K = 27;

    public HashMap<ASMblock, HashSet<ASMregister>> blockdef = new HashMap<>();
    public HashMap<ASMblock, HashSet<ASMregister>> blockuse = new HashMap<>();
    public HashMap<ASMblock, HashSet<ASMregister>> blocklivein = new HashMap<>();
    public HashMap<ASMblock, HashSet<ASMregister>> blockliveout = new HashMap<>();

    public HashSet<ASMregister> precolored = new HashSet<>();
    public HashSet<ASMregister> initialed = new HashSet<>();
    public HashSet<ASMregister> simplifyWorklist = new HashSet<>();
    public HashSet<ASMregister> freezeWorklist = new HashSet<>();
    public HashSet<ASMregister> spillWorklist = new HashSet<>();
    public HashSet<ASMregister> spilledNodes = new HashSet<>();
    public HashSet<ASMregister> coalescedNodes = new HashSet<>();
    public HashSet<ASMregister> coloredNodes = new HashSet<>();
    public Stack<ASMregister> selectStack = new Stack<>();

    public HashSet<mv> coalescedMoves = new HashSet<>();
    public HashSet<mv> constrainedMoves = new HashSet<>();
    public HashSet<mv> frozenMoves = new HashSet<>();
    public HashSet<mv> worklistMoves = new HashSet<>();
    public HashSet<mv> activeMoves = new HashSet<>();

    public HashSet<Pair<ASMregister, ASMregister>> adjSet = new HashSet<>();
    public HashMap<ASMregister, HashSet<ASMregister>> adjList = new HashMap<>();
    public HashMap<ASMregister, Integer> degree = new HashMap<>();
    public HashMap<ASMregister, HashSet<mv>> moveList = new HashMap<>();
    public HashMap<ASMregister, ASMregister> alias = new HashMap<>();
    public HashMap<ASMregister, Integer> color = new HashMap<>();

    public HashSet<ASMregister> spilled = new HashSet<>();

    public GraphColoring(ASMmodule _module) {
        Module = _module;
        zero = Module.phyRegList.get(0);
        s0 = Module.phyRegList.get(8);
    }

    public void LivenessAnalysis() {
        //get the pred and succ of each block in current function
        for (ASMblock x : nowFunction.blockList)
            nowFunction.blockMap.put(x.name, x);
        for (ASMblock x : nowFunction.blockList) {
            for (Inst y : x.instList) {
                if (y instanceof branch) {
                    x.succ.add(nowFunction.blockMap.get(((branch) y).dest));
                } else if (y instanceof j) {
                    x.succ.add(nowFunction.blockMap.get(((j) y).dest));
                }
            }
        }

        for (ASMblock x : nowFunction.blockList) {
            HashSet<ASMregister> nowdef = new HashSet<>();
            HashSet<ASMregister> nowuse = new HashSet<>();
            for (Inst y : x.instList) {
                y.use.forEach(z -> {
                    if (!nowdef.contains(z)) nowuse.add(z);
                });
                nowdef.addAll(y.def);
            }
            blocklivein.put(x, nowuse);
            blockliveout.put(x, new HashSet<>());
            blockdef.put(x, nowdef);
        }
        while (true) {
            boolean fixed = true;
            for (ASMblock x : nowFunction.blockList) {
                HashSet<ASMregister> in = blocklivein.get(x);
                HashSet<ASMregister> out = blockliveout.get(x);
                int inSize = in.size(), outSize = out.size();
                out.removeAll(blockdef.get(x));
                in.addAll(out);
                x.succ.forEach(y -> {
                    out.addAll(blocklivein.get(y));
                });
                if (inSize != in.size() || outSize != out.size()) fixed = false;
            }
            if (fixed) break;
        }
    }

    public void visitFunction() {
        //initial
        precolored = new HashSet<>();
        initialed = new HashSet<>();
        simplifyWorklist = new HashSet<>();
        freezeWorklist = new HashSet<>();
        spillWorklist = new HashSet<>();
        spilledNodes = new HashSet<>();
        coalescedNodes = new HashSet<>();
        coloredNodes = new HashSet<>();
        selectStack = new Stack<>();
        coalescedMoves = new HashSet<>();
        constrainedMoves = new HashSet<>();
        frozenMoves = new HashSet<>();
        worklistMoves = new HashSet<>();
        activeMoves = new HashSet<>();
        adjSet = new HashSet<>();
        adjList = new HashMap<>();
        degree = new HashMap<>();
        moveList = new HashMap<>();
        alias = new HashMap<>();
        color = new HashMap<>();
        for (ASMblock x : nowFunction.blockList)
            for (Inst y : x.instList) {
                initialed.addAll(y.def);
                initialed.addAll(y.use);
            }
        for (int i = 0; i < 32; ++i) {
            physicalRegister x = Module.phyRegList.get(i);
            precolored.add(x);
            adjList.put(x, new HashSet<>());
            degree.put(x, Integer.MAX_VALUE);
            moveList.put(x, new HashSet<>());
            alias.put(x, null);
            color.put(x, i);
        }
        initialed.removeAll(precolored);
        for (ASMregister x : initialed) {
            adjList.put(x, new HashSet<>());
            degree.put(x, 0);
            moveList.put(x, new HashSet<>());
            alias.put(x, null);
            color.put(x, null);
        }

        LivenessAnalysis();
        Build();
        MakeWorklist();
        while (true) {
            if (!simplifyWorklist.isEmpty()) Simplify();
            else if (!worklistMoves.isEmpty()) Coalesce();
            else if (!freezeWorklist.isEmpty()) Freeze();
            else if (!spillWorklist.isEmpty()) SelectSpill();
            else break;
        }
        AssignColors();
        if (!spilledNodes.isEmpty()) {
            ReWritePrograms();
            visitFunction();
        }
    }

    public void ReWritePrograms() {
        for (ASMblock x : nowFunction.blockList) {
            ListIterator<Inst> it = x.instList.listIterator(0);
            while (it.hasNext()) {
                Inst y = it.next();
                virtualRegister extra = null;
                for (ASMregister z : y.def) {
                    if (spilledNodes.contains(z)) {
                        EX = new virtualRegister("_EX_def", 4);
                        spilled.add(EX);
                        if (!nowFunction.offMap.containsKey(z.name))
                            nowFunction.alloca((virtualRegister) z);
                        int off = -nowFunction.offMap.get(z.name);
                        if (-2048 <= off && off <= 2047) {
                            it.add(new sw(s0, EX, new immediate(off)));
                        } else {
                            virtualRegister EX_li = new virtualRegister("_EX_def_li", 4);
                            spilled.add(EX_li);
                            it.add(new li(EX_li, new immediate(off)));
                            it.add(new arithmetic("add", EX_li, s0, EX_li, null));
                            it.add(new sw(EX_li, EX, new immediate(0)));
                        }
                        extra = EX;
                        y.rd = EX;
                    }
                }
                if (extra != null) {
                    y.def.clear();
                    y.def.add(extra);
                }
                HashMap<ASMregister, ASMregister> regMap = new HashMap<>();
                for (ASMregister z : y.use) {
                    if (spilledNodes.contains(z)) {
                        EX = new virtualRegister("_EX_use", 4);
                        spilled.add(EX);
                        if (!nowFunction.offMap.containsKey(z.name))
                            nowFunction.alloca((virtualRegister) z);
                        int off = -nowFunction.offMap.get(z.name);
                        it.previous();
                        if (-2048 <= off && off <= 2047) {
                            it.add(new lw(EX, s0, new immediate(off), null));
                        } else {
                            virtualRegister EX_li = new virtualRegister("_EX_use_li", 4);
                            spilled.add(EX_li);
                            it.add(new li(EX_li, new immediate(off)));
                            it.add(new arithmetic("add", EX_li, s0, EX_li, null));
                            it.add(new lw(EX, EX_li, new immediate(0), null));
                        }
                        it.next();
                        regMap.put(z, EX);
                    }
                }
                for (Map.Entry<ASMregister, ASMregister> entry : regMap.entrySet()) {
                    if (y.rs1 == entry.getKey())
                        y.rs1 = entry.getValue();
                    if (y.rs2 == entry.getKey())
                        y.rs2 = entry.getValue();
                    y.use.remove(entry.getKey());
                    y.use.add(entry.getValue());
                }
            }
        }
    }

    public void AssignColors() {
        while (!selectStack.isEmpty()) {
            ASMregister n = selectStack.pop();
            ArrayList<physicalRegister> nowCol = new ArrayList<>(Module.icolors);
            for (ASMregister x : adjList.get(n)) {
                HashSet<ASMregister> tmp = new HashSet<>(coloredNodes);
                tmp.addAll(precolored);
                if (tmp.contains(GetAlias(x)))
                    nowCol.remove(Module.phyRegList.get(color.get(GetAlias(x))));
            }
            if (nowCol.isEmpty()) {
                spilledNodes.add(n);
            } else {
                coloredNodes.add(n);
                color.replace(n, Module.phyRegid.get(nowCol.get(0)));
            }
        }
        for (ASMregister x : coalescedNodes)
            color.replace(x, color.get(GetAlias(x)));
    }

    public void SelectSpill() {
        ASMregister nowReg = null;
        int nowd = -1;
        for (ASMregister x : spillWorklist) {
            if ((!spilled.contains(x)) && degree.get(x) > nowd) {
                nowd = degree.get(x);
                nowReg = x;
            }
        }
        spillWorklist.remove(nowReg);
        simplifyWorklist.add(nowReg);
        FreezeMoves(nowReg);
    }

    public void Freeze() {
        ASMregister u = freezeWorklist.iterator().next();
        freezeWorklist.remove(u);
        simplifyWorklist.add(u);
        FreezeMoves(u);
    }

    public void FreezeMoves(ASMregister u) {
        for (mv m : NodeMoves(u)) {
            ASMregister x = m.rd;
            ASMregister y = m.rs1;
            ASMregister v;
            if (GetAlias(y) == GetAlias(u)) v = GetAlias(x);
            else v = GetAlias(y);
            activeMoves.remove(m);
            frozenMoves.add(m);
            if (NodeMoves(v).size() == 0 && degree.get(v) < K) {
                freezeWorklist.remove(v);
                simplifyWorklist.add(v);
            }
        }
    }

    public void Coalesce() {
        mv m = worklistMoves.iterator().next();
        ASMregister x = GetAlias(m.rd);
        ASMregister y = GetAlias(m.rs1);
        ASMregister u, v;
        if (precolored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        worklistMoves.remove(m);
        if (u == v) {
            coalescedMoves.remove(m);
            AddWorklist(u);
        } else if (precolored.contains(v) || adjSet.contains(new Pair<>(u, v)) || u == zero || v == zero) {
            constrainedMoves.add(m);
            AddWorklist(u);
            AddWorklist(v);
        } else {
            boolean flag = true;
            for (ASMregister z : Adjacent(v))
                flag &= OK(z, u);
            HashSet<ASMregister> tmp = new HashSet<>(Adjacent(u));
            tmp.addAll(Adjacent(v));
            if ((precolored.contains(u) && flag) || (!precolored.contains(u)) && Conservative(tmp)) {
                coalescedMoves.add(m);
                Combine(u, v);
                AddWorklist(u);
            } else activeMoves.add(m);
        }
    }

    public void Combine(ASMregister u, ASMregister v) {
        if (freezeWorklist.contains(v)) freezeWorklist.remove(v);
        else spillWorklist.remove(v);
        coalescedNodes.add(v);
        alias.replace(v, u);
        moveList.get(u).addAll(moveList.get(v));
        HashSet<ASMregister> tmp = new HashSet<>();
        tmp.add(v);
        EnableMoves(tmp);
        for (ASMregister x : Adjacent(v)) {
            AddEdge(x, u);
            DecrementDegree(x);
        }
        if (degree.get(u) >= K && freezeWorklist.contains(u)) {
            freezeWorklist.remove(u);
            spillWorklist.add(u);
        }
    }

    public boolean Conservative(HashSet<ASMregister> tmp) {
        int res = 0;
        for (ASMregister x : tmp)
            if (degree.get(x) >= K) res++;
        return res < K;
    }

    public boolean OK(ASMregister x, ASMregister y) {
        return degree.get(x) < K || precolored.contains(x) || adjSet.contains(new Pair<>(x, y));
    }

    public void AddWorklist(ASMregister u) {
        if ((!precolored.contains(u)) && (!MoveRelated(u)) && degree.get(u) < K) {
            freezeWorklist.remove(u);
            simplifyWorklist.add(u);
        }
    }

    public ASMregister GetAlias(ASMregister x) {
        if (coalescedNodes.contains(x)) {
            alias.replace(x, GetAlias(alias.get(x)));
            return alias.get(x);
        } else return x;
    }

    public void Simplify() {
        ASMregister nowReg = simplifyWorklist.iterator().next();
        simplifyWorklist.remove(nowReg);
        selectStack.push(nowReg);
        for (ASMregister x : Adjacent(nowReg)) DecrementDegree(x);
    }

    public void DecrementDegree(ASMregister x) {
        int d = degree.get(x);
        degree.replace(x, d - 1);
        if (d == K) {
            HashSet<ASMregister> regSet = new HashSet<>(Adjacent(x));
            regSet.add(x);
            EnableMoves(regSet);
            if (MoveRelated(x))
                freezeWorklist.add(x);
            else
                simplifyWorklist.add(x);
        }
    }

    public void EnableMoves(HashSet<ASMregister> regSet) {
        for (ASMregister x : regSet)
            for (mv y : NodeMoves(x))
                if (activeMoves.contains(y)) {
                    activeMoves.remove(y);
                    worklistMoves.add(y);
                }
    }

    public HashSet<ASMregister> Adjacent(ASMregister nowReg) {
        HashSet<ASMregister> res = new HashSet<>(adjList.get(nowReg));
        res.removeAll(selectStack);
        res.removeAll(coalescedNodes);
        return res;
    }

    public void MakeWorklist() {
        for (ASMregister x : initialed) {
            if (degree.get(x) >= K)
                spillWorklist.add(x);
            else if (MoveRelated(x))
                freezeWorklist.add(x);
            else
                simplifyWorklist.add(x);
        }
    }

    public boolean MoveRelated(ASMregister x) {
        return NodeMoves(x).size() > 0;
    }

    public HashSet<mv> NodeMoves(ASMregister x) {
        HashSet<mv> res = new HashSet<>(activeMoves);
        res.addAll(worklistMoves);
        res.retainAll(moveList.get(x));
        return res;
    }

    public void Build() {
        for (ASMblock x : nowFunction.blockList) {
            HashSet<ASMregister> live = blockliveout.get(x);
            for (int i = x.instList.size() - 1; i >= 0; --i) {
                Inst y = x.instList.get(i);
                if (y instanceof mv) {
                    live.removeAll(y.use);
                    for (ASMregister z : y.def)
                        moveList.get(z).add((mv) y);
                    for (ASMregister z : y.use)
                        moveList.get(z).add((mv) y);
                    worklistMoves.add((mv) y);
                }
                live.addAll(y.def);
                live.add(zero);
                for (ASMregister z : y.def)
                    for (ASMregister p : live)
                        AddEdge(p, z);
                live.removeAll(y.def);
                live.addAll(y.use);
            }
        }
    }

    public void AddEdge(ASMregister u, ASMregister v) {
        if (u != v && !adjSet.contains(new Pair<>(u, v))) {
            adjSet.add(new Pair<>(u, v));
            adjSet.add(new Pair<>(v, u));
            if (!precolored.contains(u)) {
                adjList.get(u).add(v);
                degree.replace(u, degree.get(u) + 1);
            }
            if (!precolored.contains(v)) {
                adjList.get(v).add(u);
                degree.replace(v, degree.get(v) + 1);
            }
        }
    }

    public void visitModule() {
        for (ASMfunction x : Module.funcList) {
            nowFunction = x;

            visitFunction();

            for (ASMblock y : nowFunction.blockList) {
                LinkedList<Inst> newList = new LinkedList<>();
                for (Inst z : y.instList) {
                    if (z.rs1 instanceof virtualRegister)
                        z.rs1 = Module.phyRegList.get(color.get(z.rs1));
                    if (z.rs2 instanceof virtualRegister)
                        z.rs2 = Module.phyRegList.get(color.get(z.rs2));
                    if (z.rd instanceof virtualRegister)
                        z.rd = Module.phyRegList.get(color.get(z.rd));

                    if (z instanceof mv && z.rd == z.rs1) continue;
                    newList.add(z);
                }
                y.instList = newList;
            }
        }
    }
}
