package model.stmt;

import exception.MyException;
import model.PrgState;

public class NopStmt implements IStmt{
    NopStmt() {}
    @Override
    public String toStr() throws MyException {
        return "nop statement";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }
}
