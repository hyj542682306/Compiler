package ASM.operand;

public class string extends ASMOperand {
    public String name, str;
    public int size;

    public string(String _name, String _str) {
        name = _name;
        str = _str.replace("\\5C", "\\\\")
                .replace("\\0A", "\\n")
                .replace("\\22", "\\\"")
                .replace("\\00", "");
        size = str.length();
    }

    @Override
    public String toString() {
        return "\t.type\t" + name + ",@object\n" +
                "\t.section\t.rodata\n" +
                name + ":\n" +
                "\t.asciz\t" + "\"" + str + "\"" + "\n" +
                "\t.size\t" + name + ", " + size;
    }
}
