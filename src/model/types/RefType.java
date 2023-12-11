package model.types;

import model.values.RefValue;
import model.values.Value;

public class RefType implements Type{
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return inner;
    }

    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public boolean equals(Object another) {

        if (another instanceof RefType) {
            return ((RefType) another).getInner().equals(inner);
        }
        return false;
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0, inner);
    }

}
