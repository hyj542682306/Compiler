package IR.operand;

import IR.type.*;

public class intConst extends Operand {
    public int val;

    public intConst(int _val) {
        super(new intType(32));
        val = _val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
