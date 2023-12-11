package model.stmt;

import exception.MyException;
import model.expressions.Exp;
import model.PrgState;

public class PrintStmt implements IStmt{
    Exp expression;
    public PrintStmt(Exp expr) {
        expression = expr;
    }
    @Override
    public String toStr() throws MyException {
        return "print(" + expression.toStr() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.newOut(expression.eval(state.getSymTable(), state.getHeap()));
//        System.out.println(expression.eval(state.getSymTable()).toStr()); // ?
        return state;
    }
}
