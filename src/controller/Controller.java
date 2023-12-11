package controller;

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
import repository.IRepository;
import model.values.RefValue;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    IRepository repo;
    boolean logMode = false;
    public Controller(IRepository r) { repo = r; }
    public IRepository getRepo() { return repo; }
    public void setLog(boolean newLog) { logMode = newLog; }
    public void setProgram(IStmt statement) {
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDictionary<String, Value> dic = new MyDictionary<String, Value>();
        MyList<Value> out = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        MyHeap hp = new MyHeap();
        PrgState state = new PrgState(stk, dic, out, statement, fileTable, hp);
        repo.setState(state);
    }
    public PrgState oneStep(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStack();
        if(stk.isEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        IStmt crtStmt = stk.top();
//        if(logMode) {
//            System.out.println("Before executing statement: " + crtStmt.toStr());
//            System.out.println(repo.getState().toStr());
//        }
        repo.logPrgStateExec();
        stk.pop();
        return crtStmt.execute(state);
    }
    MyIDictionary garbageCollector(List<Integer> symTableAddr, MyIDictionary<Integer, Value> heapTable) {
        MyIDictionary<Integer, Value> newHeap = new MyDictionary<>();
        for(Integer key : heapTable.getKeys()) {
            if(symTableAddr.contains(key)) {
                newHeap.insert(key, heapTable.access(key));
            }
        }

        return newHeap;
    }
    List<Integer> getAddressesFromSymTable(MyIDictionary<String, Value> symTable) {
        return symTable.getValues().stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    public void allStep() throws MyException {
        PrgState currentState = repo.getState();
        while(!currentState.getStack().isEmpty()) {
            oneStep(currentState);
            currentState.getHeap().setContent(
                    garbageCollector(
                            getAddressesFromSymTable(currentState.getSymTable()),
                            currentState.getHeap().getContent()
                    )
            );
        }
//        if(logMode) {
//            System.out.println("Final state:");
//            System.out.println(repo.getState().toStr());
//        }

        repo.logPrgStateExec();
    }
    public MyIList<Value> getOut() {
        return repo.getState().getOut();
    }
}
