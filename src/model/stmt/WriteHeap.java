package model.stmt;

import exception.MyException;
import model.PrgState;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.expressions.Exp;
import model.values.RefValue;
import model.values.Value;

public class WriteHeap implements IStmt{
    String varName;
    Exp expr;
    @Override
    public String toStr() throws MyException {
        return "wH(" + varName + ", " + expr.toStr() + ")";
    }
    public WriteHeap(String vn, Exp e) {
        varName = vn;
        expr = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap hp = state.getHeap();

        if(!tbl.isDefined(varName))
            throw new MyException("variable name not defined");
        if(!hp.contains(((RefValue)(tbl.access(varName))).getAddress()))
            throw new MyException("variable not in heap");

        hp.write(((RefValue)(tbl.access(varName))).getAddress(), expr.eval(tbl, hp));
//        Value val = expr.eval(tbl, hp);
//        if(!(val instanceof RefValue))
//            throw new MyException("Value is not RefValue");
//        if(!hp.contains(((RefValue) val).getAddress()))
//            throw new MyException("Address is not in heap");

//        hp.write(((RefValue) val).getAddress()), );
//        Value heapValue = hp.read(((RefValue) val).getAddress());
//        return heapValue;

        return state;
    }
}
