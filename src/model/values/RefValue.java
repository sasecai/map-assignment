package model.values;

import model.types.RefType;
import model.types.Type;

public class RefValue implements Value{
    int address;
    Type typeOfVariable;
    public RefValue(int a, Type t) {
        address = a;
        typeOfVariable = t;
    }
    public int getAddress() {
        return address;
    }
    public void setAddress(int newAddress) { address = newAddress; }
    public String toString() {
        return "Ref(" + address + ", " + typeOfVariable.toString() + ")";
    }
    public Type getType() {
        return new RefType(typeOfVariable);
    }
    public boolean equals(Object another) {
        if (another instanceof RefValue) {
            return ((RefValue) another).getAddress() == address;
        }
        return false;
    }
}
