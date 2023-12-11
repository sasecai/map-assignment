package model.types;

import model.values.IntValue;

public class IntType implements Type{
    public boolean equals(Object another) {
        return another instanceof IntType;
    }
    public String toString() {
        return "int";
    }
    public IntValue defaultValue() {
        return new IntValue(0);
    }
}
