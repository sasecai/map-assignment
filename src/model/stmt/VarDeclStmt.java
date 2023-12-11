package model.stmt;

import exception.MyException;
import model.PrgState;
import model.types.Type;

public class VarDeclStmt implements IStmt{
    String name;
    Type varType;
    public VarDeclStmt(String nm, Type vt) {
        name = nm; varType = vt;
    }
    @Override
    public String toStr() throws MyException {
//        return "Declare variable " + name + " of type " + varType.toStr();
        return varType.toString() + " " + name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        state.updateSymTable(name, varType.defaultValue());
        return state;
    }
}
