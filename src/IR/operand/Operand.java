package IR.operand;

import IR.type.*;

public abstract class Operand {
    public IRType type;
    public boolean loadneed = false;

    public Operand() {
    }

    public Operand(IRType _type) {
        type = _type;
    }

    public abstract String toString();
}
