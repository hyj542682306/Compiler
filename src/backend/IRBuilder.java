package backend;

import AST.ASTNode;
import IR.*;
import IR.inst.*;
import AST.ASTvisitor;
import AST.defNode.classDefNode;
import AST.defNode.funcDefNode;
import AST.exprNode.*;
import AST.progNode;
import AST.stmtNode.*;
import AST.typeNode;
import IR.operand.*;
import IR.type.*;
import util.scope;
import util.type.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class IRBuilder implements ASTvisitor {
    public module Module;
    public function nowFunction, initFunction;
    public basicblock nowBlock, globalEndBlock, globalIncrBlock; // globalEndBlock: "break", globalIncrBlock: "continue"
    public basicblock initBlock;
    public scope globalScope;
    public classType nowClass = null;
    public HashMap<String, register> regMap;
    public HashMap<String, globalVariable> globalMap;
    public int num = 0, numString = 0, numLabel = 0;
    public boolean Global, classCollector, structFunction;

    public IRBuilder(scope _globalScope, module _Module) {
        globalScope = _globalScope;
        Module = _Module;
        globalMap = new HashMap<>();
        regMap = new HashMap<>();
        initFunction = new function();
        initFunction.funcDefine = new define(new voidType(), "_INIT_");
        initBlock = new basicblock("L0", initFunction);
        structFunction = false;
    }

    @Override
    public void visit(progNode it) {
        //classCollector
        classCollector = true;
        for (ASTNode x : it.List)
            if (x instanceof classDefNode)
                x.accept(this);
        classCollector = false;

        //visit
        Global = true;
        it.List.forEach(x -> x.accept(this));
        initBlock.addInst(new ret(initBlock));
        initFunction.BlockList.add(initBlock);
        Module.FuncList.add(initFunction);
    }

    @Override
    public void visit(typeNode it) {
        //nothing to do
    }

    @Override
    public void visit(funcDefNode it) {
        Type nowType;
        IRType nowIRType;
        if (structFunction)
            nowIRType = new voidType();
        else {
            nowType = globalScope.typeGet(it.type);
            nowIRType = nowType.getIRType();
            for (int i = 1; i <= it.type.dim; ++i)
                nowIRType = new pointerType(nowIRType);
        }


        HashMap<String, register> tmpregMap = regMap;
        regMap.clear();
        num = numLabel = -1;

        nowFunction = new function();
        numLabel++;
        nowBlock = new basicblock("L" + numLabel, nowFunction);
        if (it.id.equals("main")) {
            nowBlock.addInst(new call(nowBlock, null, new voidType(), "_INIT_", new ArrayList<>()));
        }

        String funcId;
        if (nowClass == null) funcId = it.id;
        else funcId = nowClass.name + "_" + it.id;
        define nowdef = new define(nowIRType, funcId);
        if (nowClass != null) {
            nowdef.TypeList.add(new pointerType(nowClass));
            num++;
            register tmpReg = new register(new pointerType(nowClass), Integer.toString(num));
            nowdef.RegList.add(tmpReg);
        }
        if (it.paraList != null) {
            for (varDecStmtNode x : it.paraList) {
                Type tmpType = globalScope.typeGet(x.type);
                IRType tmpIRType = tmpType.getIRType();
                for (int i = 1; i <= it.type.dim; ++i)
                    tmpIRType = new pointerType(tmpIRType);
                nowdef.TypeList.add(tmpIRType);
                num++;
                register tmpReg = new register(tmpIRType, Integer.toString(num));
                nowdef.RegList.add(tmpReg);
            }
        }
        nowFunction.funcDefine = nowdef;

        if (nowClass != null) {
            IRType tmpIRType = new pointerType(nowClass);
            num++;
            register tmpReg = new register(tmpIRType, Integer.toString(num));
            nowBlock.addInst(new alloca(nowBlock, tmpReg, tmpIRType));
            nowBlock.addInst(new store(nowBlock, tmpIRType, new register(tmpIRType, "0"), tmpReg));
        }
        if (it.paraList != null) {
            for (varDecStmtNode x : it.paraList) {
                Type tmpType = globalScope.typeGet(x.type);
                IRType tmpIRType = tmpType.getIRType();
                for (int i = 1; i <= it.type.dim; ++i)
                    tmpIRType = new pointerType(tmpIRType);
                num++;
                register tmpReg = new register(new pointerType(tmpIRType), Integer.toString(num));
                nowBlock.addInst(new alloca(nowBlock, tmpReg, tmpIRType));
                regMap.put(x.id, tmpReg);
                int lasId = num - it.paraList.size();
                if (nowClass != null) lasId--;
                nowBlock.addInst(new store(nowBlock, tmpIRType, new register(tmpIRType, Integer.toString(lasId)), tmpReg));
            }
        }

        boolean tmpGlobal = Global;
        Global = false;
        it.suite.accept(this);
        Global = tmpGlobal;

        //void function can have no return
        if (nowBlock.terminator == null) {
            structFunction = true;
            nowBlock.terminator = new ret(nowBlock);
            structFunction = false;
        }
        nowFunction.BlockList.add(nowBlock);

        Module.FuncList.add(nowFunction);
        regMap = tmpregMap;
    }

    @Override
    public void visit(classDefNode it) {
        //classCollector
        if (classCollector) {
            Type nowType = globalScope.typeMap.get(it.id);
            IRType nowIRType = nowType.getIRType();
            for (varDecStmtNode x : it.varList) {
                Type xType = globalScope.typeGet(x.type);
                IRType xIRType = xType.getIRType();
                for (int i = 1; i <= x.type.dim; ++i)
                    xIRType = new pointerType(xIRType);
                ((classType) nowIRType).typeList.add(xIRType);
                ((classType) nowIRType).nameList.add(x.id);
            }
            Module.GlobalList.add(new global(nowIRType));
            return;
        }

        nowClass = (classType) globalScope.typeMap.get(it.id).getIRType();
        if (it.struct != null) {
            structFunction = true;
            it.struct.accept(this);
            structFunction = false;
        }
        it.funcList.forEach(x -> x.accept(this));
        nowClass = null;
    }

    @Override
    public void visit(varDecStmtNode it) {
        Type nowType = globalScope.typeGet(it.type);
        IRType nowIRType = nowType.getIRType();
        for (int i = 1; i <= it.type.dim; ++i)
            nowIRType = new pointerType(nowIRType);

        if (Global) {
            basicblock tmpBlock = nowBlock;
            nowBlock = initBlock;

            globalVariable nowVar = new globalVariable(it.id, new pointerType(nowIRType));
            //global
            if (nowIRType instanceof pointerType || nowIRType instanceof arrayType || nowIRType instanceof classType)
                Module.GlobalList.add(new global(nowVar, nowIRType, new nullOperand()));
            else if (nowIRType instanceof intType)
                Module.GlobalList.add(new global(nowVar, nowIRType, new intConst(0)));
            globalMap.put(it.id, nowVar);

            if (it.expr != null) {
                it.expr.accept(this);

                //store
                Operand exprReg;
                IRType valueIRType = it.expr.operand.type;
                if (it.expr.operand.loadneed) {
                    IRType newIRType = ((pointerType) valueIRType).type;
                    num++;
                    exprReg = new register(newIRType, Integer.toString(num));
                    nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
                } else exprReg = it.expr.operand;
                nowBlock.addInst(new store(nowBlock, valueIRType, exprReg, nowVar));
            }

            nowBlock = tmpBlock;
        } else {
            num++;
            register nowReg = new register(new pointerType(nowIRType), Integer.toString(num));
            //alloca
            nowBlock.addInst(new alloca(nowBlock, nowReg, nowIRType));
            regMap.put(it.id, nowReg);

            if (it.expr != null) {
                it.expr.accept(this);

                //store
                Operand exprReg;
                IRType valueIRType = it.expr.operand.type;
                if (it.expr.operand.loadneed) {
                    IRType newIRType = ((pointerType) valueIRType).type;
                    num++;
                    exprReg = new register(newIRType, Integer.toString(num));
                    nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
                } else exprReg = it.expr.operand;
                nowBlock.addInst(new store(nowBlock, valueIRType, exprReg, nowReg));
            }
        }
    }

    @Override
    public void visit(varDefStmtNode it) {
        it.varList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(suiteStmtNode it) {
        it.stmtList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ifStmtNode it) {
        it.expr.accept(this);

        if (it.falseStmt != null) {
            numLabel++;
            basicblock TrueBlock = new basicblock("L" + numLabel, nowFunction);
            numLabel++;
            basicblock FalseBlock = new basicblock("L" + numLabel, nowFunction);
            numLabel++;
            basicblock EndBlock = new basicblock("L" + numLabel, nowFunction);

            Operand exprReg;
            IRType valueIRType = it.expr.operand.type;
            if (it.expr.operand.loadneed) {
                IRType newIRType = ((pointerType) valueIRType).type;
                num++;
                exprReg = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
            } else exprReg = it.expr.operand;
            nowBlock.addInst(new br(nowBlock, exprReg, TrueBlock, FalseBlock));
            nowFunction.BlockList.add(nowBlock);

            nowBlock = TrueBlock;
            it.trueStmt.accept(this);
            nowBlock.addInst(new br(nowBlock, EndBlock));
            nowFunction.BlockList.add(nowBlock);

            nowBlock = FalseBlock;
            it.falseStmt.accept(this);
            nowBlock.addInst(new br(nowBlock, EndBlock));
            nowFunction.BlockList.add(nowBlock);

            nowBlock = EndBlock;
        } else {
            numLabel++;
            basicblock TrueBlock = new basicblock("L" + numLabel, nowFunction);
            numLabel++;
            basicblock EndBlock = new basicblock("L" + numLabel, nowFunction);

            Operand exprReg;
            IRType valueIRType = it.expr.operand.type;
            if (it.expr.operand.loadneed) {
                IRType newIRType = ((pointerType) valueIRType).type;
                num++;
                exprReg = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
            } else exprReg = it.expr.operand;
            nowBlock.addInst(new br(nowBlock, exprReg, TrueBlock, EndBlock));
            nowFunction.BlockList.add(nowBlock);

            nowBlock = TrueBlock;
            it.trueStmt.accept(this);
            nowBlock.addInst(new br(nowBlock, EndBlock));
            nowFunction.BlockList.add(nowBlock);

            nowBlock = EndBlock;
        }
    }

    @Override
    public void visit(whileStmtNode it) {
        numLabel++;
        basicblock WhileBlock = new basicblock("L" + numLabel, nowFunction);
        numLabel++;
        basicblock TrueBlock = new basicblock("L" + numLabel, nowFunction);
        numLabel++;
        basicblock FalseBlock = new basicblock("L" + numLabel, nowFunction);

        basicblock tmpEndBlock = globalEndBlock;
        globalEndBlock = FalseBlock;
        basicblock tmpIncrBlock = globalIncrBlock;
        globalIncrBlock = WhileBlock;

        nowBlock.addInst(new br(nowBlock, WhileBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = WhileBlock;
        it.expr.accept(this);
        Operand exprReg;
        IRType valueIRType = it.expr.operand.type;
        if (it.expr.operand.loadneed) {
            IRType newIRType = ((pointerType) valueIRType).type;
            num++;
            exprReg = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
        } else exprReg = it.expr.operand;
        nowBlock.addInst(new br(nowBlock, exprReg, TrueBlock, FalseBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = TrueBlock;
        it.stmt.accept(this);
        nowBlock.addInst(new br(nowBlock, WhileBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = FalseBlock;
        globalEndBlock = tmpEndBlock;
        globalIncrBlock = tmpIncrBlock;
    }

    @Override
    public void visit(forStmtNode it) {
        if (it.init != null) it.init.accept(this);

        numLabel++;
        basicblock CondBlock = new basicblock("L" + numLabel, nowFunction);
        numLabel++;
        basicblock TrueBlock = new basicblock("L" + numLabel, nowFunction);
        numLabel++;
        basicblock IncrBlock = new basicblock("L" + numLabel, nowFunction);
        numLabel++;
        basicblock FalseBlock = new basicblock("L" + numLabel, nowFunction);

        basicblock tmpEndBlock = globalEndBlock;
        globalEndBlock = FalseBlock;
        basicblock tmpIncrBlock = globalIncrBlock;
        globalIncrBlock = IncrBlock;

        nowBlock.addInst(new br(nowBlock, CondBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = CondBlock;
        if (it.cond != null) {
            it.cond.accept(this);

            Operand exprReg;
            IRType valueIRType = it.cond.operand.type;
            if (it.cond.operand.loadneed) {
                IRType newIRType = ((pointerType) valueIRType).type;
                num++;
                exprReg = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.cond.operand));
            } else exprReg = it.cond.operand;
            nowBlock.addInst(new br(nowBlock, exprReg, TrueBlock, FalseBlock));
        } else nowBlock.addInst(new br(nowBlock, new boolConst(true), TrueBlock, FalseBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = TrueBlock;
        it.stmt.accept(this);
        nowBlock.addInst(new br(nowBlock, IncrBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = IncrBlock;
        if (it.incr != null) it.incr.accept(this);
        nowBlock.addInst(new br(nowBlock, CondBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = FalseBlock;
        globalEndBlock = tmpEndBlock;
        globalIncrBlock = tmpIncrBlock;
    }

    @Override
    public void visit(returnStmtNode it) {
        if (it.expr == null) nowBlock.addInst(new ret(nowBlock));
        else {
            it.expr.accept(this);
            Operand exprReg;
            IRType valueIRType = it.expr.operand.type;
            if (it.expr.operand.loadneed) {
                IRType newIRType = ((pointerType) valueIRType).type;
                num++;
                exprReg = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) exprReg, newIRType, it.expr.operand));
            } else exprReg = it.expr.operand;
            nowBlock.addInst(new ret(nowBlock, exprReg.type, exprReg));
        }
    }

    @Override
    public void visit(breakStmtNode it) {
        nowBlock.addInst(new br(nowBlock, globalEndBlock));
    }

    @Override
    public void visit(continueStmtNode it) {
        nowBlock.addInst(new br(nowBlock, globalIncrBlock));
    }

    @Override
    public void visit(pureExprStmtNode it) {
        it.expr.accept(this);
    }

    @Override
    public void visit(emptyStmtNode it) {
        //nothing to do
    }

    @Override
    public void visit(intExprNode it) {
        it.operand = new intConst(it.value);
    }

    @Override
    public void visit(boolExprNode it) {
        it.operand = new boolConst(it.value);
    }

    @Override
    public void visit(stringExprNode it) {
        String res = it.value.substring(1, it.value.length() - 1);
        IRType globalIRType = new arrayType(res.length(), new intType(8));
        numString++;
        globalVariable nowVar = new globalVariable("str" + numString, globalIRType);
        Module.GlobalList.add(new global(nowVar, globalIRType, new stringConst(globalIRType, res)));

        num++;
        register nowReg = new register(new pointerType(new intType(8)), Integer.toString(num));
        nowBlock.addInst(new getelementptr(nowBlock, nowReg, new pointerType(nowVar.type), nowVar));
        it.operand = nowReg;
    }

    @Override
    public void visit(nullExprNode it) {
        it.operand = new nullOperand();
    }

    @Override
    public void visit(thisExprNode it) {
    }

    @Override
    public void visit(idExprNode it) {
        IRType nowIRType = it.type.getIRType();
        if (nowIRType instanceof intType) {
            //idExpr's operand
            it.operand = globalMap.get(it.id);
            if (it.operand == null)
                it.operand = regMap.get(it.id);
            it.operand.loadneed = true;
        } else if (nowIRType instanceof pointerType) {
            it.operand = globalMap.get(it.id);
            if (it.operand == null)
                it.operand = regMap.get(it.id);
            it.operand.loadneed = true;
        } else if (nowIRType instanceof classType) {
            ;
        }
    }

    @Override
    public void visit(lambdaExprNode it) {
        //nothing to do
    }

    @Override
    public void visit(funcExprNode it) {
        String funcId = "";
        IRType nowIRType = it.type.getIRType();
        ArrayList<Operand> paraList = new ArrayList<>();

        //String-Builtin
        if (it.id instanceof classExprNode && ((classExprNode) it.id).name.type.isString()) {
            ((classExprNode) it.id).name.accept(this);
            funcId = "_str_" + ((classExprNode) it.id).id;

            Operand strReg;
            if (((classExprNode) it.id).name.operand.loadneed) {
                IRType newType = ((pointerType) ((classExprNode) it.id).name.operand.type).type;
                num++;
                strReg = new register(newType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) strReg, newType, ((classExprNode) it.id).name.operand));
            } else strReg = ((classExprNode) it.id).name.operand;
            paraList.add(strReg);
        }
        //Array-Builtin - size()
        else if (it.id instanceof classExprNode && ((classExprNode) it.id).name.type instanceof util.type.arrayType) {
            ((classExprNode) it.id).name.accept(this);

            Operand begReg;
            if (((classExprNode) it.id).name.operand.loadneed) {
                IRType newType = ((pointerType) ((classExprNode) it.id).name.operand.type).type;
                num++;
                begReg = new register(newType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) begReg, newType, ((classExprNode) it.id).name.operand));
            } else begReg = ((classExprNode) it.id).name.operand;

            //bitcast
            num++;
            register bitReg = new register(new pointerType(new intType(32)), Integer.toString(num));
            nowBlock.addInst(new bitcast(nowBlock, bitReg, begReg.type, begReg, bitReg.type));

            //move
            num++;
            register moveReg = new register(bitReg.type, Integer.toString(num));
            paraList.add(new intConst(-1));
            nowBlock.addInst(new getelementptr(nowBlock, moveReg, (pointerType) bitReg.type, bitReg, paraList));

            //load
            num++;
            register sizeReg = new register(new intType(32), Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, sizeReg, sizeReg.type, moveReg));

            it.operand = sizeReg;
            return;
        }
        //class
        else if (it.id instanceof classExprNode) {
            ;
        } else if (it.id instanceof idExprNode) {
            funcId = ((idExprNode) it.id).id;
        }

        for (exprNode x : it.exprList.exprList) {
            x.accept(this);

            Operand nowOp;
            IRType exprIRType = x.operand.type;
            if (x.operand.loadneed) {
                IRType newIRType = ((pointerType) exprIRType).type;
                num++;
                nowOp = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) nowOp, newIRType, x.operand));
            } else nowOp = x.operand;
            paraList.add(nowOp);
        }

        if (nowIRType instanceof voidType) {
            nowBlock.addInst(new call(nowBlock, null, nowIRType, funcId, paraList));
        } else {
            num++;
            register nowReg = new register(nowIRType, Integer.toString(num));
            nowBlock.addInst(new call(nowBlock, nowReg, nowIRType, funcId, paraList));

            //funcExpr's operand
            it.operand = nowReg;
        }
    }

    @Override
    public void visit(indexExprNode it) {
        ArrayList<Operand> nowparaList = new ArrayList<>();
        it.bas.accept(this);
        it.off.accept(this);

        Operand offReg;
        IRType offIRType = it.off.operand.type;
        if (it.off.operand.loadneed) {
            IRType newIRType = ((pointerType) offIRType).type;
            num++;
            offReg = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) offReg, newIRType, it.off.operand));
        } else offReg = it.off.operand;

        nowparaList.add(offReg);

        Operand basReg;
        IRType basIRType = it.bas.operand.type, nowIRType = basIRType;
        if (it.bas.operand.loadneed) {
            IRType newIRType = ((pointerType) basIRType).type;
            nowIRType = newIRType;
            num++;
            basReg = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) basReg, newIRType, it.bas.operand));
        } else basReg = it.bas.operand;

        num++;
        register nowReg = new register(nowIRType, Integer.toString(num));
        nowBlock.addInst(new getelementptr(nowBlock, nowReg, (pointerType) nowIRType, basReg, nowparaList));

        it.operand = nowReg;
        it.operand.loadneed = true;
    }

    @Override
    public void visit(classExprNode it) {

    }

    @Override
    public void visit(sufExprNode it) {
        it.expr.accept(this);
        IRType nowIRType;

        Operand BeforeReg;
        IRType BeforeIRType = it.expr.operand.type;
        nowIRType = BeforeIRType;
        if (it.expr.operand.loadneed) {
            IRType newIRType = ((pointerType) BeforeIRType).type;
            nowIRType = newIRType;
            num++;
            BeforeReg = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) BeforeReg, newIRType, it.expr.operand));
        } else BeforeReg = it.expr.operand;

        //binary
        num++;
        register AfterReg = new register(nowIRType, Integer.toString(num));
        if (it.op.equals("++"))
            nowBlock.addInst(new binary(nowBlock, AfterReg, "add", nowIRType, BeforeReg, new intConst(1)));
        else if (it.op.equals("--"))
            nowBlock.addInst(new binary(nowBlock, AfterReg, "add", nowIRType, BeforeReg, new intConst(-1)));

        //store - update the sufExpr's value
        nowBlock.addInst(new store(nowBlock, nowIRType, AfterReg, it.expr.operand));

        //sufExpr's operand
        it.operand = BeforeReg;
    }

    @Override
    public void visit(preExprNode it) {
        it.expr.accept(this);
        IRType nowIRType;

        Operand BeforeReg;
        IRType BeforeIRType = it.expr.operand.type;
        nowIRType = BeforeIRType;
        if (it.expr.operand.loadneed) {
            IRType newIRType = ((pointerType) BeforeIRType).type;
            nowIRType = newIRType;
            num++;
            BeforeReg = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) BeforeReg, newIRType, it.expr.operand));
        } else BeforeReg = it.expr.operand;

        switch (it.op) {
            case "++" -> {
                //binary
                num++;
                register AfterReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, AfterReg, "add", nowIRType, BeforeReg, new intConst(1)));

                //store - update the preExpr's value
                nowBlock.addInst(new store(nowBlock, nowIRType, AfterReg, it.expr.operand));

                //preExpr's operand
                it.operand = AfterReg;
            }
            case "--" -> {
                //binary
                num++;
                register AfterReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, AfterReg, "add", nowIRType, BeforeReg, new intConst(-1)));

                //store - update the preExpr's value
                nowBlock.addInst(new store(nowBlock, nowIRType, AfterReg, it.expr.operand));

                //preExpr's operand
                it.operand = AfterReg;
            }
            case "+" ->
                    //preExpr's operand
                    it.operand = BeforeReg;
            case "-" -> {
                //binary
                num++;
                register AfterReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, AfterReg, "sub", nowIRType, new intConst(0), BeforeReg));

                //preExpr's operand
                it.operand = AfterReg;
            }
            case "!" -> {
                //binary
                num++;
                register AfterReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, AfterReg, "xor", nowIRType, BeforeReg, new boolConst(true)));

                //preExpr's operand
                it.operand = AfterReg;
            }
            case "~" -> {
                //binary
                num++;
                register AfterReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, AfterReg, "xor", nowIRType, BeforeReg, new intConst(-1)));

                //preExpr's operand
                it.operand = AfterReg;
            }
        }
    }

    @Override
    public void visit(newExprNode it) {
        IRType nowIRType = it.type.getIRType();
        int dim = it.typeN.dim;

        //class
        if (it.exprList == null) {
            ;
        }

        //null
        if (it.exprList.size() == 0) {
            it.operand = new nullOperand();
            return;
        }

        //the first dimension
        exprNode x = it.exprList.get(0);
        x.accept(this);
        Operand sizeReg;
        if (x.operand.loadneed) {
            num++;
            sizeReg = new register(new intType(32), Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) sizeReg, new intType(32), x.operand));
        } else sizeReg = x.operand;

        //malloc
        //1. malloc an additional space to record the size
        //2. move the begin pointer

        //mul
        num++;
        register mulReg = new register(new intType(32), Integer.toString(num));
        int singleSize = 0;
        if (dim == 1) {
            if (it.typeN.type.equals("int")) singleSize = 4;
            else if (it.typeN.type.equals("bool")) singleSize = 1;
            else if (it.typeN.type.equals("string")) singleSize = 8;
            else singleSize = 8;
        } else singleSize = 8;
        nowBlock.addInst(new binary(nowBlock, mulReg, "mul", new intType(32), sizeReg, new intConst(singleSize)));

        //sum
        num++;
        register sumReg = new register(new intType(32), Integer.toString(num));
        nowBlock.addInst(new binary(nowBlock, sumReg, "add", new intType(32), mulReg, new intConst(4)));

        //malloc
        num++;
        register callReg = new register(new pointerType(new intType(8)), Integer.toString(num));
        ArrayList<Operand> nowparaList = new ArrayList<>();
        nowparaList.add(sumReg);
        nowBlock.addInst(new call(nowBlock, callReg, new pointerType(new intType(8)), "_f_malloc", nowparaList));

        //bitcast to i32*
        pointerType sizepointerIRType = new pointerType(new intType(32));
        num++;
        register sizepointerReg = new register(sizepointerIRType, Integer.toString(num));
        nowBlock.addInst(new bitcast(nowBlock, sizepointerReg, new pointerType(new intType(8)), callReg, sizepointerIRType));

        //save the size
        nowBlock.addInst(new store(nowBlock, new intType(32), sizeReg, sizepointerReg));

        //move the pointer
        num++;
        register moveReg = new register(sizepointerIRType, Integer.toString(num));
        nowparaList = new ArrayList<>();
        nowparaList.add(new intConst(1));
        nowBlock.addInst(new getelementptr(nowBlock, moveReg, sizepointerIRType, sizepointerReg, nowparaList));

        //bitcase to the trueIRType
        IRType trueIRType = nowIRType;
        for (int j = 0; j < dim; ++j) trueIRType = new pointerType(trueIRType);
        num++;
        register finalReg = new register(trueIRType, Integer.toString(num));
        nowBlock.addInst(new bitcast(nowBlock, finalReg, sizepointerIRType, moveReg, trueIRType));

        it.operand = finalReg;

        //the lower dimension
        if (it.exprList.size() > 1) {
            Operand presizeReg;
            register preReg, incrReg, curReg, newincrReg, condReg, addrReg;
            for (int i = 1; i < it.exprList.size(); ++i) {
                preReg = finalReg;
                presizeReg = sizeReg;
                num++;
                incrReg = new register(new pointerType(new intType(32)), Integer.toString(num));
                nowBlock.addInst(new alloca(nowBlock, incrReg, new intType(32)));
                nowBlock.addInst(new store(nowBlock, new intType(32), new intConst(0), incrReg));

                //create a loop
                ++numLabel;
                basicblock condBlock = new basicblock("L" + numLabel, nowFunction);
                ++numLabel;
                basicblock bodyBlock = new basicblock("L" + numLabel, nowFunction);
                ++numLabel;
                basicblock incrBlock = new basicblock("L" + numLabel, nowFunction);
                ++numLabel;
                basicblock endBlock = new basicblock("L" + numLabel, nowFunction);

                nowBlock.addInst(new br(nowBlock, condBlock));
                nowFunction.BlockList.add(nowBlock);

                //cond
                nowBlock = condBlock;
                ++num;
                curReg = new register(new intType(32), Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, curReg, new intType(32), incrReg));
                ++num;
                condReg = new register(new intType(1), Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, condReg, "slt", new intType(32), curReg, presizeReg));
                nowBlock.addInst(new br(nowBlock, condReg, bodyBlock, endBlock));
                nowFunction.BlockList.add(nowBlock);

                //body
                nowBlock = bodyBlock;
                x = it.exprList.get(i);
                x.accept(this);
                if (x.operand.loadneed) {
                    num++;
                    sizeReg = new register(new intType(32), Integer.toString(num));
                    nowBlock.addInst(new load(nowBlock, (register) sizeReg, new intType(32), x.operand));
                } else sizeReg = x.operand;

                //mul
                num++;
                mulReg = new register(new intType(32), Integer.toString(num));
                singleSize = 0;
                if (dim - i == 1) {
                    if (it.typeN.type.equals("int")) singleSize = 4;
                    else if (it.typeN.type.equals("bool")) singleSize = 1;
                    else if (it.typeN.type.equals("string")) singleSize = 8;
                    else singleSize = 8;
                } else singleSize = 8;
                nowBlock.addInst(new binary(nowBlock, mulReg, "mul", new intType(32), sizeReg, new intConst(singleSize)));

                //sum
                num++;
                sumReg = new register(new intType(32), Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, sumReg, "add", new intType(32), mulReg, new intConst(4)));

                //malloc
                num++;
                callReg = new register(new pointerType(new intType(8)), Integer.toString(num));
                nowparaList = new ArrayList<>();
                nowparaList.add(sumReg);
                nowBlock.addInst(new call(nowBlock, callReg, new pointerType(new intType(8)), "_f_malloc", nowparaList));

                //bitcast to i32*
                sizepointerIRType = new pointerType(new intType(32));
                num++;
                sizepointerReg = new register(sizepointerIRType, Integer.toString(num));
                nowBlock.addInst(new bitcast(nowBlock, sizepointerReg, new pointerType(new intType(8)), callReg, sizepointerIRType));

                //save the size
                nowBlock.addInst(new store(nowBlock, new intType(32), sizeReg, sizepointerReg));

                //move the pointer
                num++;
                moveReg = new register(sizepointerIRType, Integer.toString(num));
                nowparaList = new ArrayList<>();
                nowparaList.add(new intConst(1));
                nowBlock.addInst(new getelementptr(nowBlock, moveReg, sizepointerIRType, sizepointerReg, nowparaList));

                //bitcase to the trueIRType
                trueIRType = nowIRType;
                for (int j = 0; j < dim - i; ++j) trueIRType = new pointerType(trueIRType);
                num++;
                finalReg = new register(trueIRType, Integer.toString(num));
                nowBlock.addInst(new bitcast(nowBlock, finalReg, sizepointerIRType, moveReg, trueIRType));

                //store
                num++;
                addrReg = new register(preReg.type, Integer.toString(num));
                nowparaList = new ArrayList<>();
                nowparaList.add(curReg);
                nowBlock.addInst(new getelementptr(nowBlock, addrReg, (pointerType) preReg.type, preReg, nowparaList));
                nowBlock.addInst(new store(nowBlock, trueIRType, finalReg, addrReg));

                nowBlock.addInst(new br(nowBlock, incrBlock));
                nowFunction.BlockList.add(nowBlock);

                //incr
                nowBlock = incrBlock;
                ++num;
                newincrReg = new register(new intType(32), Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newincrReg, "add", new intType(32), curReg, new intConst(1)));
                nowBlock.addInst(new store(nowBlock, new intType(32), newincrReg, incrReg));
                nowBlock.addInst(new br(nowBlock, condBlock));
                nowFunction.BlockList.add(nowBlock);

                //end
                nowBlock = endBlock;
            }
        }
    }

    @Override
    public void visit(binaryExprNode it) {
        //need shortest path
        if (it.op.equals("||") || it.op.equals("&&")) {
            it.expr1.accept(this);
            IRType nowIRType;

            Operand operand1;
            IRType expr1IRType = it.expr1.operand.type;
            nowIRType = expr1IRType;
            if (it.expr1.operand.loadneed) {
                IRType newIRType = ((pointerType) expr1IRType).type;
                nowIRType = newIRType;
                num++;
                operand1 = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) operand1, newIRType, it.expr1.operand));
            } else operand1 = it.expr1.operand;

            numLabel++;
            basicblock EndBlock = new basicblock("L" + numLabel, nowFunction);
            numLabel++;
            basicblock ShortBlock = new basicblock("L" + numLabel, nowFunction);
            numLabel++;
            basicblock LongBlock = new basicblock("L" + numLabel, nowFunction);

            if (it.op.equals("||")) {
                num++;
                register ctrl = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new alloca(nowBlock, ctrl, nowIRType));
                nowBlock.addInst(new br(nowBlock, operand1, ShortBlock, LongBlock));
                nowFunction.BlockList.add(nowBlock);

                basicblock tmpEndBlock = globalEndBlock;
                globalEndBlock = EndBlock;

                nowBlock = ShortBlock;
                nowBlock.addInst(new store(nowBlock, nowIRType, new boolConst(true), ctrl));
                nowBlock.addInst(new br(nowBlock, EndBlock));
                nowFunction.BlockList.add(nowBlock);

                nowBlock = LongBlock;
                it.expr2.accept(this);
                Operand operand2;
                IRType expr2IRType = it.expr2.operand.type;
                if (it.expr2.operand.loadneed) {
                    IRType newIRType = ((pointerType) expr2IRType).type;
                    num++;
                    operand2 = new register(newIRType, Integer.toString(num));
                    nowBlock.addInst(new load(nowBlock, (register) operand2, newIRType, it.expr2.operand));
                } else operand2 = it.expr2.operand;
                nowBlock.addInst(new store(nowBlock, nowIRType, operand2, ctrl));
                nowBlock.addInst(new br(nowBlock, EndBlock));
                nowFunction.BlockList.add(nowBlock);

                nowBlock = EndBlock;
                it.operand = ctrl;
                it.operand.loadneed = true;

                globalEndBlock = tmpEndBlock;
            } else {
                num++;
                register ctrl = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new alloca(nowBlock, ctrl, nowIRType));
                nowBlock.addInst(new br(nowBlock, operand1, LongBlock, ShortBlock));
                nowFunction.BlockList.add(nowBlock);

                basicblock tmpEndBlock = globalEndBlock;
                globalEndBlock = EndBlock;

                nowBlock = ShortBlock;
                nowBlock.addInst(new store(nowBlock, nowIRType, new boolConst(false), ctrl));
                nowBlock.addInst(new br(nowBlock, EndBlock));
                nowFunction.BlockList.add(nowBlock);

                nowBlock = LongBlock;
                it.expr2.accept(this);
                Operand operand2;
                if (it.expr2.operand.loadneed) {
                    num++;
                    operand2 = new register(nowIRType, Integer.toString(num));
                    nowBlock.addInst(new load(nowBlock, (register) operand2, nowIRType, it.expr2.operand));
                } else operand2 = it.expr2.operand;
                nowBlock.addInst(new store(nowBlock, nowIRType, operand2, ctrl));
                nowBlock.addInst(new br(nowBlock, EndBlock));
                nowFunction.BlockList.add(nowBlock);

                nowBlock = EndBlock;
                it.operand = ctrl;
                it.operand.loadneed = true;

                globalEndBlock = tmpEndBlock;
            }
            return;
        }

        //assignment
        if (it.op.equals("=")) {
            it.expr1.accept(this);
            it.expr2.accept(this);

            IRType nowIRType;
            Operand operand2;
            IRType expr2IRType = it.expr2.operand.type;
            nowIRType = expr2IRType;
            if (it.expr2.operand.loadneed) {
                IRType newIRType = ((pointerType) expr2IRType).type;
                nowIRType = newIRType;
                num++;
                operand2 = new register(newIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) operand2, newIRType, it.expr2.operand));
            } else operand2 = it.expr2.operand;

            nowBlock.addInst(new store(nowBlock, nowIRType, operand2, it.expr1.operand));
            it.operand = it.expr1.operand;
            return;
        }

        //normal
        it.expr1.accept(this);
        it.expr2.accept(this);

        IRType nowIRType;
        Operand operand1;
        IRType expr1IRType = it.expr1.operand.type;
        nowIRType = expr1IRType;
        if (it.expr1.operand.loadneed) {
            IRType newIRType = ((pointerType) expr1IRType).type;
            nowIRType = newIRType;
            num++;
            operand1 = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) operand1, newIRType, it.expr1.operand));
        } else operand1 = it.expr1.operand;
        Operand operand2;
        IRType expr2IRType = it.expr2.operand.type;
        if (it.expr2.operand.loadneed) {
            IRType newIRType = ((pointerType) expr2IRType).type;
            num++;
            operand2 = new register(newIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) operand2, newIRType, it.expr2.operand));
        } else operand2 = it.expr2.operand;

        //const and const
        if (operand1 instanceof intConst && operand2 instanceof intConst) {
            switch (it.op) {
                case "+" -> it.operand = new intConst(((intConst) operand1).val + ((intConst) operand2).val);
                case "-" -> it.operand = new intConst(((intConst) operand1).val - ((intConst) operand2).val);
                case "*" -> it.operand = new intConst(((intConst) operand1).val * ((intConst) operand2).val);
                case "/" -> it.operand = new intConst(((intConst) operand1).val / ((intConst) operand2).val);
                case "%" -> it.operand = new intConst(((intConst) operand1).val % ((intConst) operand2).val);
                case "<<" -> it.operand = new intConst(((intConst) operand1).val << ((intConst) operand2).val);
                case ">>" -> it.operand = new intConst(((intConst) operand1).val >> ((intConst) operand2).val);
                case "<" -> it.operand = new boolConst(((intConst) operand1).val < ((intConst) operand2).val);
                case "<=" -> it.operand = new boolConst(((intConst) operand1).val <= ((intConst) operand2).val);
                case ">" -> it.operand = new boolConst(((intConst) operand1).val > ((intConst) operand2).val);
                case ">=" -> it.operand = new boolConst(((intConst) operand1).val >= ((intConst) operand2).val);
                case "!=" -> it.operand = new boolConst(((intConst) operand1).val != ((intConst) operand2).val);
                case "==" -> it.operand = new boolConst(((intConst) operand1).val == ((intConst) operand2).val);
                case "^" -> it.operand = new intConst(((intConst) operand1).val ^ ((intConst) operand2).val);
                case "|" -> it.operand = new intConst(((intConst) operand1).val | ((intConst) operand2).val);
                case "&" -> it.operand = new intConst(((intConst) operand1).val & ((intConst) operand2).val);
            }
            return;
        } else if (operand1 instanceof boolConst && operand2 instanceof boolConst) {
            switch (it.op) {
                case "||" -> it.operand = new boolConst(((boolConst) operand1).val || ((boolConst) operand2).val);
                case "&&" -> it.operand = new boolConst(((boolConst) operand1).val && ((boolConst) operand2).val);
            }
            return;
        }

        //String
        if (it.expr1.type.isString()) {
            switch (it.op) {
                case "+" -> {
                    ++num;
                    register newReg = new register(nowIRType, Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, nowIRType, "_str_concatenate", nowparaList));

                    it.operand = newReg;
                }
                case "<" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_lt", nowparaList));

                    it.operand = newReg;
                }
                case "<=" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_le", nowparaList));

                    it.operand = newReg;
                }
                case ">" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_gt", nowparaList));

                    it.operand = newReg;
                }
                case ">=" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_ge", nowparaList));

                    it.operand = newReg;
                }
                case "==" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_eq", nowparaList));

                    it.operand = newReg;
                }
                case "!=" -> {
                    ++num;
                    register newReg = new register(new intType(1), Integer.toString(num));
                    ArrayList<Operand> nowparaList = new ArrayList<>();
                    nowparaList.add(operand1);
                    nowparaList.add(operand2);
                    nowBlock.addInst(new call(nowBlock, newReg, new intType(1), "_str_ne", nowparaList));

                    it.operand = newReg;
                }
            }
            return;
        }

        //normal
        switch (it.op) {
            case "+" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "add", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "-" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "sub", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "*" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "mul", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "/" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "sdiv", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "%" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "srem", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "<<" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "shl", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case ">>" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "ashr", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "<" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "slt", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "<=" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "sle", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case ">" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "sgt", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case ">=" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "sge", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "!=" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "ne", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "==" -> {
                //icmp
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new icmp(nowBlock, newReg, "eq", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "^" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "xor", nowIRType, operand1, operand2));
            }
            case "|" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "or", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
            case "&" -> {
                //binary
                ++num;
                register newReg = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new binary(nowBlock, newReg, "and", nowIRType, operand1, operand2));

                //binaryExpr's operand
                it.operand = newReg;
            }
        }
    }

    @Override
    public void visit(exprListNode it) {
        it.exprList.forEach(x -> x.accept(this));
    }
}
