import controller.Controller;
import exception.MyException;
import model.PrgState;
import model.containers.*;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.stmt.*;
import model.types.IntType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.Repository;
import view.View;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDictionary<String, Value> dic = new MyDictionary<String, Value>();
        MyList<Value> out = new MyList<Value>();
        IStmt iniStatement = new CompoundStmt(new VarDeclStmt("v", new IntType()),
                new CompoundStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        MyHeap hp = new MyHeap();
        PrgState state = new PrgState(stk, dic, out, iniStatement, fileTable, hp);
        Repository repo = new Repository(state);
        Controller cntr = new Controller(repo);
        View vw = new View(cntr);
//        vw.test();
        vw.uiMenu();
    }
}