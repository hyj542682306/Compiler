package AST;

import util.position;

public class idExprNode extends exprNode {
    public String id;
    public typeNode type;

    public idExprNode(position _pos, String _id) {
        super(_pos);
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}