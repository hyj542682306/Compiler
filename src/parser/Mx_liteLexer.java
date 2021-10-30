package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Mx_liteLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Dot=2, LeftParen=3, RightParen=4, LeftBracket=5, RightBracket=6, 
		LeftBrace=7, RightBrace=8, Less=9, LessEqual=10, Greater=11, GreaterEqual=12, 
		LeftShift=13, RightShift=14, Plus=15, SelfPlus=16, Minus=17, SelfMinus=18, 
		Mul=19, Div=20, Mod=21, And=22, Or=23, AndAnd=24, OrOr=25, Caret=26, Not=27, 
		Tilde=28, Question=29, Colon=30, Semi=31, Comma=32, Assign=33, Equal=34, 
		NotEqual=35, LambdaStart=36, WhiteSpace=37, NewLine=38, LineComment=39, 
		BlockComment=40, BoolConst=41, IntConst=42, StringConst=43, Int=44, Bool=45, 
		String=46, Null=47, Void=48, True=49, False=50, If=51, Else=52, For=53, 
		While=54, Break=55, Continue=56, Return=57, New=58, Class=59, This=60, 
		Identifier=61;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", 
			"LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus", 
			"Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", 
			"Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual", 
			"LambdaStart", "WhiteSpace", "NewLine", "LineComment", "BlockComment", 
			"BoolConst", "IntConst", "StringConst", "Int", "Bool", "String", "Null", 
			"Void", "True", "False", "If", "Else", "For", "While", "Break", "Continue", 
			"Return", "New", "Class", "This", "Identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'<'", 
			"'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'", "'++'", "'-'", "'--'", 
			"'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", 
			"'?'", "':'", "';'", "','", "'='", "'=='", "'!='", "'[&]'", null, null, 
			null, null, null, null, null, "'int'", "'bool'", "'string'", "'null'", 
			"'void'", "'true'", "'false'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'new'", "'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket", 
			"LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", 
			"LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus", 
			"Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", 
			"Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual", 
			"LambdaStart", "WhiteSpace", "NewLine", "LineComment", "BlockComment", 
			"BoolConst", "IntConst", "StringConst", "Int", "Bool", "String", "Null", 
			"Void", "True", "False", "If", "Else", "For", "While", "Break", "Continue", 
			"Return", "New", "Class", "This", "Identifier"
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


	public Mx_liteLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mx_lite.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u017b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3#\3$\3$\3$\3%\3%\3%\3%\3&\6&\u00d4\n&\r&\16&\u00d5\3&\3&\3\'\3\'"+
		"\5\'\u00dc\n\'\3\'\5\'\u00df\n\'\3\'\3\'\3(\3(\3(\3(\7(\u00e7\n(\f(\16"+
		"(\u00ea\13(\3(\3(\3)\3)\3)\3)\7)\u00f2\n)\f)\16)\u00f5\13)\3)\3)\3)\3"+
		")\3)\3*\3*\5*\u00fe\n*\3+\3+\7+\u0102\n+\f+\16+\u0105\13+\3+\5+\u0108"+
		"\n+\3,\3,\3,\3,\3,\3,\3,\3,\7,\u0112\n,\f,\16,\u0115\13,\3,\3,\3-\3-\3"+
		"-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61"+
		"\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67"+
		"\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\39\39\39\39\39\39\39\39\3"+
		"9\3:\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3"+
		">\3>\7>\u0177\n>\f>\16>\u017a\13>\4\u00f3\u0113\2?\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?\3\2\b"+
		"\4\2\13\f\"\"\4\2\f\f\17\17\3\2\63;\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\2"+
		"\u0187\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3"+
		"\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2"+
		"\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2"+
		"m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3"+
		"\2\2\2\2{\3\2\2\2\3}\3\2\2\2\5\u0080\3\2\2\2\7\u0082\3\2\2\2\t\u0084\3"+
		"\2\2\2\13\u0086\3\2\2\2\r\u0088\3\2\2\2\17\u008a\3\2\2\2\21\u008c\3\2"+
		"\2\2\23\u008e\3\2\2\2\25\u0090\3\2\2\2\27\u0093\3\2\2\2\31\u0095\3\2\2"+
		"\2\33\u0098\3\2\2\2\35\u009b\3\2\2\2\37\u009e\3\2\2\2!\u00a0\3\2\2\2#"+
		"\u00a3\3\2\2\2%\u00a5\3\2\2\2\'\u00a8\3\2\2\2)\u00aa\3\2\2\2+\u00ac\3"+
		"\2\2\2-\u00ae\3\2\2\2/\u00b0\3\2\2\2\61\u00b2\3\2\2\2\63\u00b5\3\2\2\2"+
		"\65\u00b8\3\2\2\2\67\u00ba\3\2\2\29\u00bc\3\2\2\2;\u00be\3\2\2\2=\u00c0"+
		"\3\2\2\2?\u00c2\3\2\2\2A\u00c4\3\2\2\2C\u00c6\3\2\2\2E\u00c8\3\2\2\2G"+
		"\u00cb\3\2\2\2I\u00ce\3\2\2\2K\u00d3\3\2\2\2M\u00de\3\2\2\2O\u00e2\3\2"+
		"\2\2Q\u00ed\3\2\2\2S\u00fd\3\2\2\2U\u0107\3\2\2\2W\u0109\3\2\2\2Y\u0118"+
		"\3\2\2\2[\u011c\3\2\2\2]\u0121\3\2\2\2_\u0128\3\2\2\2a\u012d\3\2\2\2c"+
		"\u0132\3\2\2\2e\u0137\3\2\2\2g\u013d\3\2\2\2i\u0140\3\2\2\2k\u0145\3\2"+
		"\2\2m\u0149\3\2\2\2o\u014f\3\2\2\2q\u0155\3\2\2\2s\u015e\3\2\2\2u\u0165"+
		"\3\2\2\2w\u0169\3\2\2\2y\u016f\3\2\2\2{\u0174\3\2\2\2}~\7/\2\2~\177\7"+
		"@\2\2\177\4\3\2\2\2\u0080\u0081\7\60\2\2\u0081\6\3\2\2\2\u0082\u0083\7"+
		"*\2\2\u0083\b\3\2\2\2\u0084\u0085\7+\2\2\u0085\n\3\2\2\2\u0086\u0087\7"+
		"]\2\2\u0087\f\3\2\2\2\u0088\u0089\7_\2\2\u0089\16\3\2\2\2\u008a\u008b"+
		"\7}\2\2\u008b\20\3\2\2\2\u008c\u008d\7\177\2\2\u008d\22\3\2\2\2\u008e"+
		"\u008f\7>\2\2\u008f\24\3\2\2\2\u0090\u0091\7>\2\2\u0091\u0092\7?\2\2\u0092"+
		"\26\3\2\2\2\u0093\u0094\7@\2\2\u0094\30\3\2\2\2\u0095\u0096\7@\2\2\u0096"+
		"\u0097\7?\2\2\u0097\32\3\2\2\2\u0098\u0099\7>\2\2\u0099\u009a\7>\2\2\u009a"+
		"\34\3\2\2\2\u009b\u009c\7@\2\2\u009c\u009d\7@\2\2\u009d\36\3\2\2\2\u009e"+
		"\u009f\7-\2\2\u009f \3\2\2\2\u00a0\u00a1\7-\2\2\u00a1\u00a2\7-\2\2\u00a2"+
		"\"\3\2\2\2\u00a3\u00a4\7/\2\2\u00a4$\3\2\2\2\u00a5\u00a6\7/\2\2\u00a6"+
		"\u00a7\7/\2\2\u00a7&\3\2\2\2\u00a8\u00a9\7,\2\2\u00a9(\3\2\2\2\u00aa\u00ab"+
		"\7\61\2\2\u00ab*\3\2\2\2\u00ac\u00ad\7\'\2\2\u00ad,\3\2\2\2\u00ae\u00af"+
		"\7(\2\2\u00af.\3\2\2\2\u00b0\u00b1\7~\2\2\u00b1\60\3\2\2\2\u00b2\u00b3"+
		"\7(\2\2\u00b3\u00b4\7(\2\2\u00b4\62\3\2\2\2\u00b5\u00b6\7~\2\2\u00b6\u00b7"+
		"\7~\2\2\u00b7\64\3\2\2\2\u00b8\u00b9\7`\2\2\u00b9\66\3\2\2\2\u00ba\u00bb"+
		"\7#\2\2\u00bb8\3\2\2\2\u00bc\u00bd\7\u0080\2\2\u00bd:\3\2\2\2\u00be\u00bf"+
		"\7A\2\2\u00bf<\3\2\2\2\u00c0\u00c1\7<\2\2\u00c1>\3\2\2\2\u00c2\u00c3\7"+
		"=\2\2\u00c3@\3\2\2\2\u00c4\u00c5\7.\2\2\u00c5B\3\2\2\2\u00c6\u00c7\7?"+
		"\2\2\u00c7D\3\2\2\2\u00c8\u00c9\7?\2\2\u00c9\u00ca\7?\2\2\u00caF\3\2\2"+
		"\2\u00cb\u00cc\7#\2\2\u00cc\u00cd\7?\2\2\u00cdH\3\2\2\2\u00ce\u00cf\7"+
		"]\2\2\u00cf\u00d0\7(\2\2\u00d0\u00d1\7_\2\2\u00d1J\3\2\2\2\u00d2\u00d4"+
		"\t\2\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\b&\2\2\u00d8L\3\2\2\2\u00d9"+
		"\u00db\7\17\2\2\u00da\u00dc\7\f\2\2\u00db\u00da\3\2\2\2\u00db\u00dc\3"+
		"\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00df\7\f\2\2\u00de\u00d9\3\2\2\2\u00de"+
		"\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\b\'\2\2\u00e1N\3\2\2\2"+
		"\u00e2\u00e3\7\61\2\2\u00e3\u00e4\7\61\2\2\u00e4\u00e8\3\2\2\2\u00e5\u00e7"+
		"\n\3\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\b("+
		"\2\2\u00ecP\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00ef\7,\2\2\u00ef\u00f3"+
		"\3\2\2\2\u00f0\u00f2\13\2\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f5\3\2\2\2"+
		"\u00f3\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f6\u00f7\7,\2\2\u00f7\u00f8\7\61\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\b)\2\2\u00faR\3\2\2\2\u00fb\u00fe\5c\62\2\u00fc\u00fe\5e\63\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00feT\3\2\2\2\u00ff\u0103\t\4\2\2"+
		"\u0100\u0102\t\5\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101"+
		"\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0108\3\2\2\2\u0105\u0103\3\2\2\2\u0106"+
		"\u0108\7\62\2\2\u0107\u00ff\3\2\2\2\u0107\u0106\3\2\2\2\u0108V\3\2\2\2"+
		"\u0109\u0113\7$\2\2\u010a\u010b\7^\2\2\u010b\u0112\7p\2\2\u010c\u010d"+
		"\7^\2\2\u010d\u0112\7^\2\2\u010e\u010f\7^\2\2\u010f\u0112\7$\2\2\u0110"+
		"\u0112\13\2\2\2\u0111\u010a\3\2\2\2\u0111\u010c\3\2\2\2\u0111\u010e\3"+
		"\2\2\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0114\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7$"+
		"\2\2\u0117X\3\2\2\2\u0118\u0119\7k\2\2\u0119\u011a\7p\2\2\u011a\u011b"+
		"\7v\2\2\u011bZ\3\2\2\2\u011c\u011d\7d\2\2\u011d\u011e\7q\2\2\u011e\u011f"+
		"\7q\2\2\u011f\u0120\7n\2\2\u0120\\\3\2\2\2\u0121\u0122\7u\2\2\u0122\u0123"+
		"\7v\2\2\u0123\u0124\7t\2\2\u0124\u0125\7k\2\2\u0125\u0126\7p\2\2\u0126"+
		"\u0127\7i\2\2\u0127^\3\2\2\2\u0128\u0129\7p\2\2\u0129\u012a\7w\2\2\u012a"+
		"\u012b\7n\2\2\u012b\u012c\7n\2\2\u012c`\3\2\2\2\u012d\u012e\7x\2\2\u012e"+
		"\u012f\7q\2\2\u012f\u0130\7k\2\2\u0130\u0131\7f\2\2\u0131b\3\2\2\2\u0132"+
		"\u0133\7v\2\2\u0133\u0134\7t\2\2\u0134\u0135\7w\2\2\u0135\u0136\7g\2\2"+
		"\u0136d\3\2\2\2\u0137\u0138\7h\2\2\u0138\u0139\7c\2\2\u0139\u013a\7n\2"+
		"\2\u013a\u013b\7u\2\2\u013b\u013c\7g\2\2\u013cf\3\2\2\2\u013d\u013e\7"+
		"k\2\2\u013e\u013f\7h\2\2\u013fh\3\2\2\2\u0140\u0141\7g\2\2\u0141\u0142"+
		"\7n\2\2\u0142\u0143\7u\2\2\u0143\u0144\7g\2\2\u0144j\3\2\2\2\u0145\u0146"+
		"\7h\2\2\u0146\u0147\7q\2\2\u0147\u0148\7t\2\2\u0148l\3\2\2\2\u0149\u014a"+
		"\7y\2\2\u014a\u014b\7j\2\2\u014b\u014c\7k\2\2\u014c\u014d\7n\2\2\u014d"+
		"\u014e\7g\2\2\u014en\3\2\2\2\u014f\u0150\7d\2\2\u0150\u0151\7t\2\2\u0151"+
		"\u0152\7g\2\2\u0152\u0153\7c\2\2\u0153\u0154\7m\2\2\u0154p\3\2\2\2\u0155"+
		"\u0156\7e\2\2\u0156\u0157\7q\2\2\u0157\u0158\7p\2\2\u0158\u0159\7v\2\2"+
		"\u0159\u015a\7k\2\2\u015a\u015b\7p\2\2\u015b\u015c\7w\2\2\u015c\u015d"+
		"\7g\2\2\u015dr\3\2\2\2\u015e\u015f\7t\2\2\u015f\u0160\7g\2\2\u0160\u0161"+
		"\7v\2\2\u0161\u0162\7w\2\2\u0162\u0163\7t\2\2\u0163\u0164\7p\2\2\u0164"+
		"t\3\2\2\2\u0165\u0166\7p\2\2\u0166\u0167\7g\2\2\u0167\u0168\7y\2\2\u0168"+
		"v\3\2\2\2\u0169\u016a\7e\2\2\u016a\u016b\7n\2\2\u016b\u016c\7c\2\2\u016c"+
		"\u016d\7u\2\2\u016d\u016e\7u\2\2\u016ex\3\2\2\2\u016f\u0170\7v\2\2\u0170"+
		"\u0171\7j\2\2\u0171\u0172\7k\2\2\u0172\u0173\7u\2\2\u0173z\3\2\2\2\u0174"+
		"\u0178\t\6\2\2\u0175\u0177\t\7\2\2\u0176\u0175\3\2\2\2\u0177\u017a\3\2"+
		"\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179|\3\2\2\2\u017a\u0178"+
		"\3\2\2\2\16\2\u00d5\u00db\u00de\u00e8\u00f3\u00fd\u0103\u0107\u0111\u0113"+
		"\u0178\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}