package IR.type;

public class voidType extends IRType {
    public voidType() {
        super();
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public int getSize() {
        return 0;
    }
}
