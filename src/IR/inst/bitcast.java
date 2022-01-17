package IR.inst;

import IR.IRvisitor;
import IR.basicblock;
import IR.operand.Operand;
import IR.operand.register;
import IR.type.IRType;

public class bitcast extends Inst {
    public IRType oldty, newty;
    public Operand value;

    public bitcast(basicblock _inblock, register _result, IRType _oldty, Operand _value, IRType _newty) {
        super(_inblock, _result);
        oldty = _oldty;
        value = _value;
        newty = _newty;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return result.toString() + " = bitcast " + oldty.toString() + " " + value.toString() + " to " + newty.toString();
    }
}
