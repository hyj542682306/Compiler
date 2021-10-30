package AST;

import util.position;

public class indexExprNode extends exprNode {
    public exprNode bas;
    public exprNode off;

    public indexExprNode(position _pos, exprNode _bas, exprNode _off) {
        super(_pos,true);
        bas = _bas;
        off = _off;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}