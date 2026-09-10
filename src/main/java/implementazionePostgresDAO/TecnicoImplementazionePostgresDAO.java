package implementazionePostgresDAO;

import dao.TecnicoDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.Manager;
import model.Tecnico;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<Tecnico> getTuttiITecnici() throws DatabaseException {
        List<Tecnico> listaTecnici = new ArrayList<>();
        String sql = "SELECT * FROM tecnico";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_dipendente");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                LocalDate dataAssunzione = rs.getDate("data_assunzione").toLocalDate();
                String ruoloSpecializzato = rs.getString("ruolo_specializzato");

                Tecnico tecnico = new Tecnico(id, nome, cognome, dataAssunzione, ruoloSpecializzato);
                listaTecnici.add(tecnico);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Errore durante la ricerca del tecnico: " + e.getMessage());
        }

        return listaTecnici;
    }
}