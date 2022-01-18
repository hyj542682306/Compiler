package IR.operand;

import IR.type.*;

public class boolConst extends Operand {
    public boolean val;

    public boolConst(boolean _val) {
        super(new intType(1));
        val = _val;
    }

    @Override
    public String toString() {
        return val ? "true" : "false";
    }
}
