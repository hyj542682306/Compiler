package ASM;

import ASM.operand.ASMOperand;
import ASM.operand.physicalRegister;

import java.util.ArrayList;

public class ASMmodule {
    public ArrayList<ASMOperand> globalList;
    public ArrayList<ASMfunction> funcList;
    public ArrayList<physicalRegister> phyRegList;

    public ASMmodule() {
        globalList = new ArrayList<>();
        funcList = new ArrayList<>();
        phyRegList = new ArrayList<>();

        phyRegList.add(new physicalRegister("zero"));
        phyRegList.add(new physicalRegister("ra"));
        phyRegList.add(new physicalRegister("sp"));
        phyRegList.add(new physicalRegister("gp"));
        phyRegList.add(new physicalRegister("tp"));
        for (int i = 0; i <= 2; ++i)
            phyRegList.add(new physicalRegister("t" + i));
        for (int i = 0; i <= 1; ++i)
            phyRegList.add(new physicalRegister("s" + i));
        for (int i = 0; i <= 7; ++i)
            phyRegList.add(new physicalRegister("a" + i));
        for (int i = 2; i <= 11; ++i)
            phyRegList.add(new physicalRegister("s" + i));
        for (int i = 3; i <= 6; ++i)
            phyRegList.add(new physicalRegister("t" + i));
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
