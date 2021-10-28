package AST;

import util.position;

public class forStmtNode extends stmtNode {
    public exprNode init, cond, incr;
    public stmtNode stmt;

    public forStmtNode(position _pos, exprNode _init, exprNode _cond, exprNode _incr, stmtNode _stmt) {
        super(_pos);
        init = _init;
        cond = _cond;
        incr = _incr;
        stmt = _stmt;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}