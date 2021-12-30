package IR.type;

public class pointerType extends IRType {
    public IRType type;

    public pointerType(IRType _type){
        super(_type.dim+1);
        type=_type;
    }
}
