package IR.type;

public class arrayType extends Type{
    public int arrayDim;
    public Type type;

    public arrayType(int _arrayDim,Type _type){
        super();
        arrayDim=_arrayDim;type=_type;
    }
}
