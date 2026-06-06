package implementazionePostgresDAO;

import dao.DipartimentoDAO;
import database.ConnessioneDatabase;
import model.Dipartimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DipartimentoImplementazionePostgresDAO implements DipartimentoDAO {

    @Override
    public List<Dipartimento> getTuttiIDipartimenti() {
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
            System.out.println(" Errore durante la lettura dei dipartimenti.");
            e.printStackTrace();
        }

        return lista;
    }
}