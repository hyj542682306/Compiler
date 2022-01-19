package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class j extends Inst {
    public String dest;

    public j(String _dest) {
        super(null, null, null, null);
        dest = _dest;
    }

    @Override
    public String toString() {
        return "j\t" + dest;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
