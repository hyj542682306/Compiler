package IR.inst;

import IR.IRvisitor;
import IR.basicblock;
import IR.operand.Operand;
import IR.operand.globalVariable;
import IR.operand.register;
import IR.operand.stringConst;
import IR.type.IRType;
import IR.type.classType;

public class global extends Inst {
    public globalVariable var;
    public IRType ty;
    public Operand value;

    public global(IRType _ty) {
        ty = _ty;
    }

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
        if (ty instanceof classType) {
            String res = ty.toString() + " = type { ";
            if (((classType) ty).typeList.size()>0) {
                res=res+((classType) ty).typeList.get(0).toString();
                for (int i=1;i<((classType) ty).typeList.size();++i)
                    res = res + ", "+((classType) ty).typeList.get(i).toString();
            }
            res = res + " }";
            return res;
        } else if (value instanceof stringConst)
            return var.toString() + " = constant [" + Integer.toString(((stringConst) value).str.length()) + " x i8] c\"" + value.toString() + "\"";
        else
            return var.toString() + " = global " + ty.toString() + " " + value.toString();
    }
}
