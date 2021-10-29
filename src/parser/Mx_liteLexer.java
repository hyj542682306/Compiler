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
    static {
        RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, Int = 2, Bool = 3, String = 4, Null = 5, Void = 6, True = 7, False = 8, If = 9,
            Else = 10, For = 11, While = 12, Break = 13, Continue = 14, Return = 15, New = 16, Class = 17,
            This = 18, Dot = 19, LeftParen = 20, RightParen = 21, LeftBracket = 22, RightBracket = 23,
            LeftBrace = 24, RightBrace = 25, Less = 26, LessEqual = 27, Greater = 28, GreaterEqual = 29,
            LeftShift = 30, RightShift = 31, Plus = 32, SelfPlus = 33, Minus = 34, SelfMinus = 35,
            Mul = 36, Div = 37, Mod = 38, And = 39, Or = 40, AndAnd = 41, OrOr = 42, Caret = 43, Not = 44,
            Tilde = 45, Question = 46, Colon = 47, Semi = 48, Comma = 49, Assign = 50, Equal = 51,
            NotEqual = 52, LambdaStart = 53, WhiteSpace = 54, NewLine = 55, LineComment = 56,
            BlockComment = 57, Identifier = 58, BoolConst = 59, IntConst = 60, StringConst = 61;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "T__0", "Int", "Bool", "String", "Null", "Void", "True", "False", "If",
                "Else", "For", "While", "Break", "Continue", "Return", "New", "Class",
                "This", "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket",
                "LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual",
                "LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus",
                "Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde",
                "Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual",
                "LambdaStart", "WhiteSpace", "NewLine", "LineComment", "BlockComment",
                "Identifier", "BoolConst", "IntConst", "StringConst"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'->'", "'int'", "'bool'", "'string'", "'null'", "'void'", "'true'",
                "'false'", "'if'", "'else'", "'for'", "'while'", "'break'", "'continue'",
                "'return'", "'new'", "'class'", "'this'", "'.'", "'('", "')'", "'['",
                "']'", "'{'", "'}'", "'<'", "'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'",
                "'++'", "'-'", "'--'", "'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'",
                "'^'", "'!'", "'~'", "'?'", "':'", "';'", "','", "'='", "'=='", "'!='",
                "'[&]'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, "Int", "Bool", "String", "Null", "Void", "True", "False",
                "If", "Else", "For", "While", "Break", "Continue", "Return", "New", "Class",
                "This", "Dot", "LeftParen", "RightParen", "LeftBracket", "RightBracket",
                "LeftBrace", "RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual",
                "LeftShift", "RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus",
                "Mul", "Div", "Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde",
                "Question", "Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual",
                "LambdaStart", "WhiteSpace", "NewLine", "LineComment", "BlockComment",
                "Identifier", "BoolConst", "IntConst", "StringConst"
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
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "Mx_lite.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u017b\b\1\4\2\t" +
                    "\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
                    "\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
                    "\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
                    "\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
                    "\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
                    ",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
                    "\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
                    "\4>\t>\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3" +
                    "\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3" +
                    "\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17" +
                    "\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20" +
                    "\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23" +
                    "\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31" +
                    "\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37" +
                    "\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3" +
                    "(\3(\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3" +
                    "\61\3\62\3\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3" +
                    "\66\3\67\6\67\u0130\n\67\r\67\16\67\u0131\3\67\3\67\38\38\58\u0138\n8" +
                    "\38\58\u013b\n8\38\38\39\39\39\39\79\u0143\n9\f9\169\u0146\139\39\39\3" +
                    ":\3:\3:\3:\7:\u014e\n:\f:\16:\u0151\13:\3:\3:\3:\3:\3:\3;\3;\7;\u015a" +
                    "\n;\f;\16;\u015d\13;\3<\3<\5<\u0161\n<\3=\3=\7=\u0165\n=\f=\16=\u0168" +
                    "\13=\3=\5=\u016b\n=\3>\3>\3>\3>\3>\3>\3>\3>\7>\u0175\n>\f>\16>\u0178\13" +
                    ">\3>\3>\4\u014f\u0176\2?\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f" +
                    "\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63" +
                    "\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62" +
                    "c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?\3\2\b\4\2\13\f\"\"\4\2\f\f\17\17" +
                    "\4\2C\\c|\6\2\62;C\\aac|\3\2\63;\3\2\62;\2\u0187\2\3\3\2\2\2\2\5\3\2\2" +
                    "\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21" +
                    "\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2" +
                    "\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3" +
                    "\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3" +
                    "\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3" +
                    "\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2" +
                    "\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2" +
                    "Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3" +
                    "\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2" +
                    "\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\3}\3\2\2\2\5" +
                    "\u0080\3\2\2\2\7\u0084\3\2\2\2\t\u0089\3\2\2\2\13\u0090\3\2\2\2\r\u0095" +
                    "\3\2\2\2\17\u009a\3\2\2\2\21\u009f\3\2\2\2\23\u00a5\3\2\2\2\25\u00a8\3" +
                    "\2\2\2\27\u00ad\3\2\2\2\31\u00b1\3\2\2\2\33\u00b7\3\2\2\2\35\u00bd\3\2" +
                    "\2\2\37\u00c6\3\2\2\2!\u00cd\3\2\2\2#\u00d1\3\2\2\2%\u00d7\3\2\2\2\'\u00dc" +
                    "\3\2\2\2)\u00de\3\2\2\2+\u00e0\3\2\2\2-\u00e2\3\2\2\2/\u00e4\3\2\2\2\61" +
                    "\u00e6\3\2\2\2\63\u00e8\3\2\2\2\65\u00ea\3\2\2\2\67\u00ec\3\2\2\29\u00ef" +
                    "\3\2\2\2;\u00f1\3\2\2\2=\u00f4\3\2\2\2?\u00f7\3\2\2\2A\u00fa\3\2\2\2C" +
                    "\u00fc\3\2\2\2E\u00ff\3\2\2\2G\u0101\3\2\2\2I\u0104\3\2\2\2K\u0106\3\2" +
                    "\2\2M\u0108\3\2\2\2O\u010a\3\2\2\2Q\u010c\3\2\2\2S\u010e\3\2\2\2U\u0111" +
                    "\3\2\2\2W\u0114\3\2\2\2Y\u0116\3\2\2\2[\u0118\3\2\2\2]\u011a\3\2\2\2_" +
                    "\u011c\3\2\2\2a\u011e\3\2\2\2c\u0120\3\2\2\2e\u0122\3\2\2\2g\u0124\3\2" +
                    "\2\2i\u0127\3\2\2\2k\u012a\3\2\2\2m\u012f\3\2\2\2o\u013a\3\2\2\2q\u013e" +
                    "\3\2\2\2s\u0149\3\2\2\2u\u0157\3\2\2\2w\u0160\3\2\2\2y\u016a\3\2\2\2{" +
                    "\u016c\3\2\2\2}~\7/\2\2~\177\7@\2\2\177\4\3\2\2\2\u0080\u0081\7k\2\2\u0081" +
                    "\u0082\7p\2\2\u0082\u0083\7v\2\2\u0083\6\3\2\2\2\u0084\u0085\7d\2\2\u0085" +
                    "\u0086\7q\2\2\u0086\u0087\7q\2\2\u0087\u0088\7n\2\2\u0088\b\3\2\2\2\u0089" +
                    "\u008a\7u\2\2\u008a\u008b\7v\2\2\u008b\u008c\7t\2\2\u008c\u008d\7k\2\2" +
                    "\u008d\u008e\7p\2\2\u008e\u008f\7i\2\2\u008f\n\3\2\2\2\u0090\u0091\7p" +
                    "\2\2\u0091\u0092\7w\2\2\u0092\u0093\7n\2\2\u0093\u0094\7n\2\2\u0094\f" +
                    "\3\2\2\2\u0095\u0096\7x\2\2\u0096\u0097\7q\2\2\u0097\u0098\7k\2\2\u0098" +
                    "\u0099\7f\2\2\u0099\16\3\2\2\2\u009a\u009b\7v\2\2\u009b\u009c\7t\2\2\u009c" +
                    "\u009d\7w\2\2\u009d\u009e\7g\2\2\u009e\20\3\2\2\2\u009f\u00a0\7h\2\2\u00a0" +
                    "\u00a1\7c\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7u\2\2\u00a3\u00a4\7g\2\2" +
                    "\u00a4\22\3\2\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7h\2\2\u00a7\24\3\2\2" +
                    "\2\u00a8\u00a9\7g\2\2\u00a9\u00aa\7n\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ac" +
                    "\7g\2\2\u00ac\26\3\2\2\2\u00ad\u00ae\7h\2\2\u00ae\u00af\7q\2\2\u00af\u00b0" +
                    "\7t\2\2\u00b0\30\3\2\2\2\u00b1\u00b2\7y\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4" +
                    "\7k\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7g\2\2\u00b6\32\3\2\2\2\u00b7\u00b8" +
                    "\7d\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7c\2\2\u00bb" +
                    "\u00bc\7m\2\2\u00bc\34\3\2\2\2\u00bd\u00be\7e\2\2\u00be\u00bf\7q\2\2\u00bf" +
                    "\u00c0\7p\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7p\2\2" +
                    "\u00c3\u00c4\7w\2\2\u00c4\u00c5\7g\2\2\u00c5\36\3\2\2\2\u00c6\u00c7\7" +
                    "t\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7w\2\2\u00ca\u00cb" +
                    "\7t\2\2\u00cb\u00cc\7p\2\2\u00cc \3\2\2\2\u00cd\u00ce\7p\2\2\u00ce\u00cf" +
                    "\7g\2\2\u00cf\u00d0\7y\2\2\u00d0\"\3\2\2\2\u00d1\u00d2\7e\2\2\u00d2\u00d3" +
                    "\7n\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7u\2\2\u00d5\u00d6\7u\2\2\u00d6" +
                    "$\3\2\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7j\2\2\u00d9\u00da\7k\2\2\u00da" +
                    "\u00db\7u\2\2\u00db&\3\2\2\2\u00dc\u00dd\7\60\2\2\u00dd(\3\2\2\2\u00de" +
                    "\u00df\7*\2\2\u00df*\3\2\2\2\u00e0\u00e1\7+\2\2\u00e1,\3\2\2\2\u00e2\u00e3" +
                    "\7]\2\2\u00e3.\3\2\2\2\u00e4\u00e5\7_\2\2\u00e5\60\3\2\2\2\u00e6\u00e7" +
                    "\7}\2\2\u00e7\62\3\2\2\2\u00e8\u00e9\7\177\2\2\u00e9\64\3\2\2\2\u00ea" +
                    "\u00eb\7>\2\2\u00eb\66\3\2\2\2\u00ec\u00ed\7>\2\2\u00ed\u00ee\7?\2\2\u00ee" +
                    "8\3\2\2\2\u00ef\u00f0\7@\2\2\u00f0:\3\2\2\2\u00f1\u00f2\7@\2\2\u00f2\u00f3" +
                    "\7?\2\2\u00f3<\3\2\2\2\u00f4\u00f5\7>\2\2\u00f5\u00f6\7>\2\2\u00f6>\3" +
                    "\2\2\2\u00f7\u00f8\7@\2\2\u00f8\u00f9\7@\2\2\u00f9@\3\2\2\2\u00fa\u00fb" +
                    "\7-\2\2\u00fbB\3\2\2\2\u00fc\u00fd\7-\2\2\u00fd\u00fe\7-\2\2\u00feD\3" +
                    "\2\2\2\u00ff\u0100\7/\2\2\u0100F\3\2\2\2\u0101\u0102\7/\2\2\u0102\u0103" +
                    "\7/\2\2\u0103H\3\2\2\2\u0104\u0105\7,\2\2\u0105J\3\2\2\2\u0106\u0107\7" +
                    "\61\2\2\u0107L\3\2\2\2\u0108\u0109\7\'\2\2\u0109N\3\2\2\2\u010a\u010b" +
                    "\7(\2\2\u010bP\3\2\2\2\u010c\u010d\7~\2\2\u010dR\3\2\2\2\u010e\u010f\7" +
                    "(\2\2\u010f\u0110\7(\2\2\u0110T\3\2\2\2\u0111\u0112\7~\2\2\u0112\u0113" +
                    "\7~\2\2\u0113V\3\2\2\2\u0114\u0115\7`\2\2\u0115X\3\2\2\2\u0116\u0117\7" +
                    "#\2\2\u0117Z\3\2\2\2\u0118\u0119\7\u0080\2\2\u0119\\\3\2\2\2\u011a\u011b" +
                    "\7A\2\2\u011b^\3\2\2\2\u011c\u011d\7<\2\2\u011d`\3\2\2\2\u011e\u011f\7" +
                    "=\2\2\u011fb\3\2\2\2\u0120\u0121\7.\2\2\u0121d\3\2\2\2\u0122\u0123\7?" +
                    "\2\2\u0123f\3\2\2\2\u0124\u0125\7?\2\2\u0125\u0126\7?\2\2\u0126h\3\2\2" +
                    "\2\u0127\u0128\7#\2\2\u0128\u0129\7?\2\2\u0129j\3\2\2\2\u012a\u012b\7" +
                    "]\2\2\u012b\u012c\7(\2\2\u012c\u012d\7_\2\2\u012dl\3\2\2\2\u012e\u0130" +
                    "\t\2\2\2\u012f\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131" +
                    "\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\b\67\2\2\u0134n\3\2\2\2" +
                    "\u0135\u0137\7\17\2\2\u0136\u0138\7\f\2\2\u0137\u0136\3\2\2\2\u0137\u0138" +
                    "\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u013b\7\f\2\2\u013a\u0135\3\2\2\2\u013a" +
                    "\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\b8\2\2\u013dp\3\2\2\2\u013e" +
                    "\u013f\7\61\2\2\u013f\u0140\7\61\2\2\u0140\u0144\3\2\2\2\u0141\u0143\n" +
                    "\3\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144" +
                    "\u0145\3\2\2\2\u0145\u0147\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\b9" +
                    "\2\2\u0148r\3\2\2\2\u0149\u014a\7\61\2\2\u014a\u014b\7,\2\2\u014b\u014f" +
                    "\3\2\2\2\u014c\u014e\13\2\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2" +
                    "\u014f\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0152\3\2\2\2\u0151\u014f" +
                    "\3\2\2\2\u0152\u0153\7,\2\2\u0153\u0154\7\61\2\2\u0154\u0155\3\2\2\2\u0155" +
                    "\u0156\b:\2\2\u0156t\3\2\2\2\u0157\u015b\t\4\2\2\u0158\u015a\t\5\2\2\u0159" +
                    "\u0158\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2" +
                    "\2\2\u015cv\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0161\5\17\b\2\u015f\u0161" +
                    "\5\21\t\2\u0160\u015e\3\2\2\2\u0160\u015f\3\2\2\2\u0161x\3\2\2\2\u0162" +
                    "\u0166\t\6\2\2\u0163\u0165\t\7\2\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2" +
                    "\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u016b\3\2\2\2\u0168" +
                    "\u0166\3\2\2\2\u0169\u016b\7\62\2\2\u016a\u0162\3\2\2\2\u016a\u0169\3" +
                    "\2\2\2\u016bz\3\2\2\2\u016c\u0176\7$\2\2\u016d\u016e\7^\2\2\u016e\u0175" +
                    "\7p\2\2\u016f\u0170\7^\2\2\u0170\u0175\7^\2\2\u0171\u0172\7^\2\2\u0172" +
                    "\u0175\7$\2\2\u0173\u0175\13\2\2\2\u0174\u016d\3\2\2\2\u0174\u016f\3\2" +
                    "\2\2\u0174\u0171\3\2\2\2\u0174\u0173\3\2\2\2\u0175\u0178\3\2\2\2\u0176" +
                    "\u0177\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u0179\3\2\2\2\u0178\u0176\3\2" +
                    "\2\2\u0179\u017a\7$\2\2\u017a|\3\2\2\2\16\2\u0131\u0137\u013a\u0144\u014f" +
                    "\u015b\u0160\u0166\u016a\u0174\u0176\3\b\2\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}