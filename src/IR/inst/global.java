package IR.inst;

import IR.IRvisitor;
import IR.basicblock;
import IR.operand.Operand;
import IR.operand.globalVariable;
import IR.operand.register;
import IR.operand.stringConst;
import IR.type.IRType;

public class global extends Inst {
    public globalVariable var;
    public IRType ty;
    public Operand value;

    public global(globalVariable _var, IRType _ty, Operand _value) {
        var = _var;
        ty = _ty;
        value = _value;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (value instanceof stringConst)
            return var.toString() + " = constant [" + Integer.toString(((stringConst) value).str.length()) + " x i8] c\"" + value.toString() + "\"";
        else
            return var.toString() + " = global " + ty.toString() + " " + value.toString();
    }
}
