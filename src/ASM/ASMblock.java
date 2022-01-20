package ASM;

import ASM.inst.Inst;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASMblock {
    public String name;
    public LinkedList<Inst> instList;

    public ASMblock(String _name) {
        name = _name;
        instList = new LinkedList<>();
    }

    public void addInst(Inst x) {
        instList.add(x);
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
