package ASM.inst;

import ASM.ASMvisitor;
import ASM.operand.ASMregister;
import ASM.operand.immediate;

public class lw extends Inst {
    public lw(ASMregister _rd, ASMregister _rs1, ASMregister _rs2, immediate _imm) {
        super(_rd, _rs1, _rs2, _imm);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void accept(ASMvisitor visitor) {

    }
}
