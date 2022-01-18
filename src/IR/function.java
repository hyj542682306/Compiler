package IR;

import IR.inst.define;

import java.util.ArrayList;

public class function {
    public define funcDefine;
    public basicblock AllocaBlock;
    public ArrayList<basicblock> BlockList;

    public function() {
        AllocaBlock = new basicblock("ALLOCA",this);
        BlockList = new ArrayList<>();
    }

    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }
}
