package AST;

import util.position;
import util.Type;

public abstract class exprNode extends ASTNode {
    public Type type;
    public boolean assign = false;

    public exprNode(position _pos) {
        super(_pos);
    }

    public exprNode(position _pos, boolean _assign) {
        super(_pos);
        assign=_assign;
    }

    public exprNode(position _pos, Type _type, boolean _assign) {
        super(_pos);
        type = _type;
        assign = _assign;
    }
}