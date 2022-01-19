package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class call extends Inst {
    public String funcId;

    public call(String _funcId) {
        super(null, null, null, null);
        funcId = _funcId;
    }

    @Override
    public String toString() {
        return "call\t" + funcId;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
