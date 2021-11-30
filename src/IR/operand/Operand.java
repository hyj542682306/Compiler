package IR.operand;

import IR.type.*;

public abstract class Operand {
    public Type type;

    public Operand(){}
    public Operand(Type _type){
        type=_type;
    }
}
