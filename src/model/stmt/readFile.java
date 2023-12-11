package model.stmt;

import exception.MyException;
import model.PrgState;
import model.expressions.Exp;
import model.values.IntValue;
import model.values.StringValue;

public class readFile implements IStmt{
    public Exp expr;
    public String varName;
    public readFile(Exp e, String v) {
        expr = e;
        varName = v;
    }
    @Override
    public String toStr() throws MyException {
        return "Reading " + varName + " from " + expr.toStr();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(state.getSymTable().isDefined(varName)) {
            if(state.getFileTable().isDefined((StringValue) expr.eval(state.getSymTable(), state.getHeap()))) {
                StringValue fileName = (StringValue) expr.eval(state.getSymTable(), state.getHeap());
                try {
                    String line = state.getFileTable().access(fileName).readLine();
                    IntValue value = new IntValue(Integer.parseInt(line));
                    state.getSymTable().insert(varName, value);
                } catch (Exception e) {
                    throw new MyException("readFile: file is empty");
                }
            } else {
                throw new MyException("readFile: file is not opened");
            }
        } else {
            throw new MyException("readFile: variable is not defined");
        }
        return state;
    }
}
