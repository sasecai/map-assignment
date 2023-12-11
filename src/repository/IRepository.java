package repository;

import model.PrgState;

public interface IRepository {
    PrgState getState();
    void setState(PrgState s);
    void logPrgStateExec();
}
