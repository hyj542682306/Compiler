package AST.exprNode;

import AST.*;
import AST.defNode.*;
import AST.stmtNode.*;
import IR.operand.*;
import util.position;
import util.type.*;

public abstract class exprNode extends ASTNode {
    public Type type;
    public boolean assign = false;
    public Operand operand; //IR 'Type' will have the same name

    public exprNode(position _pos) {
        super(_pos);
    }

    public exprNode(position _pos, boolean _assign) {
        super(_pos);
        assign = _assign;
    }

    public exprNode(position _pos, Type _type, boolean _assign) {
        super(_pos);
        type = _type;
        assign = _assign;
    }
}