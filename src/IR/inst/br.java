package IR.inst;

import IR.*;
import IR.operand.*;

public class br extends Inst{
    public register cond=null;
    public basicblock iftrue,iffalse;

    public br(basicblock _inblock, basicblock _dest){
        super(_inblock,null);
        iftrue=_dest;
    }

    public br(basicblock _inblock, register _cond, basicblock _iftrue, basicblock _iffalse){
        super(_inblock,null);
        cond=_cond;iftrue=_iftrue;iffalse=_iffalse;
    }
}
