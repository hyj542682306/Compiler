package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

import java.util.ArrayList;

public class lambdaExprNode extends exprNode {
    public ArrayList<varDecStmtNode> paraList = new ArrayList<>();
    public suiteStmtNode suite;
    public exprListNode exprList;

    public lambdaExprNode(position _pos, suiteStmtNode _suite, exprListNode _exprList) {
        super(_pos);
        suite = _suite;
        exprList = _exprList;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}