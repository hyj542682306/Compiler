package IR.type;

public class intType extends IRType {
    public int wide;

    public intType(int _wide) {
        wide = _wide;
    }

    @Override
    public String toString() {
        return "i" + Integer.toString(wide);
    }
}
