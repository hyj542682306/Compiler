package backend;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.ASMvisitor;
import ASM.inst.*;
import ASM.operand.immediate;
import ASM.operand.physicalRegister;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ASMPrinter implements ASMvisitor {
    public PrintWriter file_print;
    public physicalRegister ra, sp, s0, s2;
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
        ra = it.phyRegList.get(1);
        sp = it.phyRegList.get(2);
        s0 = it.phyRegList.get(8);
        s2 = it.phyRegList.get(18);

        file_print.println(tab + ".text");
        file_print.println("");

        it.funcList.forEach(x -> x.accept(this));

        file_print.println("");
        it.globalList.forEach(x -> {
            file_print.println(x.toString());
            file_print.println("");
        });

        file_print.close();
    }

    @Override
    public void visit(ASMfunction it) {
        file_print.println(tab + ".globl" + tab + it.name);
        file_print.println(tab + ".p2align" + tab + 2);
        file_print.println(tab + ".type" + tab + it.name + ",@function");
        file_print.println(it.name + ":");

        int off = it.offset + it.calloff;

        ASMblock firstBlock = it.blockList.get(0);
        ASMblock lastBlock = it.blockList.get(it.blockList.size() - 1);
        if (-2024 <= off && off <= 2023) {
            //first
            firstBlock.instList.addFirst(new arithmetic("add", s0, sp, null, new immediate(off + 12)));
            firstBlock.instList.addFirst(new sw(sp, s0, new immediate(off + 4)));
            firstBlock.instList.addFirst(new sw(sp, ra, new immediate(off + 8)));
            firstBlock.instList.addFirst(new arithmetic("add", sp, sp, null, new immediate(-off - 12)));

            //last
            lastBlock.addInst(new lw(s0, sp, new immediate(off + 4), null));
            lastBlock.addInst(new lw(ra, sp, new immediate(off + 8), null));
            lastBlock.addInst(new arithmetic("add", sp, sp, null, new immediate(off + 12)));
        } else {
            //first
            firstBlock.instList.addFirst(new arithmetic("add", s0, sp, s2, null));
            firstBlock.instList.addFirst(new li(s2, new immediate(off + 12)));
            firstBlock.instList.addFirst(new sw(s2, s0, new immediate(0)));
            firstBlock.instList.addFirst(new arithmetic("add", s2, s2, sp, null));
            firstBlock.instList.addFirst(new li(s2, new immediate(off + 4)));
            firstBlock.instList.addFirst(new sw(s2, ra, new immediate(0)));
            firstBlock.instList.addFirst(new arithmetic("add", s2, s2, sp, null));
            firstBlock.instList.addFirst(new li(s2, new immediate(off + 8)));
            firstBlock.instList.addFirst(new arithmetic("add", sp, sp, s2, null));
            firstBlock.instList.addFirst(new li(s2, new immediate(-off - 12)));

            //last
            lastBlock.addInst(new li(s2, new immediate(off + 4)));
            lastBlock.addInst(new arithmetic("add", s2, s2, sp, null));
            lastBlock.addInst(new lw(s0, s2, new immediate(0), null));
            lastBlock.addInst(new li(s2, new immediate(off + 8)));
            lastBlock.addInst(new arithmetic("add", s2, s2, sp, null));
            lastBlock.addInst(new lw(ra, s2, new immediate(0), null));
            lastBlock.addInst(new li(s2, new immediate(off + 12)));
            lastBlock.addInst(new arithmetic("add", sp, sp, s2, null));
        }

        lastBlock.addInst(new ret());

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
