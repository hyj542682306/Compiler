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
    private String tab = "    ";

    public IRPrinter(String path) throws FileNotFoundException {
        file_print = new PrintWriter(new FileOutputStream(path));
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
        file_print.println(tab+it.toString());
    }
}
