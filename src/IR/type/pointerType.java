package IR.type;

public class pointerType extends Type{
    public Type type;

    public pointerType(Type _type){
        super(_type.dim+1);
        type=_type;
    }
}
