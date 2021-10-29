package AST;

import util.position;

public class classExprNode extends exprNode {
    public exprNode name;
    public String id;
    public boolean isFunc=false;

    public classExprNode(position _pos, exprNode _name, String _id) {
        super(_pos);
        name = _name;
        id = _id;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}