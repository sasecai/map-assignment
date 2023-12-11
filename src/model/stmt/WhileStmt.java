package model.stmt;

import exception.MyException;
import model.PrgState;
import model.expressions.Exp;

public class WhileStmt implements IStmt{
    Exp exp;
    IStmt stmt;
    public WhileStmt(Exp e, IStmt s) {
        exp = e;
        stmt = s;
    }

    @Override
    public String toStr() throws MyException {
        return "while(" + exp.toStr() + "){" + stmt.toStr() + "}";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(exp.eval(state.getSymTable(), state.getHeap()).getType().equals(new model.types.BoolType())) {
            if(exp.eval(state.getSymTable(), state.getHeap()).equals(new model.values.BoolValue(true))) {
                state.getStack().push(this);
                state.getStack().push(stmt);
            }
        }
        else
            throw new MyException("Expression is not of type BoolType");
        return state;
    }
}
