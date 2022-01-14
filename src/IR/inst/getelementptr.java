package IR.inst;

import IR.IRvisitor;
import IR.basicblock;
import IR.operand.Operand;
import IR.operand.register;
import IR.type.IRType;

import java.util.ArrayList;

public class getelementptr extends Inst{
    public IRType ty;
    public Operand value;
    public ArrayList<Operand> IdxList;

    public getelementptr(basicblock _inblock, register _result, IRType _ty, Operand _value, ArrayList<Operand> _IdxList){
        super(_inblock,_result);
        ty=_ty;value=_value;IdxList=_IdxList;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String res=result.toString()+" = getelementptr inbounds "+ty.toString()+", "+ty.toString()+"* "+value.toString();
        for (Operand x : IdxList) {
            res = res + ", " + x.type.toString() + " " + x.toString();
        }
        return res;
    }
}
