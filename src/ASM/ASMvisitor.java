package ASM;

import ASM.inst.*;

public interface ASMvisitor {
    void visit(mv it);

    void visit(arithmetic it);

    void visit(li it);

    void visit(j it);

    void visit(branch it);

    void visit(call it);

    void visit(compare it);

    void visit(la it);

    void visit(ret it);

    void visit(ASMmodule it);

    void visit(ASMfunction it);

    void visit(ASMblock it);

    void visit(sw it);

    void visit(lw it);
}
