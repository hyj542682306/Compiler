package AST;

import util.position;

public class intExprNode extends exprNode {
    public int value;

    public intExprNode(position _pos, int _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}