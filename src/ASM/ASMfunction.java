package ASM;

import ASM.operand.virtualRegister;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMfunction {
    public String name;
    public int offset;
    public ArrayList<ASMblock> blockList;
    public HashMap<virtualRegister, Integer> offMap;

    public ASMfunction(String _name) {
        name = _name;
        offset = 8;
        blockList = new ArrayList<>();
        offMap = new HashMap<>();
    }

    public void alloca(virtualRegister reg) {
        offset += reg.size;
        offMap.put(reg, offset);
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
