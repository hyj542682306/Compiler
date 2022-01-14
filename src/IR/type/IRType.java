package IR.type;

public abstract class IRType {
    int dim = 0;

    public IRType() {
    }

    public IRType(int _dim) {
        dim = _dim;
    }

    public abstract String toString();
}
