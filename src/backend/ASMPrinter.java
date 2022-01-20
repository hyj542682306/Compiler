package backend;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.ASMvisitor;
import ASM.inst.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ASMPrinter implements ASMvisitor {
    public PrintWriter file_print;
    private final String tab = "   ";

    public ASMPrinter(String path) throws FileNotFoundException {
        file_print = new PrintWriter(new FileOutputStream(path));
    }

    @Override
    public void visit(mv it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(arithmetic it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(li it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(j it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(branch it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(call it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(compare it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(la it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(ret it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(ASMmodule it) {
        file_print.println(tab + ".text");
        file_print.println("");

        it.funcList.forEach(x -> x.accept(this));

        file_print.println("");
        it.globalList.forEach(x -> file_print.println(x.toString()));

        file_print.close();
    }

    @Override
    public void visit(ASMfunction it) {
        file_print.println(tab + ".globl" + tab + it.name);
        file_print.println(tab + ".p2align" + tab + 2);
        file_print.println(tab + ".type" + tab + it.name + ",@function");
        file_print.println(it.name + ":");
        it.blockList.forEach(x -> x.accept(this));
        file_print.println("");
    }

    @Override
    public void visit(ASMblock it) {
        file_print.println(it.name + ":");
        it.instList.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(sw it) {
        file_print.println(tab + it.toString());
    }

    @Override
    public void visit(lw it) {
        file_print.println(tab + it.toString());
    }
}
