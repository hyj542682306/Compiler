package util.type;

import IR.type.IRType;

import java.util.HashMap;

public class classType extends Type {
    public String id;
    public HashMap<String, varSymbol> varMap = new HashMap<>();
    public HashMap<String, funcSymbol> funcMap = new HashMap<>();
    public funcSymbol struct = null;
    public IR.type.classType irType=null;

    public classType(String _id) {
        id = _id;
        irType=new IR.type.classType(_id);
    }

    @Override
    public boolean typeEqual(Type t) {
        if (t.isNull()) return true;
        return (t instanceof classType) && (id.equals(((classType) t).id));
    }

    @Override
    public IRType getIRType() {
        return irType;
    }
}
