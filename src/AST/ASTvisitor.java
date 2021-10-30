package AST;

import AST.defNode.*;
import AST.stmtNode.*;
import AST.exprNode.*;
import AST.progNode;
import AST.typeNode;

public interface ASTvisitor {
    void visit(progNode it);

    void visit(typeNode it);

    void visit(funcDefNode it);

    void visit(classDefNode it);

    void visit(varDecStmtNode it);

    void visit(varDefStmtNode it);

    void visit(suiteStmtNode it);

    void visit(ifStmtNode it);

    void visit(whileStmtNode it);

    void visit(forStmtNode it);

    void visit(returnStmtNode it);

    void visit(breakStmtNode it);

    void visit(continueStmtNode it);

    void visit(pureExprStmtNode it);

    void visit(emptyStmtNode it);

    void visit(intExprNode it);

    void visit(boolExprNode it);

    void visit(stringExprNode it);

    void visit(nullExprNode it);

    void visit(thisExprNode it);

    void visit(idExprNode it);

    void visit(lambdaExprNode it);

    void visit(funcExprNode it);

    void visit(indexExprNode it);

    void visit(classExprNode it);

    void visit(sufExprNode it);

    void visit(preExprNode it);

    void visit(newExprNode it);

    void visit(binaryExprNode it);

    void visit(exprListNode it);
}