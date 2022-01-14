package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class store extends Inst {
    public IRType ty;
    public Operand value, pointer;

    public store(basicblock _inblock, IRType _ty, Operand _value, Operand _pointer) {
        super(_inblock, null);
        ty = _ty;
        value = _value;
        pointer = _pointer;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "store " + ty.toString() + " " + value.toString() + ", " + ty.toString() + "* " + pointer.toString();
    }
}
