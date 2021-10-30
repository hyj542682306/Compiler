package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

public class binaryExprNode extends exprNode {
    public exprNode expr1, expr2;
    public String op;

    public binaryExprNode(position _pos, exprNode _expr1, exprNode _expr2, String _op) {
        super(_pos);
        expr1 = _expr1;
        expr2 = _expr2;
        op = _op;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}