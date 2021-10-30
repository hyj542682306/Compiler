package AST.defNode;

import AST.*;
import AST.exprNode.*;
import AST.stmtNode.*;
import util.position;

public abstract class defNode extends ASTNode {
    public defNode(position _pos) {
        super(_pos);
    }
}