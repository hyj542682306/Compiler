package backend;

import ASM.ASMblock;
import ASM.ASMfunction;
import ASM.ASMmodule;
import ASM.inst.*;
import ASM.operand.ASMregister;
import ASM.operand.immediate;
import ASM.operand.physicalRegister;
import ASM.operand.virtualRegister;

import java.util.ListIterator;

public class ASMRegisterAllocator {
    public ASMmodule Module;
    public ASMfunction nowFunction;
    public ASMblock nowBlock;
    public physicalRegister zero, sp, s0, t3, t4, t5, t6;
    public ListIterator<Inst> it;

    public ASMRegisterAllocator(ASMmodule _Module) {
        Module = _Module;
        zero = _Module.phyRegList.get(0);
        sp = _Module.phyRegList.get(2);
        s0 = _Module.phyRegList.get(8);
        t3 = _Module.phyRegList.get(28);
        t4 = _Module.phyRegList.get(29);
        t5 = _Module.phyRegList.get(30);
        t6 = _Module.phyRegList.get(31);
    }

    public physicalRegister allocate(virtualRegister vReg, physicalRegister pReg, boolean op) {
        if (!nowFunction.offMap.containsKey(vReg.name)) nowFunction.alloca(vReg);

        int off = -nowFunction.offMap.get(vReg.name);
        if (-2048 <= off && off <= 2047) {
            if (op) {
                it.previous();
                it.add(new lw(pReg, s0, new immediate(off), null));
                it.next();
            } else it.add(new sw(s0, pReg, new immediate(off)));
        } else {
            if (op) {
                it.previous();
                it.add(new li(t6, new immediate(off)));
                it.add(new arithmetic("add", t6, t6, s0, null));
                it.add(new lw(pReg, t6, new immediate(0), null));
                it.next();
            } else {
                it.add(new li(t6, new immediate(off)));
                it.add(new arithmetic("add", t6, t6, s0, null));
                it.add(new sw(t6, pReg, new immediate(0)));
            }
        }

        return pReg;
    }

    public void RegisterAllocate() {
        for (ASMfunction z : Module.funcList) {
            nowFunction = z;
            for (ASMblock y : nowFunction.blockList) {
                nowBlock = y;
                it = y.instList.listIterator(0);
                while (it.hasNext()) {
                    Inst x = it.next();
                    if (x.rs1 instanceof virtualRegister)
                        x.rs1 = allocate((virtualRegister) x.rs1, t3, true);
                    if (x.rs2 instanceof virtualRegister)
                        x.rs2 = allocate((virtualRegister) x.rs2, t4, true);
                    if (x.rd instanceof virtualRegister)
                        x.rd = allocate((virtualRegister) x.rd, t5, false);
                }
            }
        }
    }
}
