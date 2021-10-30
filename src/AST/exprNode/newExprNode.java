package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import util.position;

import java.util.ArrayList;

public class newExprNode extends exprNode {
    public typeNode typeN;
    public ArrayList<exprNode> exprList = new ArrayList<>();

    public newExprNode(position _pos, typeNode _typeN, int _dim, ArrayList<exprNode> _exprList) {
        super(_pos);
        typeN = _typeN;
        typeN.dim = _dim;
        exprList = _exprList;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}