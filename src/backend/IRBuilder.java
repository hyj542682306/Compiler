package backend;

import AST.ASTvisitor;
import AST.defNode.classDefNode;
import AST.defNode.funcDefNode;
import AST.exprNode.*;
import AST.progNode;
import AST.stmtNode.*;
import AST.typeNode;

public class IRBuilder implements ASTvisitor {

    public IRBuilder(){}

    @Override
    public void visit(progNode it) {
        //visit
        it.List.forEach(x->x.accept(this));
    }

    @Override
    public void visit(typeNode it) {

    }

    @Override
    public void visit(funcDefNode it) {

    }

    @Override
    public void visit(classDefNode it) {

    }

    @Override
    public void visit(varDecStmtNode it) {

    }

    @Override
    public void visit(varDefStmtNode it) {
        it.varList.forEach(x->x.accept(this));
    }

    @Override
    public void visit(suiteStmtNode it) {

    }

    @Override
    public void visit(ifStmtNode it) {

    }

    @Override
    public void visit(whileStmtNode it) {

    }

    @Override
    public void visit(forStmtNode it) {

    }

    @Override
    public void visit(returnStmtNode it) {

    }

    @Override
    public void visit(breakStmtNode it) {

    }

    @Override
    public void visit(continueStmtNode it) {

    }

    @Override
    public void visit(pureExprStmtNode it) {

    }

    @Override
    public void visit(emptyStmtNode it) {

    }

    @Override
    public void visit(intExprNode it) {

    }

    @Override
    public void visit(boolExprNode it) {

    }

    @Override
    public void visit(stringExprNode it) {

    }

    @Override
    public void visit(nullExprNode it) {

    }

    @Override
    public void visit(thisExprNode it) {

    }

    @Override
    public void visit(idExprNode it) {

    }

    @Override
    public void visit(lambdaExprNode it) {

    }

    @Override
    public void visit(funcExprNode it) {

    }

    @Override
    public void visit(indexExprNode it) {

    }

    @Override
    public void visit(classExprNode it) {

    }

    @Override
    public void visit(sufExprNode it) {

    }

    @Override
    public void visit(preExprNode it) {

    }

    @Override
    public void visit(newExprNode it) {

    }

    @Override
    public void visit(binaryExprNode it) {

    }

    @Override
    public void visit(exprListNode it) {

    }
}
