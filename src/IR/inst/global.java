package IR.inst;

import IR.basicblock;
import IR.operand.Operand;
import IR.operand.register;
import IR.type.IRType;

public class global extends Inst{
    public IRType ty;
    public Operand value;

    public global(basicblock _inblock, register _result,IRType _ty,Operand _value){
        super(_inblock,_result);
        ty=_ty;value=_value;
    }
}
