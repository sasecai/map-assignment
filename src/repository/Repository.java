package repository;

import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Repository implements IRepository{
    PrgState state;
    String logFilePath;
    public Repository(PrgState s) { state = s; }
    public PrgState getState() { return state; }
    public void setState(PrgState s) { state = s; }
    public String getLogFilePath() { return logFilePath; }
    public void setLogFilePath(String newLogFilePath) {
        try {
            PrintWriter writer = new PrintWriter(newLogFilePath);
            writer.print("");
            writer.close();
        } catch (Exception e) {
        }
        logFilePath = newLogFilePath;
    }
    public void logPrgStateExec() {
        try {
            PrintWriter logFile;
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println(state.logPrgStateExec());
            logFile.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
