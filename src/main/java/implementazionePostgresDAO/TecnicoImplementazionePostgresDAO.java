package implementazionePostgresDAO;

import dao.TecnicoDAO;
import database.ConnessioneDatabase;
import model.Tecnico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TecnicoImplementazionePostgresDAO implements TecnicoDAO {

    @Override
    public void salvaTecnico(Tecnico tecnico) {
        String sql = "INSERT INTO tecnico (id_dipendente, nome, cognome, data_assunzione, ruolo_specializzato) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tecnico.getIdDipendente());
            pstmt.setString(2, tecnico.getNome());
            pstmt.setString(3, tecnico.getCognome());
            pstmt.setDate(4, Date.valueOf(tecnico.getDataAssunzione()));
            pstmt.setString(5, tecnico.getRuoloSpecializzato());

            int righeInserite = pstmt.executeUpdate();

            if (righeInserite > 0) {
                System.out.println("✅ Tecnico " + tecnico.getNome() + " salvato con successo nel Database!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Errore durante il salvataggio del Tecnico nel Database.");
            e.printStackTrace();
        }
    }
}