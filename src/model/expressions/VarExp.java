package model.expressions;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.values.Value;

public class VarExp implements Exp{
    String id;
    public VarExp(String vid) {
        id = vid;
    }
    @Override
    public String toStr() throws MyException{
//        return "Variable " + id;
        return id;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        return tbl.access(id);
    }
}
