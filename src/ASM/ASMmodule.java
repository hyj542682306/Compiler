package ASM;

import ASM.operand.ASMOperand;
import ASM.operand.physicalRegister;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMmodule {
    public ArrayList<ASMOperand> globalList;
    public ArrayList<ASMfunction> funcList;
    public ArrayList<physicalRegister> phyRegList;
    public ArrayList<physicalRegister> caller;
    public ArrayList<physicalRegister> callee;
    public ArrayList<physicalRegister> icolors;
    public HashMap<physicalRegister, Integer> phyRegid;

    public ASMmodule() {
        globalList = new ArrayList<>();
        funcList = new ArrayList<>();
        phyRegList = new ArrayList<>();
        caller = new ArrayList<>();
        callee = new ArrayList<>();
        icolors = new ArrayList<>();
        phyRegid = new HashMap<>();

        physicalRegister now;

        phyRegList.add(new physicalRegister("zero"));
        phyRegList.add(new physicalRegister("ra"));
        phyRegList.add(new physicalRegister("sp"));
        phyRegList.add(new physicalRegister("gp"));
        phyRegList.add(new physicalRegister("tp"));
        for (int i = 0; i <= 2; ++i) {
            now = new physicalRegister("t" + i);
            phyRegList.add(now);
            caller.add(now);
            icolors.add(now);
        }
        for (int i = 0; i <= 1; ++i) {
            now = new physicalRegister("s" + i);
            phyRegList.add(now);
            callee.add(now);
            icolors.add(now);
        }
        for (int i = 0; i <= 7; ++i) {
            now = new physicalRegister("a" + i);
            phyRegList.add(now);
            caller.add(now);
            icolors.add(now);
        }
        for (int i = 2; i <= 11; ++i) {
            now = new physicalRegister("s" + i);
            phyRegList.add(now);
            callee.add(now);
            icolors.add(now);
        }
        for (int i = 3; i <= 6; ++i) {
            now = new physicalRegister("t" + i);
            phyRegList.add(now);
            caller.add(now);
            icolors.add(now);
        }

        for (int i = 0; i < 32; ++i)
            phyRegid.put(phyRegList.get(i), i);
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
