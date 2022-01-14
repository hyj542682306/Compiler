package IR.operand;

import IR.type.*;

public class register extends Operand {
    public String name;

    public register(IRType _type, String _name) {
        super(_type);
        name = _name;
    }

    @Override
    public String toString() {
        return "%R" + name;
    }
}
