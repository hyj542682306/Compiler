package IR.operand;

import IR.type.IRType;

public class temporary extends Operand {
    public String name;

    public temporary(IRType _type, String _name) {
        super(_type);
        name = _name;
    }

    @Override
    public String toString() {
        return "%" + name;
    }
}
