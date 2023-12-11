package model.expressions;

import exception.MyException;
import model.containers.MyIHeap;
import model.types.BoolType;
import model.values.BoolValue;
import model.containers.MyIDictionary;
import model.values.Value;

public class LogicExp implements Exp{
    Exp e1, e2;
    public LogicExp(Exp ex1, Exp ex2, LogicExp.LogicExpType type) {
        e1 = ex1; e2 = ex2; op = type;
    }
    public enum LogicExpType {
        AND, OR, XOR;
    }
    LogicExpType op;
    @Override
    public String toStr() throws MyException{
        switch (op) {
            case AND -> {
                return e1.toStr() + " AND " + e2.toStr();
            }
            case OR -> {
                return e1.toStr() + " OR " + e2.toStr();
            }
            case XOR -> {
                return e1.toStr() + " XOR " + e2.toStr();
            }
            case null, default -> {
                throw new MyException("No operator");
            }
        }
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        v2 = e2.eval(tbl, hp);
        if(!v1.getType().equals(new BoolType()))
            throw new MyException("First operand is not a boolean");
        if(!v2.getType().equals(new BoolType()))
            throw new MyException("Second operand is not a boolean");
        BoolValue b1 = (BoolValue) v1;
        BoolValue b2 = (BoolValue)v2;
        boolean n1 = b1.getVal(), n2 = b2.getVal();
        switch (op) {
            case AND -> {
                return new BoolValue(n1&&n2);
            }
            case OR -> {
                return new BoolValue(n1||n2);
            }
            case XOR -> {
                return new BoolValue(n1^n2);
            }
            case null, default -> {
                throw new MyException("No operation expression");
            }
        }
    }
}
