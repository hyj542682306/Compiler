package frontend;

import AST.*;
import org.antlr.v4.runtime.Parser;
import util.position;
import util.error.syntaxError;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.Mx_liteBaseVisitor;
import parser.Mx_liteParser;

import java.util.ArrayList;

public class ASTBuilder extends Mx_liteBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitConstant(Mx_liteParser.ConstantContext ctx) {
        if (ctx.IntConst() != null)
            return new intExprNode(new position(ctx), Integer.parseInt(ctx.IntConst().getText()));
        if (ctx.BoolConst() != null)
            return new boolExprNode(new position(ctx), Boolean.parseBoolean(ctx.BoolConst().getText()));
        if (ctx.StringConst() != null)
            return new stringExprNode(new position(ctx), ctx.StringConst().getText());
        return new nullExprNode(new position(ctx));
    }

    @Override
    public ASTNode visitBasType(Mx_liteParser.BasTypeContext ctx) {
        return new typeNode(new position(ctx), ctx.getText(), 0);
    }

    @Override
    public ASTNode visitVarType(Mx_liteParser.VarTypeContext ctx) {
        return new typeNode(new position(ctx), ctx.getText(), (ctx.getChildCount() - 1) / 2);
    }

    @Override
    public ASTNode visitReturnType(Mx_liteParser.ReturnTypeContext ctx) {
        if (ctx.varType() != null) return visit(ctx.varType());
        return new typeNode(new position(ctx), ctx.Void().getText(), 0);
    }

    @Override
    public ASTNode visitProgram(Mx_liteParser.ProgramContext ctx) {
        progNode res = new progNode(new position(ctx));
        for (ParserRuleContext x : ctx.varDef()) {
            res.List.add(visit(x));
        }
        for (ParserRuleContext x : ctx.funcDef()) {
            res.List.add(visit(x));
        }
        for (ParserRuleContext x : ctx.classDef()) {
            res.List.add(visit(x));
        }
        return res;
    }

    @Override
    public ASTNode visitVarDef(Mx_liteParser.VarDefContext ctx) {
        varDefStmtNode res = new varDefStmtNode(new position(ctx));
        typeNode type = (typeNode) visit(ctx.varType());
        for (ParserRuleContext x : ctx.varDeclaration()) {
            varDecStmtNode tmp = (varDecStmtNode) visit(x);
            tmp.type = type;
            res.varList.add(tmp);
        }
        return res;
    }

    @Override
    public ASTNode visitVarDeclaration(Mx_liteParser.VarDeclarationContext ctx) {
        return new varDecStmtNode(new position(ctx), ctx.Identifier().getText(),
                ctx.expression() == null ? null : (exprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitFuncDef(Mx_liteParser.FuncDefContext ctx) {
        return new funcDefNode(new position(ctx),
                ctx.returnType() == null ? null : (typeNode) visit(ctx.returnType()),
                ctx.Identifier().getText(),
                ctx.parameterList() == null ? (new ArrayList<>()) : ((varDefStmtNode) visit(ctx.parameterList())).varList,
                (suiteStmtNode) visit(ctx.suite()));
    }

    @Override
    public ASTNode visitParameterList(Mx_liteParser.ParameterListContext ctx) {
        varDefStmtNode res = new varDefStmtNode(new position(ctx));
        for (ParserRuleContext x : ctx.parameter()) {
            res.varList.add((varDecStmtNode) visit(x));
        }
        return res;
    }

    @Override
    public ASTNode visitParameter(Mx_liteParser.ParameterContext ctx) {
        varDecStmtNode res = new varDecStmtNode(new position(ctx), ctx.Identifier().getText(), null);
        res.type = (typeNode) visit(ctx.varType());
        return res;
    }

    @Override
    public ASTNode visitSuite(Mx_liteParser.SuiteContext ctx) {
        suiteStmtNode res = new suiteStmtNode(new position(ctx));
        if (ctx.statement() != null) {
            for (ParserRuleContext x : ctx.statement()) {
                res.stmtList.add((stmtNode) visit(x));
            }
        }
        return res;
    }

    @Override
    public ASTNode visitClassDef(Mx_liteParser.ClassDefContext ctx) {
        classDefNode res = new classDefNode(new position(ctx), ctx.Identifier().getText());
        if (ctx.varDef() != null) {
            for (ParserRuleContext x : ctx.varDef()) {
                res.varList.addAll(((varDefStmtNode) visit(x)).varList);
            }
        }
        if (ctx.funcDef() != null) {
            for (ParserRuleContext x : ctx.funcDef()) {
                res.funcList.add((funcDefNode) visit(x));
            }
        }
        if (ctx.classStruct().size() != 0) {
            res.struct = (funcDefNode) visit(ctx.classStruct().get(0));
        }
        return res;
    }

    @Override
    public ASTNode visitClassStruct(Mx_liteParser.ClassStructContext ctx) {
        return new funcDefNode(new position(ctx), null, ctx.Identifier().getText(), null, (suiteStmtNode) visit(ctx.suite()));
    }

    @Override
    public ASTNode visitSuiteStmt(Mx_liteParser.SuiteStmtContext ctx) {
        suiteStmtNode res = new suiteStmtNode(new position(ctx));
        if (ctx.suite().statement() != null) {
            for (ParserRuleContext x : ctx.suite().statement()) {
                res.stmtList.add((stmtNode) visit(x));
            }
        }
        return res;
    }

    @Override
    public ASTNode visitVarDefStmt(Mx_liteParser.VarDefStmtContext ctx) {
        return visit(ctx.varDef());
    }

    @Override
    public ASTNode visitIfStmt(Mx_liteParser.IfStmtContext ctx) {
        return new ifStmtNode(new position(ctx), (exprNode) visit(ctx.expression()), (stmtNode) visit(ctx.trueStmt),
                ctx.falseStmt == null ? null : (stmtNode) visit(ctx.falseStmt));
    }

    @Override
    public ASTNode visitWhileStmt(Mx_liteParser.WhileStmtContext ctx) {
        return new whileStmtNode(new position(ctx), (exprNode) visit(ctx.expression()), (stmtNode) visit(ctx.statement()));
    }

    @Override
    public ASTNode visitForStmt(Mx_liteParser.ForStmtContext ctx) {
        return new forStmtNode(new position(ctx),
                ctx.expr1 == null ? null : (exprNode) visit(ctx.expr1),
                ctx.expr2 == null ? null : (exprNode) visit(ctx.expr2),
                ctx.expr3 == null ? null : (exprNode) visit(ctx.expr3),
                (stmtNode) visit(ctx.statement()));
    }

    @Override
    public ASTNode visitReturnStmt(Mx_liteParser.ReturnStmtContext ctx) {
        return new returnStmtNode(new position(ctx),
                ctx.expression() == null ? null : (exprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitBreakStmt(Mx_liteParser.BreakStmtContext ctx) {
        return new breakStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitContinueStmt(Mx_liteParser.ContinueStmtContext ctx) {
        return new continueStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitPureExprStmt(Mx_liteParser.PureExprStmtContext ctx) {
        return new pureExprStmtNode(new position(ctx), (exprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitEmptyStmt(Mx_liteParser.EmptyStmtContext ctx) {
        return new emptyStmtNode(new position(ctx));
    }

    @Override
    public ASTNode visitNewExpr(Mx_liteParser.NewExprContext ctx) {
        return visit(ctx.creator());
    }

    @Override
    public ASTNode visitIndexExpr(Mx_liteParser.IndexExprContext ctx) {
        return new indexExprNode(new position(ctx), (exprNode) visit(ctx.expr1), (exprNode) visit(ctx.expr2));
    }

    @Override
    public ASTNode visitThisExpr(Mx_liteParser.ThisExprContext ctx) {
        return new thisExprNode(new position(ctx));
    }

    @Override
    public ASTNode visitFuncExpr(Mx_liteParser.FuncExprContext ctx) {
        exprNode res=(exprNode) visit(ctx.expression());
        if(res instanceof classExprNode){
            res.assign=false;
            ((classExprNode) res).isFunc=true;
        }
        return new funcExprNode(new position(ctx), res,
                ctx.expressionList() == null ? new exprListNode(new position(ctx)) : (exprListNode) visit(ctx.expressionList()));
    }

    @Override
    public ASTNode visitLambdaExpr(Mx_liteParser.LambdaExprContext ctx) {
        return visit(ctx.lambda());
    }

    @Override
    public ASTNode visitClassExpr(Mx_liteParser.ClassExprContext ctx) {
        return new classExprNode(new position(ctx), (exprNode) visit(ctx.expression()), ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitBinaryExpr(Mx_liteParser.BinaryExprContext ctx) {
        return new binaryExprNode(new position(ctx), (exprNode) visit(ctx.src1), (exprNode) visit(ctx.src2), ctx.op.getText());
    }

    @Override
    public ASTNode visitPreExpr(Mx_liteParser.PreExprContext ctx) {
        return new preExprNode(new position(ctx), ctx.op.getText(), (exprNode) visit(ctx.expression()));
    }

    @Override
    public ASTNode visitSufExpr(Mx_liteParser.SufExprContext ctx) {
        return new sufExprNode(new position(ctx), (exprNode) visit(ctx.expression()), ctx.op.getText());
    }

    @Override
    public ASTNode visitSubExpr(Mx_liteParser.SubExprContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public ASTNode visitConstExpr(Mx_liteParser.ConstExprContext ctx) {
        return visit(ctx.constant());
    }

    @Override
    public ASTNode visitIdExpr(Mx_liteParser.IdExprContext ctx) {
        return new idExprNode(new position(ctx), ctx.Identifier().getText());
    }

    @Override
    public ASTNode visitExpressionList(Mx_liteParser.ExpressionListContext ctx) {
        exprListNode res = new exprListNode(new position(ctx));
        for (ParserRuleContext x : ctx.expression()) {
            res.exprList.add((exprNode) visit(x));
        }
        return res;
    }

    @Override
    public ASTNode visitErrorCrea(Mx_liteParser.ErrorCreaContext ctx) {
        throw new syntaxError("ErrorCreator", new position(ctx));
    }

    @Override
    public ASTNode visitArrayCrea(Mx_liteParser.ArrayCreaContext ctx) {
        typeNode type = (typeNode) visit(ctx.basType());
        ArrayList<exprNode> _exprList = new ArrayList<>();
        for (ParserRuleContext x : ctx.expression()) {
            _exprList.add((exprNode) visit(x));
        }
        return new newExprNode(new position(ctx), type, (ctx.getChildCount() - ctx.expression().size() - 1) / 2, _exprList);
    }

    @Override
    public ASTNode visitClassCrea(Mx_liteParser.ClassCreaContext ctx) {
        return new newExprNode(new position(ctx), (typeNode) visit(ctx.basType()), 0, null);
    }

    @Override
    public ASTNode visitBasicCrea(Mx_liteParser.BasicCreaContext ctx) {
        return new newExprNode(new position(ctx), (typeNode) visit(ctx.basType()), 0, null);
    }

    @Override
    public ASTNode visitLambda(Mx_liteParser.LambdaContext ctx) {
        lambdaExprNode res = new lambdaExprNode(new position(ctx), (suiteStmtNode) visit(ctx.suite()),
                ctx.expressionList() == null ? null : (exprListNode) visit(ctx.expressionList()));
        if (ctx.parameterList() != null) {
            res.paraList.addAll(((varDefStmtNode) visit(ctx.parameterList())).varList);
        }
        return res;
    }
}