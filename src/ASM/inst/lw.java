package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class lw extends Inst {
    public String global;

    public lw(ASMregister _rd, ASMregister _rs1, immediate _imm, String _global) {
        super(_rd, _rs1, null, _imm);
        global = _global;
    }

    @Override
    public String toString() {
        if (global == null)
            return "lw\t" + rd + ", " + imm + "(" + rs1 + ")";
        else
            return "lw\t" + rd + ", " + global;
    }

    @Override
    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
