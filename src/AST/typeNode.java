package AST;

import util.position;

public class typeNode extends ASTNode {
    public String type;
    public int dim;

    public typeNode(position _pos, String _type, int _dim) {
        super(_pos);
        type = _type;
        dim = _dim;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}