package model.expressions;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.values.RefValue;
import model.values.Value;

public class ReadHeap implements Exp{
    Exp expr;
    @Override
    public String toStr() throws MyException{
        return "rH(" + expr.toStr() + ")";
    }
    public ReadHeap(Exp exp) {
        expr = exp;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        Value val = expr.eval(tbl, hp);
        if(!(val instanceof RefValue))
            throw new MyException("Value is not RefValue");
        if(!hp.contains(((RefValue) val).getAddress()))
            throw new MyException("Address is not in heap");
        Value heapValue = hp.read(((RefValue) val).getAddress());
        return heapValue;
    }
}
