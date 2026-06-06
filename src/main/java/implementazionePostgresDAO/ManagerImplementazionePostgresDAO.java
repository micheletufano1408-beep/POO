package implementazionePostgresDAO;

import dao.ManagerDAO;
import database.ConnessioneDatabase;
import model.Manager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerImplementazionePostgresDAO implements ManagerDAO {

    @Override
    public void salvaManager(Manager manager) {
        String sql = "INSERT INTO manager (id_dipendente, nome, cognome, data_assunzione, bonus_percentuale) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, manager.getIdDipendente());
            pstmt.setString(2, manager.getNome());
            pstmt.setString(3, manager.getCognome());
            pstmt.setDate(4, Date.valueOf(manager.getDataAssunzione()));
            pstmt.setDouble(5, manager.getBonusPercentuale());

            int righeInserite = pstmt.executeUpdate();
            if (righeInserite > 0) {
                System.out.println("Manager " + manager.getNome() + " salvato con successo nel Database!");
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio del manager.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Manager> getTuttiIManager() {
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
            System.out.println("❌ Errore durante il recupero dei manager dal database.");
            e.printStackTrace();
        }

        return listaManager;
    }
}