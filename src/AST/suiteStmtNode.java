package AST;

import util.position;

import java.util.ArrayList;

public class suiteStmtNode extends stmtNode {
    public ArrayList<stmtNode> stmtList = new ArrayList<>();

    public suiteStmtNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}