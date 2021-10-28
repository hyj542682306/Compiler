package AST;

import util.position;

public abstract class ASTNode {
    public position pos;

    public ASTNode(position _pos) {
        pos = _pos;
    }

    public abstract void accept(ASTvisitor visitor);
}