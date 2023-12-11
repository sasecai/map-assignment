package model.stmt;

import exception.MyException;
import model.containers.MyIStack;
import model.PrgState;

public class CompoundStmt implements IStmt {
    IStmt first, second;
    public CompoundStmt(IStmt f, IStmt s) {
        first = f;
        second = s;
    }
    public String toStr() throws MyException {
//        return "Compound statement: (" + first.toStr() + ";" + second.toStr() + ")";
        return first.toStr() + ";" + second.toStr();
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        stk.push(second);
        stk.push(first);
        return state;
    }
}
