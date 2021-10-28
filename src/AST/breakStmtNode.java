package AST;

import util.position;

public class breakStmtNode extends stmtNode {
    public breakStmtNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}