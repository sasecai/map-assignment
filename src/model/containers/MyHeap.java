package model.containers;

import model.expressions.Exp;
import model.values.Value;

public class MyHeap implements MyIHeap{
    private MyIDictionary<Integer, Value> heap;
    private int freeAddress;

    public MyHeap() {
        heap = new MyDictionary<>();
        freeAddress = 1;
    }

    public int allocate(Value val) {
        heap.insert(freeAddress, val);
        freeAddress++;
        return freeAddress - 1;
    }

    @Override
    public void deallocate(int address) {
        heap.remove(address);
    }

    @Override
    public Value read(int address) {
        return heap.access(address);
    }

    @Override
    public void write(int address, Value value) {
        heap.insert(address, value);
    }

    @Override
    public String toString() {
        return heap.toStr();
    }

    @Override
    public boolean contains(int address) {
        return heap.isDefined(address);
    }

    @Override
    public void update(int address, Value value) {
        heap.insert(address, value);
    }

    @Override
    public void setContent(MyIDictionary<Integer, Value> newHeap) {
        heap = newHeap;
    }

    @Override
    public MyIDictionary<Integer, Value> getContent() {
        return heap;
    }

}
