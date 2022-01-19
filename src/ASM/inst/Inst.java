package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.immediate;
import ASM.operand.ASMregister;

public abstract class Inst {
    public ASMregister rd, rs1, rs2;
    public immediate imm;

    public Inst(ASMregister _rd, ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        rd = _rd;
        rs1 = _rs1;
        rs2 = _rs2;
        imm = _imm;
    }

    public abstract String toString();

    public abstract void accept(ASMvisitor visitor);
}
