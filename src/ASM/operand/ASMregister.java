package ASM.operand;

public abstract class ASMregister extends ASMOperand {
    public String name;

    public ASMregister(String _name) {
        name = _name;
    }
}
