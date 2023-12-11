package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value{
    boolean val;
    public BoolValue(boolean v) {
        val = v;
    }
    public boolean getVal() {
        return val;
    }
    public String toString() {
        return val ? "true" : "false";
    }
    @Override
    public Type getType() {
        return new BoolType();
    }

    public boolean equals(Object another) {
        if (another instanceof BoolValue) {
            return ((BoolValue) another).getVal() == val;
        }
        return false;
    }
}
