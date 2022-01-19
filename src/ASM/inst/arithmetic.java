package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.immediate;
import ASM.operand.ASMregister;

public class arithmetic extends Inst {
    public String op;
    public boolean ImmArith = false;

    public arithmetic(String _op, ASMregister _rd, ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        super(_rd, _rs1, _rs2, _imm);
        op = _op;
        if (_imm != null) ImmArith = true;
    }

    @Override
    public String toString() {
        if (ImmArith)
            return op + "\t" + rd + ", " + rs1 + ", " + imm;
        else
            return op + "i\t" + rd + ", " + rs1 + ", " + rs2;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
