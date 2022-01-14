package IR.inst;

import IR.*;
import IR.operand.*;

import javax.swing.plaf.OptionPaneUI;

public class br extends Inst {
    public Operand cond = null;
    public basicblock iftrue, iffalse;

    public br(basicblock _inblock, basicblock _dest) {
        super(_inblock, null);
        iftrue = _dest;
    }

    public br(basicblock _inblock, Operand _cond, basicblock _iftrue, basicblock _iffalse) {
        super(_inblock, null);
        cond = _cond;
        iftrue = _iftrue;
        iffalse = _iffalse;
    }

    @Override
    public void accept(IRvisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        if (cond == null)
            return "br label %" + iftrue.name;
        else
            return "br i1 " + cond.toString() + ", label %" + iftrue.name + ", label %" + iffalse.name;
    }
}
