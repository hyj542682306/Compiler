package util.type;

import IR.type.IRType;
import IR.type.intType;
import IR.type.pointerType;
import IR.type.voidType;

public class literalType extends Type {
    public String id;
    public IRType irType = null;

    public literalType(String _id) {
        id = _id;
    }

    @Override
    public boolean isInt() {
        return id.equals("int");
    }

    @Override
    public boolean isBool() {
        return id.equals("bool");
    }

    @Override
    public boolean isString() {
        return id.equals("string");
    }

    @Override
    public boolean isNull() {
        return id.equals("null");
    }

    @Override
    public boolean isVoid() {
        return id.equals("void");
    }

    @Override
    public boolean typeEqual(Type t) {
        if (this.isNull())
            return t.isNull() || (t instanceof arrayType) || (t instanceof classType);
        else
            return (t instanceof literalType) && (id.equals(((literalType) t).id));
    }

    @Override
    public IRType getIRType() {
        if (irType == null) {
            if (isInt())
                irType = new intType(32);
            else if (isBool())
                irType = new intType(1);
            else if (isString())
                irType = new pointerType(new intType(8));
            else if (isVoid())
                irType = new voidType();
        }
        return irType;
    }
}
