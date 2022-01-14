package IR;

import IR.inst.global;

import java.util.ArrayList;

public class module {
    public ArrayList<global> GlobalList;
    public ArrayList<function> FuncList;

    public module() {
        GlobalList = new ArrayList<>();
        FuncList = new ArrayList<>();
    }

    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }
}
