package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

public class preExprNode extends exprNode {
    public String op;
    public exprNode expr;

    public preExprNode(position _pos, String _op, exprNode _expr) {
        super(_pos);
        op = _op;
        expr = _expr;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}