package AST;

import util.position;

public class boolExprNode extends exprNode {
    public boolean value;

    public boolExprNode(position _pos, boolean _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}