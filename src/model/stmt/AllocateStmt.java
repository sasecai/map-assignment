package model.stmt;

import exception.MyException;
import model.PrgState;
import model.expressions.Exp;
import model.types.RefType;
import model.values.RefValue;
import model.values.Value;

public class AllocateStmt implements IStmt{
    String varName;
    Exp expression;
    @Override
    public String toStr() throws MyException {
        return "Alloc(" + varName + ", " + expression.toStr() + ")";
    }

    public AllocateStmt(String vName, Exp exp) {
        varName = vName;
        expression = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        if(!state.getSymTable().isDefined(varName))
            throw new MyException("Name is not in Symbol Table");
        if(!(state.getSymTable().access(varName).getType() instanceof RefType))
            throw new MyException("Name is not of type RefType in Symbol Table");

        Value val = expression.eval(state.getSymTable(), state.getHeap());

        if(!val.getType().equals(((RefType) state.getSymTable().access(varName).getType()).getInner()))
            throw new MyException("Type of allocated variable doesn't match the symbol table variable");

        int address = state.getHeap().allocate(val);
        ((RefValue)state.getSymTable().access(varName)).setAddress(address);

        return state;
    }
}
