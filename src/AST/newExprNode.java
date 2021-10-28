package AST;

import util.position;

import java.util.ArrayList;

public class newExprNode extends exprNode {
    public typeNode type;
    public ArrayList<exprNode> exprList = new ArrayList<>();

    public newExprNode(position _pos, typeNode _type, int _dim, ArrayList<exprNode> _exprList) {
        super(_pos);
        type = _type;
        type.dim = _dim;
        exprList = _exprList;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}