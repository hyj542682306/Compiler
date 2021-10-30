package AST.stmtNode;

import AST.*;
import AST.defNode.*;
import AST.exprNode.*;
import util.position;

public class varDecStmtNode extends stmtNode {
    public typeNode type;
    public String id;
    public exprNode expr;

    public varDecStmtNode(position _pos, String _id, exprNode _expr) {
        super(_pos);
        id = _id;
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}