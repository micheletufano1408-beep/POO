package dao;

import eccezioni.DatabaseException;
import model.Manager;
import java.util.List;

public interface ManagerDAO {
    void salvaManager(Manager manager) throws DatabaseException;
    List<Manager> getTuttiIManager() throws DatabaseException;
}