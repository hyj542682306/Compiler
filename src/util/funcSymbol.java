package util;

import java.util.ArrayList;

public class funcSymbol {
    public Type type = null;
    public String id;
    public ArrayList<varSymbol> paraList = new ArrayList<>();

    public funcSymbol(String _id) {
        id = _id;
    }
}
