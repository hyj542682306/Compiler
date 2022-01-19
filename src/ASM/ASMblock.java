package ASM;

import ASM.inst.Inst;

import java.util.ArrayList;

public class ASMblock {
    public String name;
    public ArrayList<Inst> instList;

    public ASMblock(String _name) {
        name = _name;
        instList = new ArrayList<>();
    }

    public void addInst(Inst x) {
        instList.add(x);
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
