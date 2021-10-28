package AST;

import util.position;

import java.util.ArrayList;

public class varDefStmtNode extends stmtNode {
    public ArrayList<varDecStmtNode> varList = new ArrayList<>();

    public varDefStmtNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}