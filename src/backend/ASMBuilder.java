package backend;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.inst.*;
import ASM.inst.Inst;
import ASM.inst.call;
import ASM.operand.*;
import IR.IRvisitor;
import IR.basicblock;
import IR.function;
import IR.inst.*;
import IR.module;
import IR.operand.*;
import IR.type.classType;
import IR.type.voidType;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMBuilder implements IRvisitor {
    public ASMmodule Module;
    public ASMfunction nowFunction;
    public ASMblock nowBlock;
    public physicalRegister zero, ra, sp, s0, a0;
    public HashMap<String, ASMregister> regMap;
    public ArrayList<virtualRegister> calleeList;
    int extraNum = -1;

    public ASMBuilder(ASMmodule _Module) {
        Module = _Module;
        zero = _Module.phyRegList.get(0);
        ra = _Module.phyRegList.get(1);
        sp = _Module.phyRegList.get(2);
        s0 = _Module.phyRegList.get(8);
        a0 = _Module.phyRegList.get(10);
        regMap = new HashMap<>();
        calleeList = new ArrayList<>();
    }

    //virtual register
    public ASMregister getReg(Operand operand) {
        virtualRegister res = null;
        if (operand instanceof register) {
            if (regMap.containsKey(nowFunction.name + "_" + ((register) operand).name))
                return regMap.get(nowFunction.name + "_" + ((register) operand).name);
            else {
                res = new virtualRegister(((register) operand).name, 4);
                regMap.put(nowFunction.name + "_" + ((register) operand).name, res);
            }
        } else if (operand instanceof temporary) {
            if (regMap.containsKey(nowFunction.name + "_" + ((temporary) operand).name))
                return regMap.get(nowFunction.name + "_" + ((temporary) operand).name);
            else {
                res = new virtualRegister(((temporary) operand).name, 4);
                regMap.put(nowFunction.name + "_" + ((temporary) operand).name, res);
            }
        } else if (operand instanceof globalVariable) {
            extraNum++;
            res = new virtualRegister("_EX_" + extraNum, 4);
            nowBlock.addInst(new la(res, ((globalVariable) operand).name));
            return res;
        } else {
            extraNum++;
            res = new virtualRegister("_EX_" + extraNum, 4);

            int value = 0;
            if (operand instanceof intConst) value = ((intConst) operand).val;
            else if (operand instanceof boolConst) value = ((boolConst) operand).val ? 1 : 0;
            else if (operand instanceof nullOperand) value = 0;

            if (value == 0) return zero;

            immediate imm = new immediate(value);
            nowBlock.addInst(new li(res, imm));
        }
        return res;
    }

    @Override
    public void visit(module it) {
        it.GlobalList.forEach(x -> x.accept(this));
        it.FuncList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(function it) {
        nowFunction = new ASMfunction(it.funcDefine.name);
        nowBlock = new ASMblock("." + it.funcDefine.name + "_INIT");
        it.funcDefine.accept(this);
        nowBlock.addInst(new j("." + it.funcDefine.name + "_L0"));
        nowFunction.blockList.add(nowBlock);
        it.AllocaBlock.AllocaList.forEach(x -> x.accept(this));
        it.BlockList.forEach(x -> x.accept(this));
        nowBlock = new ASMblock("." + it.funcDefine.name + "_RETURN");
        nowFunction.blockList.add(nowBlock);
        Module.funcList.add(nowFunction);
    }

    @Override
    public void visit(basicblock it) {
        nowBlock = new ASMblock("." + nowFunction.name + "_" + it.name);
        it.InstList.forEach(x -> x.accept(this));
        it.terminator.accept(this);
        nowFunction.blockList.add(nowBlock);
    }

    @Override
    public void visit(alloca it) {
        virtualRegister alcReg = new virtualRegister(it.result.name, 4);
        regMap.put(nowFunction.name + "_" + it.result.name, alcReg);
        nowFunction.alloca(alcReg);
    }

    @Override
    public void visit(binary it) {
        String arithop = switch (it.inst) {
            case "sdiv" -> "div";
            case "srem" -> "rem";
            case "shl" -> "sll";
            case "ashr" -> "sra";
            default -> it.inst;
        };
        ASMregister rd = getReg(it.result);
        ASMregister rs1 = getReg(it.op1);
        ASMregister rs2 = getReg(it.op2);
        nowBlock.addInst(new arithmetic(arithop, rd, rs1, rs2, null));
    }

    @Override
    public void visit(br it) {
        //br label -> j label
        if (it.cond == null) {
            nowBlock.addInst(new j("." + it.iftrue.infunction.funcDefine.name + "_" + it.iftrue.name));
        }
        //br REG label1, label2 -> beqz REG label2
        //                         j label1
        else {
            ASMregister rs1 = getReg(it.cond);
            nowBlock.addInst(new branch("beqz", rs1, "." + it.iffalse.infunction.funcDefine.name + "_" + it.iffalse.name));
            nowBlock.addInst(new j("." + it.iftrue.infunction.funcDefine.name + "_" + it.iftrue.name));
        }
    }

    @Override
    public void visit(define it) {
        //deal with the callee
        calleeList.clear();
        for (int i = 0; i < Module.callee.size(); ++i) {
            physicalRegister x = Module.callee.get(i);
            ++extraNum;
            virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
            calleeList.add(EX);
            nowBlock.addInst(new mv(EX, x));
        }

        //deal with the paraList
        for (int i = 0; i < Integer.min(it.RegList.size(), 8); ++i) {
            ASMregister tmpReg = getReg(it.RegList.get(i));
            nowBlock.addInst(new mv(tmpReg, Module.phyRegList.get(10 + i)));
        }
        for (int i = 8; i < it.RegList.size(); ++i) {
            ASMregister tmpReg = getReg(it.RegList.get(i));
            nowBlock.addInst(new lw(tmpReg, s0, new immediate((i - 8) * 4), null));
        }
    }

    @Override
    public void visit(global it) {
        if (it.ty instanceof classType) return;
        if (it.value instanceof stringConst) {
            Module.globalList.add(new string(it.var.name, ((stringConst) it.value).str));
        } else {
            Module.globalList.add(new globalRegister(it.var.name, 4, 0));
        }
    }

    @Override
    public void visit(icmp it) {
        ASMregister rd = getReg(it.result), rs1 = getReg(it.op1), rs2 = getReg(it.op2);

        switch (it.cond) {
            case "eq" -> {
                ++extraNum;
                virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
                nowBlock.addInst(new arithmetic("xor", EX, rs1, rs2, null));
                nowBlock.addInst(new compare("seqz", rd, EX, null));
            }
            case "ne" -> {
                ++extraNum;
                virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
                nowBlock.addInst(new arithmetic("xor", EX, rs1, rs2, null));
                nowBlock.addInst(new compare("snez", rd, EX, null));
            }
            case "slt" -> nowBlock.addInst(new compare("slt", rd, rs1, rs2));
            case "sle" -> {
                nowBlock.addInst(new compare("sgt", rd, rs1, rs2));
                nowBlock.addInst(new arithmetic("xor", rd, rd, null, new immediate(1)));
            }
            case "sgt" -> nowBlock.addInst(new compare("sgt", rd, rs1, rs2));
            case "sge" -> {
                nowBlock.addInst(new compare("slt", rd, rs1, rs2));
                nowBlock.addInst(new arithmetic("xor", rd, rd, null, new immediate(1)));
            }
        }
    }

    @Override
    public void visit(load it) {
        virtualRegister rd = new virtualRegister(it.result.name, 4);
        regMap.put(nowFunction.name + "_" + it.result.name, rd);
        if (it.pointer instanceof globalVariable) {
            nowBlock.addInst(new lw(rd, null, null, ((globalVariable) it.pointer).name));
        } else {
            ASMregister rs1 = getReg(it.pointer);
            if (nowFunction.offMap.containsKey(rs1.name)) {
                int offset = -nowFunction.offMap.get(rs1.name);
                if (-2048 <= offset && offset <= 2047)
                    nowBlock.addInst(new lw(rd, s0, new immediate(offset), null));
                else {
                    ++extraNum;
                    virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
                    nowBlock.addInst(new li(EX, new immediate(offset)));
                    nowBlock.addInst(new arithmetic("add", EX, EX, s0, null));
                    nowBlock.addInst(new sw(EX, rd, new immediate(0)));
                }
            } else
                nowBlock.addInst(new lw(rd, rs1, new immediate(0), null));
        }
    }

    @Override
    public void visit(IR.inst.ret it) {
        //deal with the callee
        for (int i = 0; i < Module.callee.size(); ++i) {
            physicalRegister x = Module.callee.get(i);
            virtualRegister EX = calleeList.get(i);
            nowBlock.addInst(new mv(x, EX));
        }

        if (it.ty instanceof voidType) nowBlock.addInst(new mv(a0, zero));
        else nowBlock.addInst(new mv(a0, getReg(it.value)));
        nowBlock.addInst(new j("." + nowFunction.name + "_" + "RETURN"));
    }

    @Override
    public void visit(store it) {
        if (it.pointer instanceof globalVariable) {
            ++extraNum;
            virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
            nowBlock.addInst(new la(EX, ((globalVariable) it.pointer).name));
            nowBlock.addInst(new sw(EX, getReg(it.value), new immediate(0)));
        } else {
            if (it.pointer instanceof temporary) {
                nowBlock.addInst(new mv(getReg(it.pointer),getReg(it.value)));
            } else {
                ASMregister rs1 = getReg(it.pointer), rs2 = getReg(it.value);
                if (nowFunction.offMap.containsKey(rs1.name)) {
                    int offset = -nowFunction.offMap.get(rs1.name);
                    if (-2048 <= offset && offset <= 2047)
                        nowBlock.addInst(new sw(s0, rs2, new immediate(offset)));
                    else {
                        ++extraNum;
                        virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
                        nowBlock.addInst(new li(EX, new immediate(offset)));
                        nowBlock.addInst(new arithmetic("add", EX, EX, s0, null));
                        nowBlock.addInst(new sw(EX, rs1, new immediate(0)));
                    }
                } else
                    nowBlock.addInst(new sw(rs1, rs2, new immediate(0)));
            }
        }
    }

    @Override
    public void visit(IR.inst.call it) {
        int spilloff = 0;
        //deal with the paraList
        for (int i = 0; i < Integer.min(it.paraList.size(), 8); ++i) {
            ASMregister tmpReg = getReg(it.paraList.get(i));
            nowBlock.addInst(new mv(Module.phyRegList.get(10 + i), tmpReg));
        }
        for (int i = 8; i < it.paraList.size(); ++i) {
            ASMregister tmpReg = getReg(it.paraList.get(i));
            spilloff += 4;
            nowBlock.addInst(new sw(sp, tmpReg, new immediate((i - 8) * 4)));
        }

        nowFunction.calloff = Math.max(nowFunction.calloff, spilloff);
        //call
        Inst res = new call(it.funcId);
        res.def.addAll(Module.caller);
        nowBlock.addInst(res);

        //deal with the return values
        if (!(it.ty instanceof voidType)) {
            ASMregister returnReg = getReg(it.result);
            nowBlock.addInst(new mv(returnReg, a0));
        }
    }

    @Override
    public void visit(bitcast it) {
        regMap.put(nowFunction.name + "_" + it.result.name, getReg(it.value));
    }

    @Override
    public void visit(getelementptr it) {
        if (it.value instanceof globalVariable)
            regMap.put(nowFunction.name + "_" + it.result.name, getReg(it.value));
        else {
            ASMregister rd = getReg(it.result);

            //class
            if (it.IdxList.size() > 1) {
                int off = 4 * ((intConst) it.IdxList.get(1)).val;
                nowBlock.addInst(new arithmetic("add", rd, getReg(it.value), null, new immediate(off)));
            }
            //array
            else {
                ++extraNum;
                virtualRegister EX = new virtualRegister("_EX_" + extraNum, 4);
                nowBlock.addInst(new li(EX, new immediate(4)));
                nowBlock.addInst(new arithmetic("mul", EX, getReg(it.IdxList.get(0)), EX, null));
                nowBlock.addInst(new arithmetic("add", rd, getReg(it.value), EX, null));
            }
        }
    }
}
