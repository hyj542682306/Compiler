package util;

import AST.typeNode;
import util.error.semanticError;

import java.util.HashMap;

public class scope {
    public scope pScope;
    public HashMap<String, Type> typeMap = new HashMap<>();
    public HashMap<String, varSymbol> varMap = new HashMap<>();
    public HashMap<String, funcSymbol> funcMap = new HashMap<>();

    public scope(scope _pScope) {
        pScope = _pScope;
    }

    //check if the newVar/newFunc's name is the same as a type's name
    public boolean exist(String id, boolean up, position pos) {
        if (typeMap.containsKey(id)) return true;
        if (up && pScope != null) return pScope.exist(id, true, pos);
        return false;
    }

    public void newVar(String id, varSymbol symbol, position pos) {
        if (exist(id, true, pos)) throw new semanticError("Variable Conflict", pos);
        if (varMap.containsKey(id)) throw new semanticError("Variable Duplicate Definition", pos);
        varMap.put(id, symbol);
    }

    public void newFunc(String id, funcSymbol symbol, position pos) {
        if (exist(id, true, pos)) throw new semanticError("Function Conflict", pos);
        if (funcMap.containsKey(id)) throw new semanticError("Function Duplicate Definition", pos);
        funcMap.put(id, symbol);
    }

    public void newType(String id, Type type, position pos) {
        if (typeMap.containsKey(id)) throw new semanticError("Type Conflict", pos);
        typeMap.put(id, type);
    }

    public varSymbol varGet(String id, boolean up, position pos) {
        if (varMap.containsKey(id)) return varMap.get(id);
        if (up && pScope != null) return pScope.varGet(id, true, pos);
        throw new semanticError("Undefined Variable", pos);
    }

    public funcSymbol funcGet(String id, boolean up, position pos) {
        if (funcMap.containsKey(id)) return funcMap.get(id);
        if (pScope != null) return pScope.funcGet(id, true, pos);
        throw new semanticError("Undefined Function", pos);
    }

    public Type typeGet(typeNode type) {
        if(type.dim==0)return typeMap.get(type.type);
        else return new arrayType(typeMap.get(type.type),type.dim);
    }

}
