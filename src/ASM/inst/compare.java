package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class compare extends Inst {
    public String op;
    public boolean ImmCmp = false;

    public compare(String _op, ASMregister _rd, ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        super(_rd, _rs1, _rs2, _imm);
        op = _op;
        if (imm != null) ImmCmp = true;
    }

    @Override
    public String toString() {
        if (op.equals("seqz") || op.equals("snez"))
            return op + "\t" + rd + ", " + rs1;
        else if (ImmCmp)
            return op + "\t" + rd + ", " + rs1 + ", " + imm;
        else
            return op + "\t" + rd + ", " + rs1 + ", " + rs2;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
