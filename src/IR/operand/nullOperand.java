package IR.operand;

import IR.type.*;

public class nullOperand extends Operand {
    public nullOperand(){
        super(new pointerType(new voidType()));
    }
}
