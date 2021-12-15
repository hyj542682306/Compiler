package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class ret extends Inst{
    public Type ty=null;
    public Operand value=null;

    public ret(basicblock _inblock){
        super(_inblock,null);
    }

    public ret(basicblock _inblock, Type _ty, Operand _value){
        super(_inblock,null);
        ty=_ty;value=_value;
    }
}
