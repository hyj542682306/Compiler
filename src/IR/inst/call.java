package IR.inst;

import IR.IRvisitor;
import IR.basicblock;
import IR.operand.Operand;
import IR.operand.register;
import IR.type.IRType;
import IR.type.voidType;

import java.util.ArrayList;
import java.util.Optional;

public class call extends Inst {
    public IRType ty;
    public String funcId;
    public ArrayList<Operand> paraList;

    public call(basicblock _inblock, register _result, IRType _ty, String _funcId, ArrayList<Operand> _paraList) {
        super(_inblock, _result);
        ty = _ty;
        funcId = _funcId;
        paraList = _paraList;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (ty instanceof voidType) {
            String res = "call void @" + funcId + "(";
            if (paraList.size() > 0) {
                res = res + paraList.get(0).type.toString().toString() + " " + paraList.get(0).toString();
                for (int i = 1; i < paraList.size(); ++i) {
                    res = res + ", " + paraList.get(i).type.toString() + " " + paraList.get(i).toString();
                }
            }
            res = res + ")";
            return res;
        } else {
            String res = result.toString() + " = call " + ty.toString() + " @" + funcId + "(";
            if (paraList.size() > 0) {
                res = res + paraList.get(0).type.toString() + " " + paraList.get(0).toString();
                for (int i = 1; i < paraList.size(); ++i) {
                    res = res + ", " + paraList.get(i).type.toString() + " " + paraList.get(i).toString();
                }
            }
            res = res + ")";
            return res;
        }
    }
}
