package IR.inst;

import IR.type.*;
import IR.operand.*;

public class binary extends Inst{
    public String inst;
    public Operand result,op1,op2;
    public Type ty;

    public binary(Operand _result, String _inst, Type _ty, Operand _op1, Operand _op2){
        super();
        result=_result;inst=_inst;ty=_ty;op1=_op1;op2=_op2;
    }
}
