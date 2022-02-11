package optimize;

import IR.IRvisitor;
import IR.basicblock;
import IR.function;
import IR.inst.*;
import IR.module;
import IR.operand.Operand;
import IR.operand.register;
import IR.operand.temporary;
import IR.type.IRType;
import IR.type.pointerType;

import java.util.ArrayList;
import java.util.HashMap;

public class MemToReg implements IRvisitor {
    public module Module;
    public function nowFunction;
    public int tmpcnt = 0;

    public HashMap<String, temporary> allocaMap;
    public HashMap<String, temporary> loadMap;

    public Operand translate(Operand x) {
        if (x instanceof register) {
            if (allocaMap.containsKey(((register) x).name)) return allocaMap.get(((register) x).name);
            if (loadMap.containsKey(((register) x).name)) return loadMap.get(((register) x).name);
        } else if (x instanceof temporary) {
            if (allocaMap.containsKey(((temporary) x).name)) return allocaMap.get(((temporary) x).name);
            if (loadMap.containsKey(((temporary) x).name)) return loadMap.get(((temporary) x).name);
        }
        return x;
    }

    public MemToReg(module _Module) {
        Module = _Module;
    }

    @Override
    public void visit(module it) {
        it.FuncList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(function it) {
        nowFunction = it;
        allocaMap = new HashMap<>();
        loadMap = new HashMap<>();

        //alloca
        for (Inst x : it.AllocaBlock.AllocaList) {
            tmpcnt++;
            allocaMap.put(x.result.name, new temporary((((alloca) x).ty), "T" + tmpcnt));
        }
        it.AllocaBlock.AllocaList.clear();
        it.AllocaBlock.terminator.accept(this);

        //others
        it.BlockList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(basicblock it) {
        ArrayList<Inst> newList = new ArrayList<>();
        for (Inst x : it.InstList) {
            if (x instanceof load && ((load) x).pointer instanceof register && allocaMap.containsKey(((register) ((load) x).pointer).name)) {
                temporary srcTmp = allocaMap.get(((register) ((load) x).pointer).name);
                IRType newType = srcTmp.type;
                tmpcnt++;
                temporary restmp = new temporary(newType, "T" + tmpcnt);
                loadMap.put(x.result.name, restmp);
                newList.add(new store(it, newType, srcTmp, restmp));
            } else {
                x.accept(this);
                newList.add(x);
            }
        }
        it.terminator.accept(this);
        it.InstList = newList;
    }

    @Override
    public void visit(alloca it) {
        //nothing to do
    }

    @Override
    public void visit(binary it) {
        it.op1 = translate(it.op1);
        it.op2 = translate(it.op2);
    }

    @Override
    public void visit(br it) {
        it.cond = translate(it.cond);
    }

    @Override
    public void visit(define it) {
        //nothing to do
    }

    @Override
    public void visit(global it) {
        //nothing to do
    }

    @Override
    public void visit(icmp it) {
        it.op1 = translate(it.op1);
        it.op2 = translate(it.op2);
    }

    @Override
    public void visit(load it) {
        //nothing to do
    }

    @Override
    public void visit(ret it) {
        it.value = translate(it.value);
    }

    @Override
    public void visit(store it) {
        it.value = translate(it.value);
        it.pointer = translate(it.pointer);
    }

    @Override
    public void visit(call it) {
        if (it.paraList != null) {
            for (int i = 0; i < it.paraList.size(); ++i)
                it.paraList.set(i, translate(it.paraList.get(i)));
        }
    }

    @Override
    public void visit(bitcast it) {
        it.value = translate(it.value);
    }

    @Override
    public void visit(getelementptr it) {
        it.value = translate(it.value);
        if (it.IdxList != null) {
            for (int i = 0; i < it.IdxList.size(); ++i)
                it.IdxList.set(i, translate(it.IdxList.get(i)));
        }
    }
}
