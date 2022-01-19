package ASM.operand;

public class globalRegister extends ASMregister {
    public int size, value;

    public globalRegister(String _name, int _size, int _value) {
        super(_name);
        size = _size;
        value = _value;
    }

    @Override
    public String toString() {
        return "\t.type\t" + name + ",@object\n" +
                "\t.section\t.sbss\n" +
                "\t.globl\t" + name + "\n" +
                "\t.p2align\t2\n" +
                name + ":\n" +
                "\t.word\t" + value + "\n" +
                "\t.size\t" + name + ", " + size;
    }
}