package model.stmt;

import exception.MyException;
import model.PrgState;
import model.expressions.Exp;
import model.types.StringType;
import model.values.StringValue;

public class closeRFile implements IStmt{
    public Exp expr;
    public closeRFile(Exp e) {
        expr = e;
    }
    @Override
    public String toStr() throws MyException {
        return "closeRFile(" + expr.toStr() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(expr.eval(state.getSymTable(), state.getHeap()).getType().equals(new StringType())) {
            StringValue strVal = (StringValue) expr.eval(state.getSymTable(), state.getHeap());
            if(state.getFileTable().isDefined(strVal)) {
                try{
                    state.getFileTable().access(strVal).close();;
                    state.getFileTable().remove(strVal);
                } catch (Exception e) {
                    throw new MyException("closeRFile: file does not exist");
                }
            } else {
                throw new MyException("closeRFile: file is not opened");
            }
        } else {
            throw new MyException("closeRFile: expression is not a string");
        }
        return state;
    }
}
