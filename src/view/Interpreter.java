package view;

import controller.Controller;
import exception.MyException;
import model.PrgState;
import model.containers.MyDictionary;
import model.containers.MyHeap;
import model.containers.MyList;
import model.containers.MyStack;
import model.expressions.*;
import model.stmt.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.RefType;
import model.values.*;
import repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) throws MyException {
//        IStmt ex1 = new PrintStmt(new LogicExp(new ValueExp(
//                new BoolValue(true)), new ValueExp(new IntValue(5)),
//                LogicExp.LogicExpType.AND));

        // varianta 1 (care are si writeHeap))
//        IStmt ex1 = new CompoundStmt(new VarDeclStmt("ace", new RefType(new IntType())),
//                        new CompoundStmt(new AllocateStmt("ace", new ValueExp(new IntValue(3))),
//                            new CompoundStmt(new WriteHeap("ace", new ValueExp(new IntValue(5))),
//                                    new PrintStmt(new ReadHeap(new VarExp("ace"))))));

//        //varianta 2 (care nu are writeHeap)
//        IStmt ex1 = new CompoundStmt(new VarDeclStmt("ace", new RefType(new IntType())),
//                new CompoundStmt(new AllocateStmt("ace", new ValueExp(new IntValue(3))),
//                        new PrintStmt(new ReadHeap(new VarExp("ace")))));

        // varianta 3 (cu garbage collector, dublu alocare))
        IStmt ex1 = new CompoundStmt(new VarDeclStmt("ace", new RefType(new IntType())),
                        new CompoundStmt(new AllocateStmt("ace", new ValueExp(new IntValue(3))),
                            new CompoundStmt(new AllocateStmt("ace", new ValueExp(new IntValue(5))),
                                    new PrintStmt(new ReadHeap(new VarExp("ace"))))));

        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyDictionary<String, Value> dic = new MyDictionary<String, Value>();
        MyList<Value> out = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h1 = new MyHeap();
        PrgState state = new PrgState(stk, dic, out, ex1, fileTable, h1);
        Repository repo = new Repository(state);
        repo.setLogFilePath("log.txt");
        Controller cntr = new Controller(repo);

        IStmt ex2 = new CompoundStmt(new VarDeclStmt("a", new IntType()),
                new CompoundStmt(new VarDeclStmt("b", new IntType()),
                        new CompoundStmt(new AssignStmt("a",
                                new ArithExp(new ValueExp(new IntValue(2)),
                                        new ArithExp(new ValueExp(new IntValue(3)),
                                                new ValueExp(new IntValue(5)),
                                                ArithExp.ExpType.MULTIPLY), ArithExp.ExpType.PLUS)),
                                new CompoundStmt(new AssignStmt("b",
                                        new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)),
                                                ArithExp.ExpType.PLUS)),
                                        new PrintStmt(new VarExp("b"))))));
        MyStack<IStmt> stk2 = new MyStack<IStmt>();
        MyDictionary<String, Value> dic2 = new MyDictionary<String, Value>();
        MyList<Value> out2 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable2 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h2 = new MyHeap();
        PrgState state2 = new PrgState(stk2, dic2, out2, ex2, fileTable2, h2);
        Repository repo2 = new Repository(state2);
        repo2.setLogFilePath("log2.txt");
        Controller cntr2 = new Controller(repo2);


        IStmt ex3 = new CompoundStmt(new VarDeclStmt("a", new BoolType()),
                new CompoundStmt(new VarDeclStmt("v", new IntType()),
                        new CompoundStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompoundStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));

        MyStack<IStmt> stk3 = new MyStack<IStmt>();
        MyDictionary<String, Value> dic3 = new MyDictionary<String, Value>();
        MyList<Value> out3 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable3 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h3 = new MyHeap();
        PrgState state3 = new PrgState(stk3, dic3, out3, ex3, fileTable3, h3);
        Repository repo3 = new Repository(state3);
        repo3.setLogFilePath("log3.txt");
        Controller cntr3 = new Controller(repo3);

        IStmt ex4 = new CompoundStmt(new VarDeclStmt("varf", new model.types.StringType()),
                new CompoundStmt(new AssignStmt("varf", new ValueExp(new model.values.StringValue("test.in"))),
                        new CompoundStmt(new openRFile(new VarExp("varf")),
                                new CompoundStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompoundStmt(new readFile(new VarExp("varf"), "varc"),
                                                new CompoundStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompoundStmt(new readFile(new VarExp("varf"), "varc"),
                                                                new CompoundStmt(new PrintStmt(new VarExp("varc")),
                                                                        new closeRFile(new VarExp("varf"))))))))));
        MyStack<IStmt> stk4 = new MyStack<IStmt>();
        MyDictionary<String, Value> dic4 = new MyDictionary<String, Value>();
        MyList<Value> out4 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable4 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h4 = new MyHeap();
        PrgState state4 = new PrgState(stk4, dic4, out4, ex4, fileTable4, h4);
        Repository repo4 = new Repository(state4);
        repo4.setLogFilePath("log4.txt");
        Controller cntr4 = new Controller(repo4);



        IStmt ex5 = new CompoundStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompoundStmt(new AllocateStmt("v", new ValueExp(new IntValue(20))),
                        new CompoundStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompoundStmt(new AllocateStmt("a", new VarExp("v")),
                                        new CompoundStmt(new PrintStmt(new ReadHeap(new VarExp("v"))),
                                                new PrintStmt(new ReadHeap(new ReadHeap(new VarExp("a")))))))));
        MyStack<IStmt> stk5 = new MyStack<IStmt>();
        MyDictionary<String, Value> dic5 = new MyDictionary<String, Value>();
        MyList<Value> out5 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable5 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h5 = new MyHeap();
        PrgState state5 = new PrgState(stk5, dic5, out5, ex5, fileTable5, h5);
        Repository repo5 = new Repository(state5);
        repo5.setLogFilePath("log5.txt");
        Controller cntr5 = new Controller(repo5);

        IStmt ex6 = new CompoundStmt(new VarDeclStmt("v", new IntType()),
                        new CompoundStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                            new CompoundStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompoundStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), ArithExp.ExpType.MINUS)))),
                                    new PrintStmt(new VarExp("v")))));
        MyStack<IStmt> stk6 = new MyStack<IStmt>();
        MyDictionary<String, Value> dic6 = new MyDictionary<String, Value>();
        MyList<Value> out6 = new MyList<Value>();
        MyDictionary<StringValue, BufferedReader> fileTable6 = new MyDictionary<StringValue, BufferedReader>();
        MyHeap h6 = new MyHeap();
        PrgState state6 = new PrgState(stk6, dic6, out6, ex6, fileTable6, h6);
        Repository repo6 = new Repository(state6);
        repo6.setLogFilePath("log6.txt");
        Controller cntr6 = new Controller(repo6);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toStr(), cntr));
        menu.addCommand(new RunExample("2", ex2.toStr(), cntr2));
        menu.addCommand(new RunExample("3", ex3.toStr(), cntr3));
        menu.addCommand(new RunExample("4", ex4.toStr(), cntr4));
        menu.addCommand(new RunExample("5", ex5.toStr(), cntr5));
        menu.addCommand(new RunExample("6", ex6.toStr(), cntr6));
        menu.show();
    }
}
