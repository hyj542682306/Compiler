package IR.inst;

import IR.*;
import IR.type.*;
import IR.operand.*;

public class icmp extends Inst {
    public Operand op1, op2;
    public String cond;
    public IRType ty;

    public icmp(basicblock _inblock, register _result, String _cond, IRType _ty, Operand _op1, Operand _op2) {
        super(_inblock, _result);
        cond = _cond;
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
        return result.toString() + " = icmp " + cond + " " + ty.toString() + " " + op1.toString() + ", " + op2.toString();
    }
}
