package AST.stmtNode;

import AST.*;
import AST.defNode.*;
import AST.exprNode.*;
import util.position;

public abstract class stmtNode extends ASTNode {
    public stmtNode(position _pos) {
        super(_pos);
    }
}