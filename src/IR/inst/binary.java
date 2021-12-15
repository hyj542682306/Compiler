package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class binary extends Inst{
    public String inst;
    public Operand op1,op2;
    public Type ty;

    public binary(basicblock _inblock, register _result, String _inst, Type _ty, Operand _op1, Operand _op2){
        super(_inblock,_result);
        inst=_inst;ty=_ty;op1=_op1;op2=_op2;
    }
}
