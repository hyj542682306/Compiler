package IR.operand;

import IR.type.*;

public abstract class Operand {
    public IRType type;

    public Operand(){}
    public Operand(IRType _type){
        type=_type;
    }
}
