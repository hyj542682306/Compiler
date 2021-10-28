package AST;

import util.position;

public class stringExprNode extends exprNode {
    public String value;

    public stringExprNode(position _pos, String _value) {
        super(_pos);
        value = _value;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}