package AST;

import util.position;

public class whileStmtNode extends stmtNode {
    public exprNode expr;
    public stmtNode stmt;

    public whileStmtNode(position _pos, exprNode _expr, stmtNode _stmt) {
        super(_pos);
        expr = _expr;
        stmt = _stmt;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}