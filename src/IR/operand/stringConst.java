package IR.operand;

import IR.type.*;

public class stringConst extends Operand {
    public String str;

    public stringConst(IRType _type, String _str){
        super(_type);
        str=_str;
    }
}
