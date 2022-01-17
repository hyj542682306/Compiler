package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

public class idExprNode extends exprNode {
    public String id;

    public idExprNode(position _pos, String _id) {
        super(_pos, true);
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}