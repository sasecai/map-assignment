package model.expressions;

import exception.MyException;
import model.containers.MyIHeap;
import model.types.IntType;
import model.values.IntValue;
import model.containers.MyIDictionary;
import model.values.Value;

public class ArithExp implements Exp{
    Exp e1, e2;
    public ArithExp(Exp ex1, Exp ex2, ExpType type) {
        e1 = ex1; e2 = ex2; op = type;
    }
    public enum ExpType {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }
    ExpType op;


    @Override
    public String toStr() throws MyException{
        switch (op) {
            case PLUS -> {
                return e1.toStr() + "+" + e2.toStr();
            }
            case MINUS -> {
                return e1.toStr() + "-" + e2.toStr();
            }
            case MULTIPLY -> {
                return e1.toStr() + "*" + e2.toStr();
            }
            case DIVIDE -> {
                return e1.toStr() + "/" + e2.toStr();
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
        if(!v1.getType().equals(new IntType()))
            throw new MyException("First operand is not an integer");
        if(!v2.getType().equals(new IntType()))
            throw new MyException("Second operand is not an integer");
        IntValue i1 = (IntValue)v1;
        IntValue i2 = (IntValue)v2;
        int n1 = i1.getVal(), n2 = i2.getVal();
        switch (op) {
            case PLUS -> {
                return new IntValue(n1+n2);
            }
            case MINUS -> {
                return new IntValue(n1-n2);
            }
            case MULTIPLY -> {
                return new IntValue(n1*n2);
            }
            case DIVIDE -> {
                if(n2 == 0)
                    throw new MyException("Arithmetic exception: division by 0");
                return new IntValue(n1/n2);
            }
            case null, default -> {
                throw new MyException("No operation expression");
            }
        }
    }
}
