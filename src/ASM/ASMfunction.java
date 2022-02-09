package ASM;

import ASM.operand.virtualRegister;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMfunction {
    public String name;
    public int offset, calloff;
    public ArrayList<ASMblock> blockList;
    public HashMap<String, ASMblock> blockMap;
    public HashMap<String, Integer> offMap;

    public ASMfunction(String _name) {
        name = _name;
        offset = 8;
        calloff = 0;
        blockList = new ArrayList<>();
        blockMap = new HashMap<>();
        offMap = new HashMap<>();
    }

    public void alloca(virtualRegister reg) {
        offset += reg.size;
        offMap.put(reg.name, offset);
    }

    public void accept(ASMvisitor visitor) {
        visitor.visit(this);
    }
}
