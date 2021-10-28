package AST;

import util.position;

public class funcExprNode extends exprNode {
    public exprNode id;
    public exprListNode exprList;

    public funcExprNode(position _pos, exprNode _id, exprListNode _exprList) {
        super(_pos);
        id = _id;
        exprList = _exprList;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}