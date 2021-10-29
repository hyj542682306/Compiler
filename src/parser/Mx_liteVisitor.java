package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Mx_liteParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface Mx_liteVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link Mx_liteParser#constant}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstant(Mx_liteParser.ConstantContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#basType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBasType(Mx_liteParser.BasTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#varType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVarType(Mx_liteParser.VarTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#returnType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnType(Mx_liteParser.ReturnTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#program}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitProgram(Mx_liteParser.ProgramContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#varDef}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVarDef(Mx_liteParser.VarDefContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#varDeclaration}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVarDeclaration(Mx_liteParser.VarDeclarationContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#funcDef}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFuncDef(Mx_liteParser.FuncDefContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#parameterList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParameterList(Mx_liteParser.ParameterListContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#parameter}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParameter(Mx_liteParser.ParameterContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#suite}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuite(Mx_liteParser.SuiteContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#classDef}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassDef(Mx_liteParser.ClassDefContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#classStruct}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassStruct(Mx_liteParser.ClassStructContext ctx);

    /**
     * Visit a parse tree produced by the {@code suiteStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuiteStmt(Mx_liteParser.SuiteStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code varDefStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVarDefStmt(Mx_liteParser.VarDefStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code ifStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfStmt(Mx_liteParser.IfStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code whileStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWhileStmt(Mx_liteParser.WhileStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code forStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitForStmt(Mx_liteParser.ForStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code returnStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnStmt(Mx_liteParser.ReturnStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code breakStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBreakStmt(Mx_liteParser.BreakStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code continueStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitContinueStmt(Mx_liteParser.ContinueStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code pureExprStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPureExprStmt(Mx_liteParser.PureExprStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code emptyStmt}
     * labeled alternative in {@link Mx_liteParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitEmptyStmt(Mx_liteParser.EmptyStmtContext ctx);

    /**
     * Visit a parse tree produced by the {@code newExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNewExpr(Mx_liteParser.NewExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code indexExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIndexExpr(Mx_liteParser.IndexExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code thisExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitThisExpr(Mx_liteParser.ThisExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code funcExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFuncExpr(Mx_liteParser.FuncExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code lambdaExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLambdaExpr(Mx_liteParser.LambdaExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code classExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassExpr(Mx_liteParser.ClassExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code binaryExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBinaryExpr(Mx_liteParser.BinaryExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code preExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPreExpr(Mx_liteParser.PreExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code sufExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSufExpr(Mx_liteParser.SufExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code subExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubExpr(Mx_liteParser.SubExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code constExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstExpr(Mx_liteParser.ConstExprContext ctx);

    /**
     * Visit a parse tree produced by the {@code idExpr}
     * labeled alternative in {@link Mx_liteParser#expression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIdExpr(Mx_liteParser.IdExprContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#expressionList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitExpressionList(Mx_liteParser.ExpressionListContext ctx);

    /**
     * Visit a parse tree produced by the {@code errorCrea}
     * labeled alternative in {@link Mx_liteParser#creator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitErrorCrea(Mx_liteParser.ErrorCreaContext ctx);

    /**
     * Visit a parse tree produced by the {@code arrayCrea}
     * labeled alternative in {@link Mx_liteParser#creator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayCrea(Mx_liteParser.ArrayCreaContext ctx);

    /**
     * Visit a parse tree produced by the {@code classCrea}
     * labeled alternative in {@link Mx_liteParser#creator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassCrea(Mx_liteParser.ClassCreaContext ctx);

    /**
     * Visit a parse tree produced by the {@code basicCrea}
     * labeled alternative in {@link Mx_liteParser#creator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBasicCrea(Mx_liteParser.BasicCreaContext ctx);

    /**
     * Visit a parse tree produced by {@link Mx_liteParser#lambda}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLambda(Mx_liteParser.LambdaContext ctx);
}