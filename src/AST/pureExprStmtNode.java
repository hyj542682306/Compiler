package AST;

import util.position;

public class pureExprStmtNode extends stmtNode {
    public exprNode expr;

    public pureExprStmtNode(position _pos, exprNode _expr) {
        super(_pos);
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}