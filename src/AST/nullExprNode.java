package AST;

import util.position;

public class nullExprNode extends exprNode {
    public nullExprNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}