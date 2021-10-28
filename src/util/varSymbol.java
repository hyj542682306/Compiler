package util;

public class varSymbol {
    public Type type;
    public String id;

    public varSymbol(String _id) {
        id = _id;
    }

    public varSymbol(Type _type, String _id) {
        type = _type;
        id = _id;
    }
}
