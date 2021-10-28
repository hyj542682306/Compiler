package util;

public abstract class Type {
    public boolean isInt() {
        return false;
    }

    public boolean isBool() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isVoid() {
        return false;
    }

    public boolean typeEqual(Type t) {
        return false;
    }
}
