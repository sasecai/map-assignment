package model.types;

import com.sun.jdi.BooleanValue;
import model.values.BoolValue;
import model.values.IntValue;

public class BoolType implements Type{
    public boolean equals(Object another) {
        return another instanceof BoolType;
    }
    public String toString() { return "bool"; }
    public BoolValue defaultValue() {
        return new BoolValue(false);
    }
}
