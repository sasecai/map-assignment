package model.containers;

import exception.MyException;
import model.stmt.IStmt;

import java.util.ArrayList;

public class MyStack<T> implements MyIStack<T> {
    ArrayList<T> stack;
    public MyStack () {
        stack = new ArrayList<>();
    }
    @Override
    public T pop() throws MyException {
        if(stack.isEmpty())
            throw new MyException("Stack is empty");
        T firstElem = stack.getLast();
        stack.removeLast();
        return firstElem;
    }
    public T top() throws MyException {
        if(stack.isEmpty())
            throw new MyException("Stack is empty");
        T firstElem = stack.getLast();
        return firstElem;
    }

    @Override
    public void push(T v) {
        stack.add(v);
    }
    public int size() {
        return stack.size();
    }
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public String toStr() throws MyException {
        String s = "";
        for(int i = 0; i < stack.size(); i ++) {
            try{
                s += ((IStmt)(stack.get(i))).toStr();
            } catch (Exception e) {
                s += stack.get(i).toString();
            }
            s += ";";
        }
        return s;
    }
}
