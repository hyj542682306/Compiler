package IR.inst;

import IR.*;
import IR.operand.*;

public abstract class Inst {
    public basicblock inblock;
    public register result;

    public Inst(basicblock _inblock, register _result){
        inblock=_inblock;result=_result;
    }
}
