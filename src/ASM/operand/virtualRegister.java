package ASM.operand;

public class virtualRegister extends ASMregister {
    public int size;

    public virtualRegister(String _name, int _size) {
        super(_name);
        size = _size;
    }

    @Override
    public String toString() {
        return "VR" + name;
    }
}
