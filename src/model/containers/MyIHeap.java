package model.containers;

import model.expressions.Exp;
import model.values.Value;

public interface MyIHeap {
    int allocate(Value val);
    void deallocate(int address);
    Value read(int address);
    void write(int address, Value value);
    String toString();
    boolean contains(int address);
    void update(int address, Value value);
    void setContent(MyIDictionary<Integer, Value> newHeap);
    MyIDictionary<Integer, Value> getContent();
}
