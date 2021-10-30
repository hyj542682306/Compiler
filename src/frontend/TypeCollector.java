package frontend;

import AST.*;
import AST.defNode.*;
import AST.exprNode.*;
import AST.stmtNode.*;
import util.*;
import util.type.*;

public class TypeCollector implements ASTvisitor {
    public scope globalScope;

    public String nowClass = null;

    public TypeCollector(scope _globalScope) {
        globalScope = _globalScope;
    }

    @Override
    public void visit(progNode it) {
        it.List.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(typeNode it) {

    }

    @Override
    public void visit(funcDefNode it) {
        funcSymbol tmp;
        if (nowClass == null) tmp = globalScope.funcMap.get(it.id);
        else tmp = ((classType) globalScope.typeMap.get(nowClass)).funcMap.get(it.id);
        tmp.type = globalScope.typeGet(it.type);
        it.paraList.forEach(x ->
                tmp.paraList.add(new varSymbol(globalScope.typeGet(x.type), x.id)));
    }

    @Override
    public void visit(classDefNode it) {
        nowClass = it.id;
        it.varList.forEach(x -> x.accept(this));
        it.funcList.forEach(x -> x.accept(this));
        nowClass = null;
    }

    @Override
    public void visit(varDecStmtNode it) {
        varSymbol tmp;
        if(nowClass==null)tmp=globalScope.varMap.get(it.id);
        else tmp=((classType)globalScope.typeMap.get(nowClass)).varMap.get(it.id);
        tmp.type=globalScope.typeGet(it.type);
    }

    @Override
    public void visit(varDefStmtNode it) {

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
