package IR.type;

public class arrayType extends IRType {
    public int arrayDim;
    public IRType type;

    public arrayType(int _arrayDim, IRType _type) {
        super();
        arrayDim = _arrayDim;
        type = _type;
    }

    @Override
    public String toString() {
        return "[" + Integer.toString(arrayDim) + " x " + type.toString() + "]";
    }

    @Override
    public int getSize() {
        return arrayDim * type.getSize();
    }
}
