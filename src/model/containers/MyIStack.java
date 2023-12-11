package model.containers;

import exception.MyException;

public interface MyIStack<T> {
    T pop() throws MyException;
    T top() throws MyException;
    void push(T v);
    int size();
    boolean isEmpty();
    String toStr() throws MyException;
}
