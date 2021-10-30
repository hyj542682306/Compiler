package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

import java.util.ArrayList;

public class exprListNode extends exprNode {
    public ArrayList<exprNode> exprList = new ArrayList<>();

    public exprListNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}