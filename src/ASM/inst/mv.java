package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class mv extends Inst {
    public mv(ASMregister _rd, ASMregister _rs1) {
        super(_rd, _rs1, null, null);
    }

    @Override
    public String toString() {
        return "mv\t" + rd + ", " + rs1;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
