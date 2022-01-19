package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class branch extends Inst {
    public String op, dest;

    public branch(String _op, ASMregister _rs1, String _dest) {
        super(null, _rs1, null, null);
        op = _op;
        dest = _dest;
    }

    @Override
    public String toString() {
        return op + "\t" + rs1 + ",\t" + dest;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
