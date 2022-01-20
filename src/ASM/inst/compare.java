package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class compare extends Inst {
    public String op;

    public compare(String _op, ASMregister _rd, ASMregister _rs1, ASMregister _rs2) {
        super(_rd, _rs1, _rs2, null);
        op = _op;
    }

    @Override
    public String toString() {
        if (op.equals("seqz") || op.equals("snez"))
            return op + "\t" + rd + ", " + rs1;
        else
            return op + "\t" + rd + ", " + rs1 + ", " + rs2;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
