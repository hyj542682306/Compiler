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

import java.awt.geom.AffineTransform;
import java.awt.print.PrinterGraphics;

public class IRBuilder implements ASTvisitor {
    public basicblock nowBlock,globalBlock;
    public scope globalScope;
    public int num = 0;
    public boolean Global;

    public IRBuilder(scope _globalScope) {
        globalScope=_globalScope;
    }

    @Override
    public void visit(progNode it) {
        //visit
        Global=true;
        it.List.forEach(x -> x.accept(this));
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
        Type nowType=globalScope.typeGet(it.type);
        IRType nowIRType=nowType.getIRType();
        for (int i=1;i<=it.type.dim;++i)
            nowIRType=new pointerType(nowIRType);
        num++;
        register nowReg=new register(nowIRType, Integer.toString(num));
        if (Global){
            //global
            if (it.expr==null){
                if (nowIRType instanceof pointerType||nowIRType instanceof arrayType||nowIRType instanceof classType)
                    globalBlock.addInst(new global(globalBlock,nowReg,nowIRType,new nullOperand()));
                else if (nowIRType instanceof intType)
                    globalBlock.addInst(new global(globalBlock,nowReg,nowIRType,new intConst(0)));
            }
            else{
                it.expr.accept(this);
                globalBlock.addInst(new global(globalBlock,nowReg,nowIRType,it.expr.operand));
            }
        }
        else {
            //alloca
            nowBlock.addInst(new alloca(nowBlock, nowReg, nowIRType));

            //store - put the result of expr in the current register
            if (it.expr != null) {
                it.expr.accept(this);
                nowBlock.addInst(new store(nowBlock,nowIRType,it.expr.operand,nowReg));
            }
        }
    }

    @Override
    public void visit(varDefStmtNode it) {
        it.varList.forEach(x -> x.accept(this));
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
        it.operand=new intConst(it.value);
    }

    @Override
    public void visit(boolExprNode it) {
        it.operand=new boolConst(it.value);
    }

    @Override
    public void visit(stringExprNode it) {

    }

    @Override
    public void visit(nullExprNode it) {
        it.operand=new nullOperand();
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
        it.expr.accept(this);

        IRType nowIRType=it.expr.operand.type;
        Operand BeforeReg=it.expr.operand;

        //binary
        num++;
        register AfterReg=new register(nowIRType,Integer.toString(num));
        if (it.op.equals("++"))
            nowBlock.addInst(new binary(nowBlock,AfterReg,"add",nowIRType,BeforeReg,new intConst(1)));
        else if (it.op.equals("--"))
            nowBlock.addInst(new binary(nowBlock,AfterReg,"add",nowIRType,BeforeReg,new intConst(-1)));

        //store - update the sufExpr's value
        nowBlock.addInst(new store(nowBlock,nowIRType,AfterReg,it.expr.operand));

        //sufExpr's operand
        it.operand=BeforeReg;
    }

    @Override
    public void visit(preExprNode it) {
        it.expr.accept(this);

        IRType nowIRType=it.expr.operand.type;
        Operand BeforeReg=it.expr.operand;

        if (it.op.equals("++")) {
            //binary
            num++;
            register AfterReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock, AfterReg,"add",nowIRType,BeforeReg,new intConst(1)));

            //store - update the preExpr's value
            nowBlock.addInst(new store(nowBlock,nowIRType,AfterReg,it.expr.operand));

            //preExpr's operand
            it.operand=AfterReg;
        }
        else if (it.op.equals("--")){
            //binary
            num++;
            register AfterReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock, AfterReg,"add",nowIRType,BeforeReg,new intConst(-1)));

            //store - update the preExpr's value
            nowBlock.addInst(new store(nowBlock,nowIRType,AfterReg,it.expr.operand));

            //preExpr's operand
            it.operand=AfterReg;
        }
        else if (it.op.equals("+")){
            //preExpr's operand
            it.operand=BeforeReg;
        }
        else if (it.op.equals("-")){
            //binary
            num++;
            register AfterReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock, AfterReg,"sub",nowIRType,new intConst(0),BeforeReg));

            //preExpr's operand
            it.operand=AfterReg;
        }
        else if (it.op.equals("!")){
            ;
        }
        else if (it.op.equals("~")){
            //binary
            num++;
            register AfterReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock, AfterReg,"xor",nowIRType,BeforeReg,new intConst(-1)));

            //preExpr's operand
            it.operand=AfterReg;
        }
    }

    @Override
    public void visit(newExprNode it) {

    }

    @Override
    public void visit(binaryExprNode it) {
        it.expr1.accept(this);
        it.expr2.accept(this);

        IRType nowIRType=it.expr1.operand.type;
        Operand operand1=it.expr1.operand;
        Operand operand2=it.expr2.operand;

        if (it.op.equals("+")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"add",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("-")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"sub",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("*")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"mul",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("/")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"sdiv",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if(it.op.equals("%")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"srem",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("<<")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"shl",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals(">>")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"ashr",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("<")){
            ;
        }
        else if (it.op.equals("<=")){
            ;
        }
        else if (it.op.equals(">")){
            ;
        }
        else if (it.op.equals(">=")){
            ;
        }
        else if (it.op.equals("!=")){
            ;
        }
        else if (it.op.equals("==")){
            ;
        }
        else if (it.op.equals("^")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"xor",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("|")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"or",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("&")){
            //binary
            ++num;
            register newReg=new register(nowIRType,Integer.toString(num));
            nowBlock.addInst(new binary(nowBlock,newReg,"and",nowIRType,operand1,operand2));

            //binaryExpr's operand
            it.operand=newReg;
        }
        else if (it.op.equals("||")){
            ;
        }
        else if (it.op.equals("&&")){
            ;
        }
        else if (it.op.equals("=")){
            //store
            nowBlock.addInst(new store(nowBlock,nowIRType,operand2,operand1));

            //binaryExpr's operand
        }
    }

    @Override
    public void visit(exprListNode it) {

    }
}
