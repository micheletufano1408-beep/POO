package dao;

import eccezioni.DatabaseException;
import model.Tecnico;

public interface TecnicoDAO {
    void salvaTecnico(Tecnico tecnico) throws DatabaseException;
}