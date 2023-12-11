package model.containers;

import exception.MyException;

public interface MyIList<T> {
    void add(T elem);
    void erase(int index);
    T get(int index);
    int size();
    String toStr() throws MyException;
}
