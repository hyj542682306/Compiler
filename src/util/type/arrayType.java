package util.type;

import IR.type.IRType;

public class arrayType extends Type {
    public Type type;
    public int dim;
    public IRType irType=null;

    public arrayType(Type _type, int _dim) {
        type = _type;
        dim = _dim;
    }

    @Override
    public boolean typeEqual(Type t) {
        if (t.isNull()) return true;
        return (t instanceof arrayType) && (type.typeEqual(((arrayType) t).type)) && (dim == ((arrayType) t).dim);
    }

    @Override
    public IRType getIRType() {
        if (irType==null)
            irType=new IR.type.arrayType(dim,type.getIRType());
        return irType;
    }
}
