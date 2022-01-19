package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class ret extends Inst {
    public ret() {
        super(null, null, null, null);
    }

    @Override
    public String toString() {
        return "ret";
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
