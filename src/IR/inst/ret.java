package IR.inst;

import IR.type.*;
import IR.operand.*;

public class ret extends Inst{
    public Type ty;
    public Operand value=null;

    public ret(){
        super();
    }

    public ret(Type _ty, Operand _value){
        super();
        ty=_ty;value=_value;
    }
}
