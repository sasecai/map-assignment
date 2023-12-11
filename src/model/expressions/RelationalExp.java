package model.expressions;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExp implements Exp{
    Exp e1, e2;
    String op;
    public RelationalExp(Exp ex1, Exp ex2, String o) {
        e1 = ex1;
        e2 = ex2;
        op = o;
    }
    @Override
    public String toStr() throws MyException {
        return e1.toStr() + op + e2.toStr();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        v2 = e2.eval(tbl, hp);
        if(v1.getType().equals(new IntType())) {
            if(v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                if(op.equals("<")) {
                    return new BoolValue(i1.getVal() < i2.getVal());
                } else if(op.equals("<=")) {
                    return new BoolValue(i1.getVal() <= i2.getVal());
                } else if(op.equals("==")) {
                    return new BoolValue(i1.getVal() == i2.getVal());
                } else if(op.equals("!=")) {
                    return new BoolValue(i1.getVal() != i2.getVal());
                } else if(op.equals(">")) {
                    return new BoolValue(i1.getVal() > i2.getVal());
                } else if(op.equals(">=")) {
                    return new BoolValue(i1.getVal() >= i2.getVal());
                } else {
                    throw new MyException("Invalid operator");
                }
            } else {
                throw new MyException("Second operand is not an integer");
            }
        } else {
            throw new MyException("First operand is not an integer");
        }
    }
}
