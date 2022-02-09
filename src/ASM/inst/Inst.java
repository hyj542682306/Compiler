package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.immediate;
import ASM.operand.ASMregister;

import java.util.HashSet;

public abstract class Inst {
    public ASMregister rd, rs1, rs2;
    public immediate imm;
    public HashSet<ASMregister> use = new HashSet<>();
    public HashSet<ASMregister> def = new HashSet<>();

    public Inst(ASMregister _rd, ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        rd = _rd;
        rs1 = _rs1;
        rs2 = _rs2;
        imm = _imm;
        if (rd != null) def.add(rd);
        if (rs1 != null) use.add(rs1);
        if (rs2 != null) use.add(rs2);
    }

    public abstract String toString();

    public abstract void accept(ASMvisitor visitor);
}
