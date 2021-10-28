package AST;

import util.position;

public class emptyStmtNode extends stmtNode {
    public emptyStmtNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}