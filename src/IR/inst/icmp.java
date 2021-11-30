package IR.inst;

import IR.type.*;
import IR.operand.*;

public class icmp extends Inst{
    public Operand result,op1,op2;
    public String cond;
    public Type ty;

    public icmp(Operand _result, String _cond, Type _ty, Operand _op1, Operand _op2){
        super();
        result=_result;cond=_cond;ty=_ty;op1=_op1;op2=_op2;
    }
}
