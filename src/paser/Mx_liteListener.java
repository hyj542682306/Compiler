// Generated from D:/2021-2022-1/计算机系统（1）/大作业/Compiler/src/paser\Mx_lite.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Mx_liteParser}.
 */
public interface Mx_liteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#basType}.
	 * @param ctx the parse tree
	 */
	void enterBasType(Mx_liteParser.BasTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#basType}.
	 * @param ctx the parse tree
	 */
	void exitBasType(Mx_liteParser.BasTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(Mx_liteParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(Mx_liteParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(Mx_liteParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(Mx_liteParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(Mx_liteParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(Mx_liteParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(Mx_liteParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(Mx_liteParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(Mx_liteParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(Mx_liteParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(Mx_liteParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(Mx_liteParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(Mx_liteParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(Mx_liteParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(Mx_liteParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(Mx_liteParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(Mx_liteParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(Mx_liteParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(Mx_liteParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(Mx_liteParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#classStruct}.
	 * @param ctx the parse tree
	 */
	void enterClassStruct(Mx_liteParser.ClassStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#classStruct}.
	 * @param ctx the parse tree
	 */
	void exitClassStruct(Mx_liteParser.ClassStructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code block}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlock(Mx_liteParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code block}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlock(Mx_liteParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDefStmt(Mx_liteParser.VarDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDefStmt(Mx_liteParser.VarDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(Mx_liteParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(Mx_liteParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(Mx_liteParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(Mx_liteParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(Mx_liteParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(Mx_liteParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(Mx_liteParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(Mx_liteParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(Mx_liteParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(Mx_liteParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(Mx_liteParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(Mx_liteParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPureExprStmt(Mx_liteParser.PureExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPureExprStmt(Mx_liteParser.PureExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(Mx_liteParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link Mx_liteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(Mx_liteParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(Mx_liteParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(Mx_liteParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(Mx_liteParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(Mx_liteParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(Mx_liteParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(Mx_liteParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(Mx_liteParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(Mx_liteParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassExpr(Mx_liteParser.ClassExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassExpr(Mx_liteParser.ClassExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(Mx_liteParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(Mx_liteParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreExpr(Mx_liteParser.PreExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreExpr(Mx_liteParser.PreExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSufExpr(Mx_liteParser.SufExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSufExpr(Mx_liteParser.SufExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(Mx_liteParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(Mx_liteParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(Mx_liteParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(Mx_liteParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(Mx_liteParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link Mx_liteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(Mx_liteParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(Mx_liteParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(Mx_liteParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Mx_liteParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterCreator(Mx_liteParser.CreatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Mx_liteParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitCreator(Mx_liteParser.CreatorContext ctx);
}