package dao;

import model.Manager;
import java.util.List;

public interface ManagerDAO {
    void salvaManager(Manager manager);
    List<Manager> getTuttiIManager();
}