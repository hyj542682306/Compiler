package frontend;

import AST.*;
import util.*;
import util.error.semanticError;

public class SymbolCollector implements ASTvisitor {
    public scope globalScope, nowScope;

    public SymbolCollector(scope _globalScope) {
        globalScope = _globalScope;

        globalScope.typeMap.put("int", new literalType("int"));
        globalScope.typeMap.put("bool", new literalType("bool"));
        globalScope.typeMap.put("string", new literalType("string"));
        globalScope.typeMap.put("null", new literalType("null"));
        globalScope.typeMap.put("void", new literalType("void"));

        funcSymbol functmp1 = new funcSymbol("print");
        functmp1.type = new literalType("void");
        functmp1.paraList.add(new varSymbol(new literalType("string"), "str"));
        globalScope.funcMap.put("print", functmp1);

        funcSymbol functmp2 = new funcSymbol("println");
        functmp2.type = new literalType("void");
        functmp2.paraList.add(new varSymbol(new literalType("string"), "str"));
        globalScope.funcMap.put("println", functmp2);

        funcSymbol functmp3 = new funcSymbol("printInt");
        functmp3.type = new literalType("void");
        functmp3.paraList.add(new varSymbol(new literalType("int"), "n"));
        globalScope.funcMap.put("printInt", functmp3);

        funcSymbol functmp4 = new funcSymbol("printlnInt");
        functmp4.type = new literalType("void");
        functmp4.paraList.add(new varSymbol(new literalType("int"), "n"));
        globalScope.funcMap.put("printlnInt", functmp4);

        funcSymbol functmp5 = new funcSymbol("getString");
        functmp5.type = new literalType("string");
        globalScope.funcMap.put("getString", functmp5);

        funcSymbol functmp6 = new funcSymbol("getInt");
        functmp6.type = new literalType("int");
        globalScope.funcMap.put("getInt", functmp6);

        funcSymbol functmp7 = new funcSymbol("toString");
        functmp7.type = new literalType("string");
        functmp7.paraList.add(new varSymbol(new literalType("int"), "i"));
        globalScope.funcMap.put("toString", functmp7);
    }

    @Override
    public void visit(progNode it) {
        nowScope = globalScope;
        it.List.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(typeNode it) {

    }

    @Override
    public void visit(funcDefNode it) {
        funcSymbol tmp = new funcSymbol(it.id);
        it.paraList.forEach(x -> tmp.paraList.add(new varSymbol(nowScope.typeGet(x.type.type, x.pos), x.id)));
        nowScope.newFunc(it.id, tmp, it.pos);
    }

    @Override
    public void visit(classDefNode it) {
        nowScope = new scope(nowScope);
        classType res = new classType(it.id);
        it.varList.forEach(x -> x.accept(this));
        it.funcList.forEach(x -> x.accept(this));
        if (it.struct != null) res.struct = new funcSymbol(it.id);
        res.varMap = nowScope.varMap;
        res.funcMap = nowScope.funcMap;
        nowScope = nowScope.pScope;
        nowScope.newType(it.id, res, it.pos);
    }

    @Override
    public void visit(varDecStmtNode it) {
        varSymbol tmp = new varSymbol(nowScope.typeGet(it.type.type, it.pos), it.id);
        nowScope.newVar(it.id, tmp, it.pos);
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
