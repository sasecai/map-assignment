package model.stmt;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIStack;
import model.expressions.Exp;
import model.PrgState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;

public class IfStmt implements IStmt{
    Exp condition;
    IStmt thenStmt, elseStmt;
    public IfStmt(Exp e, IStmt t, IStmt el) {
        condition = e;
        thenStmt = t;
        elseStmt = el;
    }
    @Override
    public String toStr() throws MyException{
        return "(IF(" + condition.toStr() + ")" + ")THEN(" + thenStmt.toStr()
                +")ELSE("+elseStmt.toStr()+"))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(!condition.eval(symTbl, state.getHeap()).getType().equals(new BoolType()))
            throw new MyException("Not boolean condition");
        if(condition.eval(symTbl, state.getHeap()).getType().equals(new BoolType()) &&
                ((BoolValue)condition.eval(symTbl, state.getHeap())).getVal() == true) {
            // exp1
//            thenStmt.execute(state);
            MyIStack<IStmt> stk = state.getStack();
            stk.push(thenStmt);
        } else {
            // exp2
//            elseStmt.execute(state);
            MyIStack<IStmt> stk = state.getStack();
            stk.push(elseStmt);
        }
        return state; // i don't know if this is how i'm supposed to do it
    }
}
