package implementazionePostgresDAO;

import dao.TecnicoDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.Tecnico;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TecnicoImplementazionePostgresDAO implements TecnicoDAO {

    @Override
    public void salvaTecnico(Tecnico tecnico) throws DatabaseException {
        String sql = "INSERT INTO tecnico (id_dipendente, nome, cognome, data_assunzione, ruolo_specializzato) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tecnico.getIdDipendente());
            pstmt.setString(2, tecnico.getNome());
            pstmt.setString(3, tecnico.getCognome());
            pstmt.setDate(4, Date.valueOf(tecnico.getDataAssunzione()));
            pstmt.setString(5, tecnico.getRuoloSpecializzato());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new DatabaseException("Errore durante il salvataggio del tecnico: " + e.getMessage());
        }
    }
}