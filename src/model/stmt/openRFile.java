package model.stmt;

import exception.MyException;
import model.PrgState;
import model.expressions.Exp;
import model.types.StringType;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class openRFile implements IStmt{
    public Exp expr;
    public openRFile(Exp e) {
        expr = e;
    }
    @Override
    public String toStr() throws MyException {
        return "openRFile(" + expr.toStr() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(expr.eval(state.getSymTable(), state.getHeap()).getType().equals(new StringType())) {
            if(state.getFileTable().isDefined( (StringValue) expr.eval(state.getSymTable(), state.getHeap())) ) {
                throw new MyException("openRFile: file already opened");
            }
            try{
                BufferedReader newFile = new BufferedReader(new FileReader(((StringValue) expr.eval(state.getSymTable(), state.getHeap())).getVal()));
                state.getFileTable().insert((StringValue) expr.eval(state.getSymTable(), state.getHeap()), newFile);
            } catch (Exception e) {
                throw new MyException("openRFile: file does not exist");
            }
        } else {
            throw new MyException("openRFile: expression is not a string");
        }
        return state;
    }
}
