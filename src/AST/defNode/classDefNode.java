package AST.defNode;

import AST.*;
import AST.exprNode.*;
import AST.stmtNode.*;
import util.position;

import java.util.ArrayList;

public class classDefNode extends defNode {
    public String id;
    public ArrayList<varDecStmtNode> varList = new ArrayList<>();
    public ArrayList<funcDefNode> funcList = new ArrayList<>();
    public funcDefNode struct = null;

    public classDefNode(position _pos, String _id) {
        super(_pos);
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}