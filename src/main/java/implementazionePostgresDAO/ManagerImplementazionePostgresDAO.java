package implementazionePostgresDAO;

import dao.ManagerDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.Manager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerImplementazionePostgresDAO implements ManagerDAO {

    @Override
    public void salvaManager(Manager manager) throws DatabaseException{
        String sql = "INSERT INTO manager (id_dipendente, nome, cognome, data_assunzione, bonus_percentuale) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, manager.getIdDipendente());
            pstmt.setString(2, manager.getNome());
            pstmt.setString(3, manager.getCognome());
            pstmt.setDate(4, Date.valueOf(manager.getDataAssunzione()));
            pstmt.setDouble(5, manager.getBonusPercentuale());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new DatabaseException("Errore durante il salvataggio del manager: " + e.getMessage());
        }
    }

    @Override
    public List<Manager> getTuttiIManager() throws DatabaseException {
        List<Manager> listaManager = new ArrayList<>();
        String sql = "SELECT * FROM manager";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_dipendente");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
                Double bonus = rs.getDouble("bonus_percentuale");

                Manager manager = new Manager(id, nome, cognome, dataAssunzione, bonus);
                listaManager.add(manager);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Errore durante la ricerca del manager: " + e.getMessage());
        }

        return listaManager;
    }
}