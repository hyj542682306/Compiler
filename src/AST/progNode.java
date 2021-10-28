package AST;

import util.position;

import java.util.ArrayList;

public class progNode extends ASTNode {
    public ArrayList<ASTNode> List = new ArrayList<>();

    public progNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTvisitor visitor) {
        visitor.visit(this);
    }
}