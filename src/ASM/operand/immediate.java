package ASM.operand;

public class immediate extends ASMOperand {
    public int value;

    public immediate(int _value) {
        value = _value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
