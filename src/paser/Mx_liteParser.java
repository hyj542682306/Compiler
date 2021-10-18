// Generated from D:/2021-2022-1/计算机系统（1）/大作业/Compiler/src/paser\Mx_lite.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Mx_liteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Int=1, Bool=2, String=3, Null=4, Void=5, True=6, False=7, If=8, Else=9, 
		For=10, While=11, Break=12, Continue=13, Return=14, New=15, Class=16, 
		This=17, Dot=18, LeftParen=19, RightParen=20, LeftBracket=21, RightBracket=22, 
		LeftBrace=23, RightBrace=24, Less=25, LessEqual=26, Greater=27, GreaterEqual=28, 
		LeftShift=29, RightShift=30, Plus=31, SelfPlus=32, Minus=33, SelfMinus=34, 
		Mul=35, Div=36, Mod=37, And=38, Or=39, AndAnd=40, OrOr=41, Caret=42, Not=43, 
		Tilde=44, Question=45, Colon=46, Semi=47, Comma=48, Assign=49, Equal=50, 
		NotEqual=51, WhiteSpace=52, NewLine=53, LineComment=54, BlockComment=55, 
		Identifier=56, BoolConst=57, IntConst=58, StringConst=59;
	public static final int
		RULE_constant = 0, RULE_basType = 1, RULE_varType = 2, RULE_returnType = 3, 
		RULE_program = 4, RULE_varDef = 5, RULE_varDeclaration = 6, RULE_funcDef = 7, 
		RULE_parameterList = 8, RULE_parameter = 9, RULE_suite = 10, RULE_classDef = 11, 
		RULE_classStruct = 12, RULE_statement = 13, RULE_expression = 14, RULE_expressionList = 15, 
		RULE_creator = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"constant", "basType", "varType", "returnType", "program", "varDef", 
			"varDeclaration", "funcDef", "parameterList", "parameter", "suite", "classDef", 
			"classStruct", "statement", "expression", "expressionList", "creator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'bool'", "'string'", "'null'", "'void'", "'true'", "'false'", 
			"'if'", "'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", 
			"'new'", "'class'", "'this'", "'.'", "'('", "')'", "'['", "']'", "'{'", 
			"'}'", "'<'", "'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'", "'++'", "'-'", 
			"'--'", "'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", 
			"'~'", "'?'", "':'", "';'", "','", "'='", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Int", "Bool", "String", "Null", "Void", "True", "False", "If", 
			"Else", "For", "While", "Break", "Continue", "Return", "New", "Class", 
			"This", "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", 
			"LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus", 
			"Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", 
			"Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual", 
			"WhiteSpace", "NewLine", "LineComment", "BlockComment", "Identifier", 
			"BoolConst", "IntConst", "StringConst"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx_lite.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Mx_liteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode BoolConst() { return getToken(Mx_liteParser.BoolConst, 0); }
		public TerminalNode IntConst() { return getToken(Mx_liteParser.IntConst, 0); }
		public TerminalNode StringConst() { return getToken(Mx_liteParser.StringConst, 0); }
		public TerminalNode Null() { return getToken(Mx_liteParser.Null, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public TerminalNode Int() { return getToken(Mx_liteParser.Int, 0); }
		public TerminalNode Bool() { return getToken(Mx_liteParser.Bool, 0); }
		public TerminalNode String() { return getToken(Mx_liteParser.String, 0); }
		public BasTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterBasType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitBasType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitBasType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasTypeContext basType() throws RecognitionException {
		BasTypeContext _localctx = new BasTypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_basType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypeContext extends ParserRuleContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(Mx_liteParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(Mx_liteParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(Mx_liteParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(Mx_liteParser.RightBracket, i);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitVarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			basType();
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftBracket) {
				{
				{
				setState(39);
				match(LeftBracket);
				setState(40);
				match(RightBracket);
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode Void() { return getToken(Mx_liteParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_returnType);
		try {
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				varType();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(50);
					varDef();
					}
					break;
				case 2:
					{
					setState(51);
					funcDef();
					}
					break;
				case 3:
					{
					setState(52);
					classDef();
					}
					break;
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(Mx_liteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(Mx_liteParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			varType();
			setState(59);
			varDeclaration();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(60);
				match(Comma);
				setState(61);
				varDeclaration();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(Mx_liteParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(Identifier);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(70);
				match(Assign);
				setState(71);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			returnType();
			setState(75);
			match(Identifier);
			setState(76);
			match(LeftParen);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(77);
				parameterList();
				}
			}

			setState(80);
			match(RightParen);
			setState(81);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(Mx_liteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(Mx_liteParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			parameter();
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(84);
				match(Comma);
				setState(85);
				parameter();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			varType();
			setState(92);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(Mx_liteParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(Mx_liteParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(LeftBrace);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
				{
				{
				setState(95);
				statement();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(Mx_liteParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(Mx_liteParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(Mx_liteParser.RightBrace, 0); }
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassStructContext> classStruct() {
			return getRuleContexts(ClassStructContext.class);
		}
		public ClassStructContext classStruct(int i) {
			return getRuleContext(ClassStructContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(Class);
			setState(104);
			match(Identifier);
			setState(105);
			match(LeftBrace);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(109);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(106);
					varDef();
					}
					break;
				case 2:
					{
					setState(107);
					funcDef();
					}
					break;
				case 3:
					{
					setState(108);
					classStruct();
					}
					break;
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(114);
			match(RightBrace);
			setState(115);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassStructContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ClassStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterClassStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitClassStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitClassStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassStructContext classStruct() throws RecognitionException {
		ClassStructContext _localctx = new ClassStructContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_classStruct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(Identifier);
			setState(118);
			match(LeftParen);
			setState(119);
			match(RightParen);
			setState(120);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDefStmtContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarDefStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterVarDefStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitVarDefStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitVarDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext expr1;
		public ExpressionContext expr2;
		public ExpressionContext expr3;
		public TerminalNode For() { return getToken(Mx_liteParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(Mx_liteParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(Mx_liteParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(Mx_liteParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public TerminalNode If() { return getToken(Mx_liteParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(Mx_liteParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(Mx_liteParser.Break, 0); }
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BlockContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public BlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(Mx_liteParser.Return, 0); }
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(Mx_liteParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PureExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(Mx_liteParser.Semi, 0); }
		public PureExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterPureExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitPureExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitPureExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_statement);
		int _la;
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new BlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				suite();
				}
				break;
			case 2:
				_localctx = new VarDefStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				varDef();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				match(If);
				setState(125);
				match(LeftParen);
				setState(126);
				expression(0);
				setState(127);
				match(RightParen);
				setState(128);
				((IfStmtContext)_localctx).trueStmt = statement();
				setState(131);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(129);
					match(Else);
					setState(130);
					((IfStmtContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(133);
				match(While);
				setState(134);
				match(LeftParen);
				setState(135);
				expression(0);
				setState(136);
				match(RightParen);
				setState(137);
				statement();
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				match(For);
				setState(140);
				match(LeftParen);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
					{
					setState(141);
					((ForStmtContext)_localctx).expr1 = expression(0);
					}
				}

				setState(144);
				match(Semi);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
					{
					setState(145);
					((ForStmtContext)_localctx).expr2 = expression(0);
					}
				}

				setState(148);
				match(Semi);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
					{
					setState(149);
					((ForStmtContext)_localctx).expr3 = expression(0);
					}
				}

				setState(152);
				match(RightParen);
				setState(153);
				statement();
				}
				break;
			case 6:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				match(Return);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
					{
					setState(155);
					expression(0);
					}
				}

				setState(158);
				match(Semi);
				}
				break;
			case 7:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(159);
				match(Break);
				setState(160);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(161);
				match(Continue);
				setState(162);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new PureExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(163);
				expression(0);
				setState(164);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(166);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(Mx_liteParser.New, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexExprContext extends ExpressionContext {
		public ExpressionContext expr1;
		public ExpressionContext expr2;
		public TerminalNode LeftBracket() { return getToken(Mx_liteParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(Mx_liteParser.RightBracket, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IndexExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitIndexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitIndexExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisExprContext extends ExpressionContext {
		public TerminalNode This() { return getToken(Mx_liteParser.This, 0); }
		public ThisExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterThisExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitThisExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitThisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FuncExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(Mx_liteParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public ClassExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitClassExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitClassExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public ExpressionContext src1;
		public Token op;
		public ExpressionContext src2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(Mx_liteParser.Mul, 0); }
		public TerminalNode Div() { return getToken(Mx_liteParser.Div, 0); }
		public TerminalNode Mod() { return getToken(Mx_liteParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(Mx_liteParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(Mx_liteParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(Mx_liteParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(Mx_liteParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(Mx_liteParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(Mx_liteParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(Mx_liteParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(Mx_liteParser.GreaterEqual, 0); }
		public TerminalNode NotEqual() { return getToken(Mx_liteParser.NotEqual, 0); }
		public TerminalNode Equal() { return getToken(Mx_liteParser.Equal, 0); }
		public TerminalNode And() { return getToken(Mx_liteParser.And, 0); }
		public TerminalNode Caret() { return getToken(Mx_liteParser.Caret, 0); }
		public TerminalNode Or() { return getToken(Mx_liteParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(Mx_liteParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(Mx_liteParser.OrOr, 0); }
		public TerminalNode Assign() { return getToken(Mx_liteParser.Assign, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(Mx_liteParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(Mx_liteParser.SelfMinus, 0); }
		public TerminalNode Plus() { return getToken(Mx_liteParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(Mx_liteParser.Minus, 0); }
		public TerminalNode Not() { return getToken(Mx_liteParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(Mx_liteParser.Tilde, 0); }
		public PreExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterPreExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitPreExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitPreExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SufExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(Mx_liteParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(Mx_liteParser.SelfMinus, 0); }
		public SufExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterSufExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitSufExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitSufExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubExprContext extends ExpressionContext {
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public SubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstExprContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterConstExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitConstExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitConstExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(Mx_liteParser.Identifier, 0); }
		public IdExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
				{
				_localctx = new SubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(170);
				match(LeftParen);
				setState(171);
				expression(0);
				setState(172);
				match(RightParen);
				}
				break;
			case Null:
			case BoolConst:
			case IntConst:
			case StringConst:
				{
				_localctx = new ConstExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				constant();
				}
				break;
			case This:
				{
				_localctx = new ThisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				match(This);
				}
				break;
			case Identifier:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(Identifier);
				}
				break;
			case SelfPlus:
			case SelfMinus:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(177);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfPlus || _la==SelfMinus) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(178);
				expression(15);
				}
				break;
			case Plus:
			case Minus:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(180);
				expression(14);
				}
				break;
			case Not:
			case Tilde:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Not || _la==Tilde) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(182);
				expression(13);
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				match(New);
				setState(184);
				creator();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(238);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(236);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(188);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(189);
						((BinaryExprContext)_localctx).src2 = expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(191);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(192);
						((BinaryExprContext)_localctx).src2 = expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(194);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(195);
						((BinaryExprContext)_localctx).src2 = expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(197);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(198);
						((BinaryExprContext)_localctx).src2 = expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(200);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(201);
						((BinaryExprContext)_localctx).src2 = expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(203);
						((BinaryExprContext)_localctx).op = match(And);
						setState(204);
						((BinaryExprContext)_localctx).src2 = expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(206);
						((BinaryExprContext)_localctx).op = match(Caret);
						setState(207);
						((BinaryExprContext)_localctx).src2 = expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(209);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(210);
						((BinaryExprContext)_localctx).src2 = expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(212);
						((BinaryExprContext)_localctx).op = match(AndAnd);
						setState(213);
						((BinaryExprContext)_localctx).src2 = expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(215);
						((BinaryExprContext)_localctx).op = match(OrOr);
						setState(216);
						((BinaryExprContext)_localctx).src2 = expression(3);
						}
						break;
					case 11:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(218);
						((BinaryExprContext)_localctx).op = match(Assign);
						setState(219);
						((BinaryExprContext)_localctx).src2 = expression(1);
						}
						break;
					case 12:
						{
						_localctx = new FuncExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(221);
						match(LeftParen);
						setState(223);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Identifier) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) {
							{
							setState(222);
							expressionList();
							}
						}

						setState(225);
						match(RightParen);
						}
						break;
					case 13:
						{
						_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState));
						((IndexExprContext)_localctx).expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(227);
						match(LeftBracket);
						setState(228);
						((IndexExprContext)_localctx).expr2 = expression(0);
						setState(229);
						match(RightBracket);
						}
						break;
					case 14:
						{
						_localctx = new ClassExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(231);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(232);
						match(Dot);
						setState(233);
						match(Identifier);
						}
						break;
					case 15:
						{
						_localctx = new SufExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(234);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(235);
						((SufExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
							((SufExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(240);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(Mx_liteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(Mx_liteParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			expression(0);
			setState(246);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(242);
				match(Comma);
				setState(243);
				expression(0);
				}
				}
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(Mx_liteParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(Mx_liteParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(Mx_liteParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(Mx_liteParser.RightBracket, i);
		}
		public TerminalNode LeftParen() { return getToken(Mx_liteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(Mx_liteParser.RightParen, 0); }
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Mx_liteListener ) ((Mx_liteListener)listener).exitCreator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Mx_liteVisitor ) return ((Mx_liteVisitor<? extends T>)visitor).visitCreator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_creator);
		try {
			int _alt;
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				basType();
				setState(254); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(250);
						match(LeftBracket);
						setState(251);
						expression(0);
						setState(252);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(256); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(260); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(258);
						match(LeftBracket);
						setState(259);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(262); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(268); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(264);
						match(LeftBracket);
						setState(265);
						expression(0);
						setState(266);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(270); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				basType();
				setState(277); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(273);
						match(LeftBracket);
						setState(274);
						expression(0);
						setState(275);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(279); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(281);
						match(LeftBracket);
						setState(282);
						match(RightBracket);
						}
						} 
					}
					setState(287);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(288);
				basType();
				setState(289);
				match(LeftParen);
				setState(290);
				match(RightParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(292);
				basType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 19);
		case 12:
			return precpred(_ctx, 18);
		case 13:
			return precpred(_ctx, 17);
		case 14:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u012a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\5\3\5\5\5\63\n"+
		"\5\3\6\3\6\3\6\7\68\n\6\f\6\16\6;\13\6\3\7\3\7\3\7\3\7\7\7A\n\7\f\7\16"+
		"\7D\13\7\3\7\3\7\3\b\3\b\3\b\5\bK\n\b\3\t\3\t\3\t\3\t\5\tQ\n\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\7\nY\n\n\f\n\16\n\\\13\n\3\13\3\13\3\13\3\f\3\f\7\fc"+
		"\n\f\f\f\16\ff\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\7\rp\n\r\f\r\16\r"+
		"s\13\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\5\17\u0086\n\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u0091\n\17\3\17\3\17\5\17\u0095\n\17\3\17\3\17\5\17\u0099"+
		"\n\17\3\17\3\17\3\17\3\17\5\17\u009f\n\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u00aa\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00bc\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u00e2\n\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\7\20\u00ef\n\20\f\20\16\20\u00f2\13\20\3\21"+
		"\3\21\3\21\7\21\u00f7\n\21\f\21\16\21\u00fa\13\21\3\22\3\22\3\22\3\22"+
		"\3\22\6\22\u0101\n\22\r\22\16\22\u0102\3\22\3\22\6\22\u0107\n\22\r\22"+
		"\16\22\u0108\3\22\3\22\3\22\3\22\6\22\u010f\n\22\r\22\16\22\u0110\3\22"+
		"\3\22\3\22\3\22\3\22\6\22\u0118\n\22\r\22\16\22\u0119\3\22\3\22\7\22\u011e"+
		"\n\22\f\22\16\22\u0121\13\22\3\22\3\22\3\22\3\22\3\22\5\22\u0128\n\22"+
		"\3\22\2\3\36\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\13\4\2\6\6"+
		";=\4\2\3\5::\4\2\"\"$$\4\2!!##\3\2-.\3\2%\'\3\2\37 \3\2\33\36\3\2\64\65"+
		"\2\u0153\2$\3\2\2\2\4&\3\2\2\2\6(\3\2\2\2\b\62\3\2\2\2\n9\3\2\2\2\f<\3"+
		"\2\2\2\16G\3\2\2\2\20L\3\2\2\2\22U\3\2\2\2\24]\3\2\2\2\26`\3\2\2\2\30"+
		"i\3\2\2\2\32w\3\2\2\2\34\u00a9\3\2\2\2\36\u00bb\3\2\2\2 \u00f3\3\2\2\2"+
		"\"\u0127\3\2\2\2$%\t\2\2\2%\3\3\2\2\2&\'\t\3\2\2\'\5\3\2\2\2(-\5\4\3\2"+
		")*\7\27\2\2*,\7\30\2\2+)\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\7\3\2"+
		"\2\2/-\3\2\2\2\60\63\5\6\4\2\61\63\7\7\2\2\62\60\3\2\2\2\62\61\3\2\2\2"+
		"\63\t\3\2\2\2\648\5\f\7\2\658\5\20\t\2\668\5\30\r\2\67\64\3\2\2\2\67\65"+
		"\3\2\2\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\13\3\2\2\2;9\3"+
		"\2\2\2<=\5\6\4\2=B\5\16\b\2>?\7\62\2\2?A\5\16\b\2@>\3\2\2\2AD\3\2\2\2"+
		"B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3\2\2\2EF\7\61\2\2F\r\3\2\2\2GJ\7:\2"+
		"\2HI\7\63\2\2IK\5\36\20\2JH\3\2\2\2JK\3\2\2\2K\17\3\2\2\2LM\5\b\5\2MN"+
		"\7:\2\2NP\7\25\2\2OQ\5\22\n\2PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\26\2\2"+
		"ST\5\26\f\2T\21\3\2\2\2UZ\5\24\13\2VW\7\62\2\2WY\5\24\13\2XV\3\2\2\2Y"+
		"\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\23\3\2\2\2\\Z\3\2\2\2]^\5\6\4\2^_\7:\2"+
		"\2_\25\3\2\2\2`d\7\31\2\2ac\5\34\17\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de"+
		"\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\32\2\2h\27\3\2\2\2ij\7\22\2\2jk\7:\2"+
		"\2kq\7\31\2\2lp\5\f\7\2mp\5\20\t\2np\5\32\16\2ol\3\2\2\2om\3\2\2\2on\3"+
		"\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\32\2\2uv"+
		"\7\61\2\2v\31\3\2\2\2wx\7:\2\2xy\7\25\2\2yz\7\26\2\2z{\5\26\f\2{\33\3"+
		"\2\2\2|\u00aa\5\26\f\2}\u00aa\5\f\7\2~\177\7\n\2\2\177\u0080\7\25\2\2"+
		"\u0080\u0081\5\36\20\2\u0081\u0082\7\26\2\2\u0082\u0085\5\34\17\2\u0083"+
		"\u0084\7\13\2\2\u0084\u0086\5\34\17\2\u0085\u0083\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u00aa\3\2\2\2\u0087\u0088\7\r\2\2\u0088\u0089\7\25\2\2"+
		"\u0089\u008a\5\36\20\2\u008a\u008b\7\26\2\2\u008b\u008c\5\34\17\2\u008c"+
		"\u00aa\3\2\2\2\u008d\u008e\7\f\2\2\u008e\u0090\7\25\2\2\u008f\u0091\5"+
		"\36\20\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0094\7\61\2\2\u0093\u0095\5\36\20\2\u0094\u0093\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\7\61\2\2\u0097\u0099\5\36\20"+
		"\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b"+
		"\7\26\2\2\u009b\u00aa\5\34\17\2\u009c\u009e\7\20\2\2\u009d\u009f\5\36"+
		"\20\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00aa\7\61\2\2\u00a1\u00a2\7\16\2\2\u00a2\u00aa\7\61\2\2\u00a3\u00a4"+
		"\7\17\2\2\u00a4\u00aa\7\61\2\2\u00a5\u00a6\5\36\20\2\u00a6\u00a7\7\61"+
		"\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00aa\7\61\2\2\u00a9|\3\2\2\2\u00a9}\3"+
		"\2\2\2\u00a9~\3\2\2\2\u00a9\u0087\3\2\2\2\u00a9\u008d\3\2\2\2\u00a9\u009c"+
		"\3\2\2\2\u00a9\u00a1\3\2\2\2\u00a9\u00a3\3\2\2\2\u00a9\u00a5\3\2\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00aa\35\3\2\2\2\u00ab\u00ac\b\20\1\2\u00ac\u00ad\7\25"+
		"\2\2\u00ad\u00ae\5\36\20\2\u00ae\u00af\7\26\2\2\u00af\u00bc\3\2\2\2\u00b0"+
		"\u00bc\5\2\2\2\u00b1\u00bc\7\23\2\2\u00b2\u00bc\7:\2\2\u00b3\u00b4\t\4"+
		"\2\2\u00b4\u00bc\5\36\20\21\u00b5\u00b6\t\5\2\2\u00b6\u00bc\5\36\20\20"+
		"\u00b7\u00b8\t\6\2\2\u00b8\u00bc\5\36\20\17\u00b9\u00ba\7\21\2\2\u00ba"+
		"\u00bc\5\"\22\2\u00bb\u00ab\3\2\2\2\u00bb\u00b0\3\2\2\2\u00bb\u00b1\3"+
		"\2\2\2\u00bb\u00b2\3\2\2\2\u00bb\u00b3\3\2\2\2\u00bb\u00b5\3\2\2\2\u00bb"+
		"\u00b7\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00f0\3\2\2\2\u00bd\u00be\f\r"+
		"\2\2\u00be\u00bf\t\7\2\2\u00bf\u00ef\5\36\20\16\u00c0\u00c1\f\f\2\2\u00c1"+
		"\u00c2\t\5\2\2\u00c2\u00ef\5\36\20\r\u00c3\u00c4\f\13\2\2\u00c4\u00c5"+
		"\t\b\2\2\u00c5\u00ef\5\36\20\f\u00c6\u00c7\f\n\2\2\u00c7\u00c8\t\t\2\2"+
		"\u00c8\u00ef\5\36\20\13\u00c9\u00ca\f\t\2\2\u00ca\u00cb\t\n\2\2\u00cb"+
		"\u00ef\5\36\20\n\u00cc\u00cd\f\b\2\2\u00cd\u00ce\7(\2\2\u00ce\u00ef\5"+
		"\36\20\t\u00cf\u00d0\f\7\2\2\u00d0\u00d1\7,\2\2\u00d1\u00ef\5\36\20\b"+
		"\u00d2\u00d3\f\6\2\2\u00d3\u00d4\7)\2\2\u00d4\u00ef\5\36\20\7\u00d5\u00d6"+
		"\f\5\2\2\u00d6\u00d7\7*\2\2\u00d7\u00ef\5\36\20\6\u00d8\u00d9\f\4\2\2"+
		"\u00d9\u00da\7+\2\2\u00da\u00ef\5\36\20\5\u00db\u00dc\f\3\2\2\u00dc\u00dd"+
		"\7\63\2\2\u00dd\u00ef\5\36\20\3\u00de\u00df\f\25\2\2\u00df\u00e1\7\25"+
		"\2\2\u00e0\u00e2\5 \21\2\u00e1\u00e0\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e3\3\2\2\2\u00e3\u00ef\7\26\2\2\u00e4\u00e5\f\24\2\2\u00e5\u00e6\7"+
		"\27\2\2\u00e6\u00e7\5\36\20\2\u00e7\u00e8\7\30\2\2\u00e8\u00ef\3\2\2\2"+
		"\u00e9\u00ea\f\23\2\2\u00ea\u00eb\7\24\2\2\u00eb\u00ef\7:\2\2\u00ec\u00ed"+
		"\f\22\2\2\u00ed\u00ef\t\4\2\2\u00ee\u00bd\3\2\2\2\u00ee\u00c0\3\2\2\2"+
		"\u00ee\u00c3\3\2\2\2\u00ee\u00c6\3\2\2\2\u00ee\u00c9\3\2\2\2\u00ee\u00cc"+
		"\3\2\2\2\u00ee\u00cf\3\2\2\2\u00ee\u00d2\3\2\2\2\u00ee\u00d5\3\2\2\2\u00ee"+
		"\u00d8\3\2\2\2\u00ee\u00db\3\2\2\2\u00ee\u00de\3\2\2\2\u00ee\u00e4\3\2"+
		"\2\2\u00ee\u00e9\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\37\3\2\2\2\u00f2\u00f0\3\2\2"+
		"\2\u00f3\u00f8\5\36\20\2\u00f4\u00f5\7\62\2\2\u00f5\u00f7\5\36\20\2\u00f6"+
		"\u00f4\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9!\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u0100\5\4\3\2\u00fc\u00fd"+
		"\7\27\2\2\u00fd\u00fe\5\36\20\2\u00fe\u00ff\7\30\2\2\u00ff\u0101\3\2\2"+
		"\2\u0100\u00fc\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103"+
		"\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0105\7\27\2\2\u0105\u0107\7\30\2\2"+
		"\u0106\u0104\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109"+
		"\3\2\2\2\u0109\u010e\3\2\2\2\u010a\u010b\7\27\2\2\u010b\u010c\5\36\20"+
		"\2\u010c\u010d\7\30\2\2\u010d\u010f\3\2\2\2\u010e\u010a\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0128\3\2"+
		"\2\2\u0112\u0117\5\4\3\2\u0113\u0114\7\27\2\2\u0114\u0115\5\36\20\2\u0115"+
		"\u0116\7\30\2\2\u0116\u0118\3\2\2\2\u0117\u0113\3\2\2\2\u0118\u0119\3"+
		"\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011f\3\2\2\2\u011b"+
		"\u011c\7\27\2\2\u011c\u011e\7\30\2\2\u011d\u011b\3\2\2\2\u011e\u0121\3"+
		"\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0128\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0122\u0123\5\4\3\2\u0123\u0124\7\25\2\2\u0124\u0125\7"+
		"\26\2\2\u0125\u0128\3\2\2\2\u0126\u0128\5\4\3\2\u0127\u00fb\3\2\2\2\u0127"+
		"\u0112\3\2\2\2\u0127\u0122\3\2\2\2\u0127\u0126\3\2\2\2\u0128#\3\2\2\2"+
		"\36-\62\679BJPZdoq\u0085\u0090\u0094\u0098\u009e\u00a9\u00bb\u00e1\u00ee"+
		"\u00f0\u00f8\u0102\u0108\u0110\u0119\u011f\u0127";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}