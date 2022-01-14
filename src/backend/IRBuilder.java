package backend;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class IRBuilder implements ASTvisitor {
    public module Module;
    public function nowFunction;
    public basicblock nowBlock, globalEndBlock, globalIncrBlock; // globalEndBlock: "break", globalIncrBlock: "continue"
    public scope globalScope;
    public classType nowClass = null;
    public ArrayList<Operand> IdxList;
    public IRType IndexFinalType=null;
    public HashMap<String, register> regMap;
    public HashMap<String, globalVariable> globalMap;
    public int num = 0, numString = 0, numLabel = 0;
    public boolean Global;

    public IRBuilder(scope _globalScope, module _Module) {
        globalScope = _globalScope;
        Module = _Module;
        globalMap = new HashMap<>();
        regMap = new HashMap<>();
    }

    @Override
    public void visit(progNode it) {
        //visit
        Global = true;
        it.List.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(typeNode it) {
        //nothing to do
    }

    @Override
    public void visit(funcDefNode it) {
        Type nowType = globalScope.typeGet(it.type);
        IRType nowIRType = nowType.getIRType();

        HashMap<String, register> tmpregMap = regMap;
        regMap.clear();
        num = numLabel = -1;

        nowFunction = new function();
        numLabel++;
        nowBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);

        String funcId;
        if (nowClass==null)funcId=it.id;
        else funcId=nowClass.name+"_"+it.id;
        define nowdef = new define(nowIRType, funcId);
        for (varDecStmtNode x : it.paraList) {
            Type tmpType = globalScope.typeGet(x.type);
            IRType tmpIRType = tmpType.getIRType();
            nowdef.TypeList.add(tmpIRType);
            num++;
            register tmpReg = new register(tmpIRType, Integer.toString(num));
            nowdef.RegList.add(tmpReg);
        }
        nowFunction.funcDefine = nowdef;

        for (varDecStmtNode x : it.paraList) {
            Type tmpType = globalScope.typeGet(x.type);
            IRType tmpIRType = tmpType.getIRType();
            num++;
            register tmpReg = new register(tmpIRType, Integer.toString(num));
            nowBlock.addInst(new alloca(nowBlock, tmpReg, tmpIRType));
            regMap.put(x.id, tmpReg);
            nowBlock.addInst(new store(nowBlock, tmpIRType, new register(tmpIRType, Integer.toString(num - it.paraList.size())), tmpReg));
        }

        boolean tmpGlobal = Global;
        Global = false;
        it.suite.accept(this);
        Global = tmpGlobal;

        //void function can have no return
        if (nowBlock.terminator == null) {
            nowBlock.terminator = new ret(nowBlock);
        }
        nowFunction.BlockList.add(nowBlock);

        Module.FuncList.add(nowFunction);
        regMap = tmpregMap;
    }

    @Override
    public void visit(classDefNode it) {

    }

    @Override
    public void visit(varDecStmtNode it) {
        Type nowType = globalScope.typeGet(it.type);
        IRType nowIRType = nowType.getIRType();
        for (int i = 1; i <= it.type.dim; ++i)
            nowIRType = new pointerType(nowIRType);
        if (Global) {
            globalVariable nowVar = new globalVariable(it.id, nowIRType);
            //global
            if (it.expr == null) {
                if (nowIRType instanceof pointerType || nowIRType instanceof arrayType || nowIRType instanceof classType)
                    Module.GlobalList.add(new global(nowVar, nowIRType, new nullOperand()));
                else if (nowIRType instanceof intType)
                    Module.GlobalList.add(new global(nowVar, nowIRType, new intConst(0)));
            } else {
                it.expr.accept(this);
                Module.GlobalList.add(new global(nowVar, nowIRType, it.expr.operand));
            }
            globalMap.put(it.id, nowVar);
        } else {
            num++;
            register nowReg = new register(nowIRType, Integer.toString(num));
            //alloca
            nowBlock.addInst(new alloca(nowBlock, nowReg, nowIRType));
            regMap.put(it.id, nowReg);

            //store - put the result of expr in the current register
            if (it.expr != null) {
                it.expr.accept(this);

                //store
                nowBlock.addInst(new store(nowBlock, nowIRType, it.expr.operand, nowReg));
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
            basicblock TrueBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            numLabel++;
            basicblock FalseBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            numLabel++;
            basicblock EndBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            nowBlock.addInst(new br(nowBlock, it.expr.operand, TrueBlock, FalseBlock));
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
            basicblock TrueBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            numLabel++;
            basicblock EndBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            nowBlock.addInst(new br(nowBlock, it.expr.operand, TrueBlock, EndBlock));
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
        basicblock WhileBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
        numLabel++;
        basicblock TrueBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
        numLabel++;
        basicblock FalseBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);

        basicblock tmpEndBlock = globalEndBlock;
        globalEndBlock = FalseBlock;
        basicblock tmpIncrBlock = globalIncrBlock;
        globalIncrBlock = WhileBlock;

        nowBlock.addInst(new br(nowBlock, WhileBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = WhileBlock;
        it.expr.accept(this);
        nowBlock.addInst(new br(nowBlock, it.expr.operand, TrueBlock, FalseBlock));
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
        basicblock CondBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
        numLabel++;
        basicblock TrueBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
        numLabel++;
        basicblock IncrBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
        numLabel++;
        basicblock FalseBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);

        basicblock tmpEndBlock = globalEndBlock;
        globalEndBlock = FalseBlock;
        basicblock tmpIncrBlock = globalIncrBlock;
        globalIncrBlock = IncrBlock;

        nowBlock.addInst(new br(nowBlock, CondBlock));
        nowFunction.BlockList.add(nowBlock);

        nowBlock = CondBlock;
        if (it.cond != null) {
            it.cond.accept(this);
            nowBlock.addInst(new br(nowBlock, it.cond.operand, TrueBlock, FalseBlock));
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
            nowBlock.addInst(new ret(nowBlock, it.expr.operand.type, it.expr.operand));
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
        IRType nowIRType = new pointerType(new intType(8));
        String res = it.value.substring(1, it.value.length() - 1);
        numString++;
        Module.GlobalList.add(new global(new globalVariable("str" + Integer.toString(numString), nowIRType), nowIRType, new stringConst(nowIRType, res)));
        if (Global)
            it.operand = new stringConst(nowIRType, it.value.substring(1, it.value.length() - 1));
        else
            it.operand = new globalVariable("str" + Integer.toString(numString), nowIRType);
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
            if (it.operand != null) return;
            it.operand = regMap.get(it.id);
            it.operand.loadneed = true;
        } else if (nowIRType instanceof pointerType) {
            ;
        } else if (nowIRType instanceof arrayType) {
            ;
        } else if (nowIRType instanceof classType) {
            ;
        }
    }

    @Override
    public void visit(lambdaExprNode it) {
        //nothing to do
    }

    public String GetFuncName(exprNode it) {
        String res = "";
        //function()
        if (it instanceof idExprNode) res = ((idExprNode) it).id;
            //class.function()
        else if (it instanceof classExprNode)
            res = ((classType) ((classExprNode) it).name.type.getIRType()).name +"_"+ ((classExprNode) it).id;
        return res;
    }

    @Override
    public void visit(funcExprNode it) {
        String funcId = GetFuncName(it.id);
        IRType nowIRType = it.type.getIRType();
        ArrayList<Operand> paraList = new ArrayList<>();

        for (exprNode x : it.exprList.exprList) {
            x.accept(this);
            Operand nowOp;
            if (x.operand.loadneed) {
                num++;
                nowOp = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) nowOp, nowIRType, x.operand));
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
        if (it.bas instanceof indexExprNode){
            it.off.accept(this);

            IRType offIRType=it.off.operand.type;
            Operand offReg;
            if (it.off.operand.loadneed) {
                num++;
                offReg = new register(offIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) offReg, offIRType, it.off.operand));
            } else offReg = it.off.operand;
            IdxList.add(offReg);

            if (IndexFinalType==null)
                IndexFinalType=it.type.getIRType();
            it.bas.accept(this);

            it.operand=it.bas.operand;
        }
        else {
            it.bas.accept(this);
            it.off.accept(this);

            IRType offIRType=it.off.operand.type;
            Operand offReg;
            if (it.off.operand.loadneed) {
                num++;
                offReg = new register(offIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) offReg, offIRType, it.off.operand));
            } else offReg = it.off.operand;
            IdxList.add(offReg);
            Collections.reverse(IdxList);

            IRType basIRType=it.bas.operand.type;
            Operand basReg;
            if (it.bas.operand.loadneed) {
                num++;
                basReg = new register(basIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) basReg, basIRType, it.bas.operand));
            } else basReg = it.bas.operand;

            num++;
            register nowReg=new register(IndexFinalType,Integer.toString(num));
            nowBlock.addInst(new getelementptr(nowBlock,nowReg,basIRType,basReg,IdxList));

            it.operand=nowReg;

            IndexFinalType=null;
            IdxList=new ArrayList<>();
        }
    }

    @Override
    public void visit(classExprNode it) {

    }

    @Override
    public void visit(sufExprNode it) {
        it.expr.accept(this);

        IRType nowIRType = it.expr.operand.type;
        Operand BeforeReg;
        if (it.expr.operand.loadneed) {
            num++;
            BeforeReg = new register(nowIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) BeforeReg, nowIRType, it.expr.operand));
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

        IRType nowIRType = it.expr.operand.type;
        Operand BeforeReg;
        if (it.expr.operand.loadneed) {
            num++;
            BeforeReg = new register(nowIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) BeforeReg, nowIRType, it.expr.operand));
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

    }

    @Override
    public void visit(binaryExprNode it) {
        //need shortest path
        if (it.op.equals("||") || it.op.equals("&&")) {
            it.expr1.accept(this);

            IRType nowIRType = it.operand.type;
            Operand operand1;
            if (it.expr1.operand.loadneed) {
                num++;
                operand1 = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) operand1, nowIRType, it.expr1.operand));
            } else operand1 = it.expr1.operand;

            numLabel++;
            basicblock EndBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            numLabel++;
            basicblock ShortBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);
            numLabel++;
            basicblock LongBlock = new basicblock("L" + Integer.toString(numLabel), nowFunction);

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

            IRType nowIRType = it.expr1.operand.type;
            Operand operand2;
            if (it.expr2.operand.loadneed) {
                num++;
                operand2 = new register(nowIRType, Integer.toString(num));
                nowBlock.addInst(new load(nowBlock, (register) operand2, nowIRType, it.expr2.operand));
            } else operand2 = it.expr2.operand;

            nowBlock.addInst(new store(nowBlock, nowIRType, operand2, it.expr1.operand));
            it.operand = it.expr1.operand;
            return;
        }

        //normal
        it.expr1.accept(this);
        it.expr2.accept(this);

        IRType nowIRType = it.expr1.operand.type;
        Operand operand1;
        if (it.expr1.operand.loadneed) {
            num++;
            operand1 = new register(nowIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) operand1, nowIRType, it.expr1.operand));
        } else operand1 = it.expr1.operand;
        Operand operand2;
        if (it.expr2.operand.loadneed) {
            num++;
            operand2 = new register(nowIRType, Integer.toString(num));
            nowBlock.addInst(new load(nowBlock, (register) operand2, nowIRType, it.expr2.operand));
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
        } else if (operand1 instanceof stringConst && operand2 instanceof stringConst) {
            switch (it.op) {
                case "+" -> it.operand = new stringConst(operand1.type, ((stringConst) operand1).str + ((stringConst) operand2).str);
                case "<" -> it.operand = new boolConst(((stringConst) operand1).str.compareTo(((stringConst) operand2).str) < 0);
                case ">" -> it.operand = new boolConst(((stringConst) operand1).str.compareTo(((stringConst) operand2).str) > 0);
                case "<=" -> it.operand = new boolConst(((stringConst) operand1).str.compareTo(((stringConst) operand2).str) <= 0);
                case ">=" -> it.operand = new boolConst(((stringConst) operand1).str.compareTo(((stringConst) operand2).str) >= 0);
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
