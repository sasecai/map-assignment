package view;

import controller.Controller;
import exception.MyException;
import model.containers.MyList;
import model.expressions.ArithExp;
import model.expressions.LogicExp;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.stmt.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

import java.util.Scanner;

public class View {
    Controller control;
    public View(Controller c) { control = c; }

    public void uiMenu() {
        Scanner scn = new Scanner(System.in);
        int log = -1;
        while(true) {
            try {
                System.out.println("log commands?(1/0)");
                log = scn.nextInt();
                if(log == 1)
                    control.setLog(true);
                else if(log == 0)
                    control.setLog(false);
                else
                    throw new Exception("Bad command");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        int type = -1;
        while(type != 0) {
            System.out.println("Enter expression to evaluate");
            System.out.println("1. int v; v=3; print(v)");
            System.out.println("2. int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)");
            System.out.println("3. bool a; int v; a = true; if(a) then v=2 else v=3. print(v)");
            System.out.println("4. string varf, varf = \"test.in\"; openRFile(varf); int varc; readFile(varf, varc); print(varc); readFile(varf, varc); print(varc); closeRFile(varf)");
            System.out.println("0. exit");
            try{
                type = scn.nextInt();
                if(type == 1) {
//                    IStmt iniStatement = new CompoundStmt(new VarDeclStmt("v", new IntType()),
//                            new CompoundStmt(new AssignStmt("v", new ValueExp(new IntValue(3))),
//                                    new PrintStmt(new VarExp("v"))));

                    IStmt iniStatement = new PrintStmt(new LogicExp(new ValueExp(
                            new BoolValue(true)), new ValueExp(new IntValue(2)),
                            LogicExp.LogicExpType.AND));

                    testStatement(iniStatement);
                } else if(type == 2) {
                    IStmt iniStatement = new CompoundStmt(new VarDeclStmt("a", new IntType()),
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
                    testStatement(iniStatement);
                } else if(type == 3) {
                    IStmt iniStatement = new CompoundStmt(new VarDeclStmt("a", new BoolType()),
                            new CompoundStmt(new VarDeclStmt("v", new IntType()),
                                    new CompoundStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                            new CompoundStmt(new IfStmt(new VarExp("a"),
                                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                                    new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                                    new PrintStmt(new VarExp("v"))))));
                    testStatement(iniStatement);
                } else if(type == 4) {
                    IStmt iniStatement = new CompoundStmt(new VarDeclStmt("varf", new model.types.StringType()),
                            new CompoundStmt(new AssignStmt("varf", new ValueExp(new model.values.StringValue("test.in"))),
                                    new CompoundStmt(new openRFile(new VarExp("varf")),
                                            new CompoundStmt(new VarDeclStmt("varc", new IntType()),
                                                    new CompoundStmt(new readFile(new VarExp("varf"), "varc"),
                                                            new CompoundStmt(new PrintStmt(new VarExp("varc")),
                                                                    new CompoundStmt(new readFile(new VarExp("varf"), "varc"),
                                                                            new CompoundStmt(new PrintStmt(new VarExp("varc")),
                                                                                    new closeRFile(new VarExp("varf"))))))))));
                    testStatement(iniStatement);
                }
                else if(type == 0) {
                    return;
                } else {
                    System.out.println("Bad option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void testStatement(IStmt iniStatement) {
        control.setProgram(iniStatement);
        try {
            control.allStep();
            MyList<Value> toPrint = (MyList<Value>) control.getOut();
            for(int i = 0; i < toPrint.size(); i ++) {
                System.out.println(toPrint.get(i).toString());
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
