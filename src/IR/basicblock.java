package IR;

import IR.inst.*;

import java.util.ArrayList;

public class basicblock {
    public String name;
    public ArrayList<Inst> InstList;
    public Inst terminator;
    public function infunction;

    public basicblock(String _name,function _infunction){
        name=_name;infunction=_infunction;
        InstList=new ArrayList<>();
        infunction=null;
    }

    public void addInst(Inst _inst){
        if (_inst instanceof br||_inst instanceof ret)
            terminator=_inst;
        else
            InstList.add(_inst);
    }
}
