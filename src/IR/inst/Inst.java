package IR.inst;

import AST.ASTvisitor;
import IR.*;
import IR.operand.*;

public abstract class Inst {
    public basicblock inblock;
    public register result;

    public Inst() {
    }

    public Inst(basicblock _inblock, register _result) {
        inblock = _inblock;
        result = _result;
    }

    public abstract void accept(IRvisitor visitor);

    public abstract String toString();
}
