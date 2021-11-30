package IR.operand;

import IR.type.*;

public class stringConst extends Operand {
    public String str;

    public stringConst(Type _type,String _str){
        super(_type);
        str=_str;
    }
}
