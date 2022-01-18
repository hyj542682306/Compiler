package IR;

import IR.operand.register;

import java.util.HashMap;

public class IRscope {
    public HashMap<String, register> regMap;
    public IRscope pIRscope;

    public IRscope(IRscope _pIRscope){
        pIRscope=_pIRscope;
        regMap = new HashMap<>();
    }

    public boolean containsKey(String id){
        return regMap.containsKey(id);
    }

    public register get(String id){
        return regMap.get(id);
    }

    public void put(String id,register reg){
        regMap.put(id,reg);
    }
}
