package ASM.operand;

public class physicalRegister extends ASMregister {
    public physicalRegister(String _name) {
        super(_name);
    }

    @Override
    public String toString() {
        return name;
    }
}
