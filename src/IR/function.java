package IR;

import IR.inst.define;

import java.util.ArrayList;

public class function {
    public define funcDefine;
    public ArrayList<basicblock> BlockList;

    public function() {
        BlockList = new ArrayList<>();
    }

    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }
}
