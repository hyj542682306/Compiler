package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class store extends Inst{
    public Type ty;
    public Operand value,pointer;

    public store(basicblock _inblock, Type _ty, Operand _value, Operand _pointer){
        super(_inblock,null);
        ty=_ty;value=_value;pointer=_pointer;
    }
}
