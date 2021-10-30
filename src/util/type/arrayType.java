package util.type;

public class arrayType extends Type {
    public Type type;
    public int dim;

    public arrayType(Type _type, int _dim) {
        type = _type;
        dim = _dim;
    }

    @Override
    public boolean typeEqual(Type t) {
        if (t.isNull()) return true;
        return (t instanceof arrayType) && (type.typeEqual(((arrayType) t).type)) && (dim == ((arrayType) t).dim);
    }
}
