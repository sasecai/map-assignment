package model.values;

import model.types.IntType;
import model.types.Type;

public class IntValue implements Value{
    int val;
    public IntValue(int v) {
        val = v;
    }
    public int getVal() {
        return val;
    }
    public String toString() {
        return "" + val;
    }
    @Override
    public Type getType() {
        return new IntType();
    }
    public boolean equals(Object another) {
        if (another instanceof IntValue) {
            return ((IntValue) another).getVal() == val;
        }
        return false;
    }
}
