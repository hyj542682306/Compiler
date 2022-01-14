package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class ret extends Inst {
    public IRType ty = null;
    public Operand value = null;

    public ret(basicblock _inblock) {
        super(_inblock, null);
    }

    public ret(basicblock _inblock, IRType _ty, Operand _value) {
        super(_inblock, null);
        ty = _ty;
        value = _value;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (ty instanceof voidType || ty == null)
            return "ret void";
        else
            return "ret " + ty.toString() + " " + value.toString();
    }
}
