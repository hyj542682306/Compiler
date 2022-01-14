package IR.type;

import java.util.ArrayList;

public class classType extends IRType {
    public String name;
    public ArrayList<IRType> typeList;

    public classType(String _name) {
        name = _name;
        typeList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "%class." + name;
    }
}
