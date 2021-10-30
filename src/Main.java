import AST.*;
import frontend.*;
import util.Mx_liteErrorListener;
import util.scope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.Mx_liteLexer;
import parser.Mx_liteParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        String name = "test.mx";
//        InputStream input = System.in;
        FileInputStream input = new FileInputStream(name);
        try {
            progNode ASTRoot;
            scope globalScope = new scope(null);

            Mx_liteLexer lexer = new Mx_liteLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new Mx_liteErrorListener());

            Mx_liteParser parser = new Mx_liteParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new Mx_liteErrorListener());

            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            ASTRoot = (progNode) astBuilder.visit(parseTreeRoot);

            new SymbolCollector(globalScope).visit(ASTRoot);
            new TypeCollector(globalScope).visit(ASTRoot);
            globalScope.varMap.clear();
            new SemanticChecker(globalScope).visit(ASTRoot);
        } catch (Error error) {
            System.err.println(error.toString());
            throw new RuntimeException();
        }
    }
}
