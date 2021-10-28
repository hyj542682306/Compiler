package AST;

import util.position;

public class continueStmtNode extends stmtNode {
    public continueStmtNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}