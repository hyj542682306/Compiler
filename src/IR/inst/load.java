package IR.inst;

import IR.type.*;
import IR.operand.*;

public class load extends Inst{
    public Operand result,pointer;
    public Type ty;

    public load(Operand _result, Type _ty, Operand _pointer){
        super();
        result=_result;ty=_ty;pointer=_pointer;
    }
}
