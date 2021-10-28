package AST;

import util.position;

public class thisExprNode extends exprNode {
    public thisExprNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}