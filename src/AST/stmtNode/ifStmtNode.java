package AST.stmtNode;

import AST.*;
import AST.defNode.*;
import AST.exprNode.*;
import util.position;

public class ifStmtNode extends stmtNode {
    public exprNode expr;
    public stmtNode trueStmt, falseStmt;

    public ifStmtNode(position _pos, exprNode _expr, stmtNode _trueStmt, stmtNode _falseStmt) {
        super(_pos);
        expr = _expr;
        trueStmt = _trueStmt;
        falseStmt = _falseStmt;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}