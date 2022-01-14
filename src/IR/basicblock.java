package IR;

import IR.inst.*;

import java.util.ArrayList;

public class basicblock {
    public String name;
    public ArrayList<Inst> AllocaList, InstList;
    public Inst terminator = null;
    public function infunction;

    public basicblock(String _name, function _infunction) {
        name = _name;
        infunction = _infunction;
        AllocaList = new ArrayList<>();
        InstList = new ArrayList<>();
        infunction = null;
    }

    public void addInst(Inst _inst) {
        if (terminator != null) return;
        if (_inst instanceof br || _inst instanceof ret)
            terminator = _inst;
        else if (_inst instanceof alloca)
            AllocaList.add(_inst);
        else
            InstList.add(_inst);
    }

    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }
}
