package backend;

import IR.IRvisitor;
import IR.basicblock;
import IR.function;
import IR.inst.*;
import IR.module;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class IRPrinter implements IRvisitor {
    public PrintWriter file_print;
    private final String tab = "    ";

    public IRPrinter(String path) throws FileNotFoundException {
        file_print = new PrintWriter(new FileOutputStream(path));
        file_print.println("declare dso_local void @print(i8* %0)");
        file_print.println("declare dso_local void @println(i8* %0)");
        file_print.println("declare dso_local void @printInt(i32 %0)");
        file_print.println("declare dso_local void @printlnInt(i32 %0)");
        file_print.println("declare dso_local i32 @getInt()");
        file_print.println("declare dso_local i8* @toString(i32 %0)");
        file_print.println("declare dso_local i8* @getString()");
        file_print.println("declare dso_local i32 @_str_ord(i8* %0, i32 %1)");
        file_print.println("declare dso_local zeroext i1 @_str_eq(i8* %0, i8* %1)");
        file_print.println("declare dso_local zeroext i1 @_str_ne(i8* %0, i8* %1)");
        file_print.println("declare dso_local zeroext i1 @_str_lt(i8* %0, i8* %1)");
        file_print.println("declare dso_local zeroext i1 @_str_le(i8* %0, i8* %1)");
        file_print.println("declare dso_local zeroext i1 @_str_gt(i8* %0, i8* %1)");
        file_print.println("declare dso_local zeroext i1 @_str_ge(i8* %0, i8* %1)");
        file_print.println("declare dso_local i8* @_str_concatenate(i8* %0, i8* %1)");
        file_print.println("declare dso_local i8* @_f_malloc(i32 %0)");
        file_print.println("declare dso_local i32 @_str_length(i8* %0)");
        file_print.println("declare dso_local i8* @_str_substring(i8* %0, i32 %1, i32 %2)");
        file_print.println("declare dso_local i32 @_str_parseInt(i8* %0)");
        file_print.println("");
    }

    @Override
    public void visit(module it) {
        it.GlobalList.forEach(x -> x.accept(this));
        file_print.println("");
        it.FuncList.forEach(x -> x.accept(this));
        file_print.close();
    }

    @Override
    public void visit(function it) {
        visit(it.funcDefine);
        it.BlockList.forEach(x -> x.accept(this));
        file_print.println("}");
        file_print.println("");
    }

    @Override
    public void visit(basicblock it) {
        file_print.println(it.name + ":");
        it.AllocaList.forEach(x -> x.accept(this));
        it.InstList.forEach(x -> x.accept(this));
        it.terminator.accept(this);
        file_print.println("");
    }

    @Override
    public void visit(alloca it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(binary it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(br it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(define it) {
        file_print.println(it.toString());
    }

    @Override
    public void visit(global it) {
        file_print.println(it.toString());
    }

    @Override
    public void visit(icmp it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(load it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(ret it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(store it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(call it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(bitcast it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(getelementptr it) {
        file_print.println(tab + it.toString());
    }
}
