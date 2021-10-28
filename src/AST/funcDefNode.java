package AST;

import util.position;

import java.util.ArrayList;

public class funcDefNode extends defNode {
    public typeNode type;
    public String id;
    public ArrayList<varDecStmtNode> paraList = new ArrayList<>();
    public suiteStmtNode suite;

    public funcDefNode(position _pos, typeNode _type, String _id, ArrayList<varDecStmtNode> _paraList, suiteStmtNode _suite) {
        super(_pos);
        type = _type;
        id = _id;
        paraList = _paraList;
        suite = _suite;
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}