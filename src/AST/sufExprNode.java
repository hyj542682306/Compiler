package AST;

import util.position;

public class sufExprNode extends exprNode {
    public exprNode expr;
    public String op;

    public sufExprNode(position _pos, exprNode _expr, String _op) {
        super(_pos);
        expr = _expr;
        op = _op;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}