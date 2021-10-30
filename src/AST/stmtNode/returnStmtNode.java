package AST.stmtNode;

import AST.*;
import AST.defNode.*;
import AST.exprNode.*;
import util.position;

public class returnStmtNode extends stmtNode {
    public exprNode expr;

    public returnStmtNode(position _pos, exprNode _expr) {
        super(_pos);
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}