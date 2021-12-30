package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class alloca extends Inst {
    public IRType ty;

    public alloca(basicblock _inblock, register _result, IRType _ty){
        super(_inblock,_result);
        ty=_ty;
    }
}
