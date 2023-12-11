package model.stmt;

import exception.MyException;
import model.*;
import model.containers.MyIDictionary;
import model.containers.MyIStack;
import model.expressions.Exp;
import model.types.Type;
import model.values.Value;

public class AssignStmt implements IStmt{
    String id;
    Exp expression;
    public AssignStmt(String i, Exp e) {
        id = i;
        expression = e;
    }
    @Override
    public String toStr() throws MyException {
        return id + "=" + expression.toStr();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.isDefined(id)) {
            Value val = expression.eval(symTbl, state.getHeap());
            Type typeId = symTbl.access(id).getType();
            if(val.getType().equals(typeId)) {
                symTbl.insert(id, val);
            }
            else throw new MyException("Declared type of variable " + id + " and type" +
                    "of the assigned expression do not match");
        }
        else throw new MyException("The used variable " + id + " was not declared before");
        return state;
    }
}
