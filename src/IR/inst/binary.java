package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class binary extends Inst {
    public String inst;
    public Operand op1, op2;
    public IRType ty;

    public binary(basicblock _inblock, register _result, String _inst, IRType _ty, Operand _op1, Operand _op2) {
        super(_inblock, _result);
        inst = _inst;
        ty = _ty;
        op1 = _op1;
        op2 = _op2;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return result.toString() + " = " + inst + " " + ty.toString() + " " + op1.toString() + ", " + op2.toString();
    }
}
