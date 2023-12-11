package model.expressions;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.values.Value;

public interface Exp {
    String toStr() throws MyException;
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException;
}
