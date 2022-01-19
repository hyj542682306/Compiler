package backend;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.inst.*;
import ASM.operand.*;
import IR.IRvisitor;
import IR.basicblock;
import IR.function;
import IR.inst.*;
import IR.module;
import IR.operand.*;
import IR.type.classType;

import java.util.ArrayList;

public class ASMBuilder implements IRvisitor {
    public ASMmodule Module;
    public ASMfunction nowFunction;
    public ASMblock nowBlock;
    public int extraNum = 0;

    public ASMBuilder(ASMmodule _Module) {
        Module = _Module;
    }

    //virtual register
    public ASMregister getReg(Operand operand){
        virtualRegister res = null;
        if (operand instanceof register){
            res = new virtualRegister(((register) operand).name,4);
        }
        else {
            extraNum++;
            res = new virtualRegister("_EX_" + extraNum, 4);

            //extra register
            int value = 0;
            if (operand instanceof intConst) value = ((intConst) operand).val;
            else if (operand instanceof boolConst) value = ((boolConst) operand).val ? 1 : 0;
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
        it.funcDefine.accept(this);
        it.BlockList.forEach(x -> x.accept(this));
        Module.funcList.add(nowFunction);
    }

    @Override
    public void visit(basicblock it) {
        nowBlock = new ASMblock("."+nowFunction.name+"_"+it.name);
        it.InstList.forEach(x -> x.accept(this));
        it.terminator.accept(this);
        nowFunction.blockList.add(nowBlock);
    }

    @Override
    public void visit(alloca it) {

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
        virtualRegister rd = new virtualRegister(it.result.name, 4);
        if (it.op1 instanceof register && it.op2 instanceof register) {
            ASMregister rs1 = getReg(it.op1);
            ASMregister rs2 = getReg(it.op2);
            nowBlock.addInst(new arithmetic(arithop, rd, rs1, rs2, null));
        } else if (it.op2 instanceof register) {
            ASMregister rs1 = getReg(it.op2);
            int value = 0;
            if (it.op1 instanceof intConst) value = ((intConst) it.op1).val;
            else if (it.op1 instanceof boolConst) value = ((boolConst) it.op1).val ? 1 : 0;
            immediate imm = new immediate(value);
            nowBlock.addInst(new arithmetic(arithop, rd, rs1, null, imm));
        } else {
            ASMregister rs1 = getReg(it.op1);
            int value=0;
            if (it.op2 instanceof intConst) value = ((intConst) it.op2).val;
            else if (it.op2 instanceof boolConst) value = ((boolConst) it.op2).val ? 1 : 0;
            immediate imm = new immediate(value);
            nowBlock.addInst(new arithmetic(arithop, rd, rs1, null, imm));
        }
    }

    @Override
    public void visit(br it) {
        //br label -> j label
        if (it.cond == null) {
            nowBlock.addInst(new j("."+it.iftrue.infunction.funcDefine.name+"_"+it.iftrue.name));
        }
        //br REG label1, label2 -> beqz REG label2
        //                         j label1
        else {
            ASMregister rs1 = getReg(it.cond);
            nowBlock.addInst(new branch("beqz", rs1, "."+it.iffalse.infunction.funcDefine.name+"_"+it.iffalse.name));
            nowBlock.addInst(new j("."+it.iftrue.infunction.funcDefine.name+"_"+it.iftrue.name));
        }
    }

    @Override
    public void visit(define it) {

    }

    @Override
    public void visit(global it) {
        if (it.ty instanceof classType) return ;
        if (it.value instanceof stringConst) {
            Module.globalList.add(new string(it.var.name, ((stringConst) it.value).str));
        } else {
            Module.globalList.add(new globalRegister(it.var.name, 4, 0));
        }
    }

    @Override
    public void visit(icmp it) {

    }

    @Override
    public void visit(load it) {

    }

    @Override
    public void visit(IR.inst.ret it) {

    }

    @Override
    public void visit(store it) {

    }

    @Override
    public void visit(IR.inst.call it) {

    }

    @Override
    public void visit(bitcast it) {

    }

    @Override
    public void visit(getelementptr it) {

    }
}
