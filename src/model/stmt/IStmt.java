package model.stmt;

import exception.MyException;
import model.PrgState;

public interface IStmt {
    String toStr() throws MyException;
    PrgState execute(PrgState state) throws MyException;
}
