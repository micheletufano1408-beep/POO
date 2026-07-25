package implementazionePostgresDAO;

import dao.DipartimentoDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.Dipartimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DipartimentoImplementazionePostgresDAO implements DipartimentoDAO {

    @Override
    public List<Dipartimento> getTuttiIDipartimenti() throws DatabaseException{
        List<Dipartimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM dipartimento";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id_dipartimento");

                String nome = rs.getString("nome_dipartimento");

                Double budget = rs.getDouble("budget_annuale");

                Dipartimento dip = new Dipartimento(id, nome, budget);
                lista.add(dip);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Errore durante il salvataggio del dipartimento: " + e.getMessage());
        }

        return lista;
    }
}