package IR.inst;

import IR.operand.*;

public class br extends Inst{
    public Operand cond=null;
    public String iftrue,iffalse;

    public br(String _dest){
        super();
        iftrue=_dest;
    }

    public br(Operand _cond, String _iftrue, String _iffalse){
        super();
        cond=_cond;iftrue=_iftrue;iffalse=_iffalse;
    }
}
