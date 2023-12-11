package model;

import exception.MyException;
import model.containers.MyIDictionary;
import model.containers.MyIHeap;
import model.containers.MyIList;
import model.containers.MyIStack;
import model.stmt.IStmt;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileWriter;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap heap;
    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }
    public MyIStack<IStmt> getStack() {
        return exeStack;
    }
    public MyIHeap getHeap() { return heap; }
    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }
    public String toStr() {
        String s = "";
        s += "Execution stack:\n";
        try{
            s += exeStack.toStr() + "\n";
        } catch (Exception e) {
            s += "Exception:(";
        }
        s += "Symbol table:\n";

        try{
            s += symTable.toStr() + "\n";
        } catch (Exception e) {
            s += "Exception:(";
        }
        s += "Out:\n";

        try{
            s += out.toStr() + "\n";
        } catch (Exception e) {
            s += "Exception:(";
        }
        return s;
    }
    public void newOut(Value val) {
        out.add(val);
    }
    public void updateSymTable(String key, Value val) {
        symTable.insert(key, val);
    }
//    IStmt originalProgram;
public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> ft, MyIHeap hp) {
        exeStack = stk;
        symTable = symtbl;
        heap = hp;
        out = ot;
        fileTable = ft;
//        originalProgram = deepCopy(prg);
        stk.push(prg);
    }
    public MyIList<Value> getOut() {
        return out;
    }
    public String logPrgStateExec() throws MyException {
        try {
            MyIDictionary<String, Value> symTable = this.getSymTable();
            MyIStack<IStmt> exeStack = this.getStack();
            MyIList<Value> out = this.getOut();
            MyIDictionary<StringValue, BufferedReader> fileTable = this.getFileTable();
            String s = "";
            s += "Execution stack:\n";
            try{
                s += exeStack.toStr() + "\n";
            } catch (Exception e) {
                s += "Exception:(";
            }
            s += "Symbol table:\n";

            try{
                s += symTable.toStr() + "\n";
            } catch (Exception e) {
                s += "Exception:(";
            }
            s += "Out:\n";

            try{
                s += out.toStr() + "\n";
            } catch (Exception e) {
                s += "Exception:(";
            }
            s += "File table:\n";

            try{
                s += fileTable.toStr() + "\n";
            } catch (Exception e) {
                s += "Exception:(";
            }
            s += "Heap:\n";

            try{
                s += heap.toString() + "\n";
            } catch (Exception e) {
                s += "Exception:(";
            }
            s += "\n";
            return s;
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
