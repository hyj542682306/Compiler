package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class li extends Inst {
    public li(ASMregister _rd, immediate _imm) {
        super(_rd, null, null, _imm);
    }

    @Override
    public String toString() {
        return "li\t" + rd + ", " + imm;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
