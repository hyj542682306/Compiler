package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class la extends Inst {
    public String addr;

    public la(ASMregister _rd, String _addr) {
        super(_rd, null, null, null);
        addr = _addr;
    }

    @Override
    public String toString() {
        return "la\t" + rd + ", " + addr;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
