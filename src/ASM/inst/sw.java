package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class sw extends Inst {
    public sw(ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        super(null, _rs1, _rs2, _imm);
    }

    @Override
    public String toString() {
        return "sw\t" + rs2 + ", " + imm + "(" + rs1 + ")";
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
