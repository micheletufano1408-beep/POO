package implementazionePostgresDAO;

import dao.RoyaltyReportDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.RoyaltyReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoyaltyReportImplementazionePostgresDAO implements RoyaltyReportDAO {

    @Override
    public void salvaRoyaltyReport(RoyaltyReport report) throws DatabaseException{
        String sql = "INSERT INTO royalty_report (id_report, periodo_riferimento, ricavi_totali, codice_release) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, report.getIdReport());
            pstmt.setString(2, report.getPeriodoRiferimento());
            pstmt.setDouble(3, report.getRicaviTotali());

            pstmt.setString(4, report.getReleaseRiferimento().getCodiceCatalogo());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new DatabaseException("Errore durante il salvataggio della Royalty Report: " + e.getMessage());
        }
    }
}