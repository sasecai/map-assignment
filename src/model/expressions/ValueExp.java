package model.expressions;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.values.Value;

public class ValueExp implements Exp{
    Value e;
    public ValueExp(Value ex) {
        e = ex;
    }
    @Override
    public String toStr() throws MyException{
        return e.toString();
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        return e;
    }
}
