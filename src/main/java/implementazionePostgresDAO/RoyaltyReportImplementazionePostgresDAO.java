package implementazionePostgresDAO;

import dao.RoyaltyReportDAO;
import database.ConnessioneDatabase;
import model.RoyaltyReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoyaltyReportImplementazionePostgresDAO implements RoyaltyReportDAO {

    @Override
    public void salvaRoyaltyReport(RoyaltyReport report) {
        String sql = "INSERT INTO royalty_report (id_report, periodo_riferimento, ricavi_totali, codice_release) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, report.getIdReport());
            pstmt.setString(2, report.getPeriodoRiferimento());
            pstmt.setDouble(3, report.getRicaviTotali());

            pstmt.setString(4, report.getReleaseRiferimento().getCodiceCatalogo());

            int righeInserite = pstmt.executeUpdate();
            if (righeInserite > 0) {
                System.out.println("Royalty Report '" + report.getIdReport() + "' salvato con successo nel DB!");
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio del Royalty Report nel Database.");
            e.printStackTrace();
        }
    }
}