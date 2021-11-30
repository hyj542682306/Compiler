package IR.inst;

import IR.type.*;
import IR.operand.*;

public class store extends Inst{
    public Type ty;
    public Operand value,pointer;

    public store(Type _ty, Operand _value, Operand _pointer){
        ty=_ty;value=_value;pointer=_pointer;
    }
}
