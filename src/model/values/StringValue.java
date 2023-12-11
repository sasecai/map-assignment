package model.values;

import model.types.StringType;
import model.types.Type;

public class StringValue implements Value {
    String val;

    public StringValue(String v) {
        val = v;
    }

    public String getVal() {
        return val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    public String toString() {
        return val;
    }
    public boolean equals(Object another) {
        if (another instanceof StringValue) {
            return ((StringValue) another).getVal().equals(val);
        }
        return false;
    }
}
