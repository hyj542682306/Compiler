package IR.inst;

import IR.type.*;

public class alloca extends Inst {
    public String result;
    public Type ty;

    public alloca(String _result,Type _ty){
        super();
        result=_result;ty=_ty;
    }
}
