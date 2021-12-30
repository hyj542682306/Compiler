package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class load extends Inst{
    public Operand pointer;
    public IRType ty;

    public load(basicblock _inblock, register _result, IRType _ty, Operand _pointer){
        super(_inblock,_result);
        ty=_ty;pointer=_pointer;
    }
}
