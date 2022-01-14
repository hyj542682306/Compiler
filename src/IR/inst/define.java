package IR.inst;

import IR.IRvisitor;
import IR.operand.register;
import IR.type.IRType;

import java.util.ArrayList;

public class define extends Inst {
    public IRType ty;
    public String name;
    public ArrayList<IRType> TypeList = new ArrayList<>();
    public ArrayList<register> RegList = new ArrayList<>();

    public define(IRType _ty, String _name) {
        ty = _ty;
        name = _name;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        String res = "define " + ty.toString() + " @" + name + "(";
        if (TypeList.size() > 0) {
            res = res + TypeList.get(0).toString() + " " + RegList.get(0).toString();
            for (int i = 1; i < TypeList.size(); i++)
                res = res + ", " + TypeList.get(i).toString() + " " + RegList.get(i).toString();
        }
        res = res + ") {";
        return res;
    }
}
