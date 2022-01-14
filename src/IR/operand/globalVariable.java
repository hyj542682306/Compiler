package IR.operand;

import IR.type.IRType;

public class globalVariable extends Operand {
    public String name;

    public globalVariable(String _name, IRType _ty) {
        super(_ty);
        name = _name;
    }

    @Override
    public String toString() {
        return "@" + name;
    }
}
