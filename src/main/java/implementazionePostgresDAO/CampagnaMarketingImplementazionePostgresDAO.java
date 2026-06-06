package implementazionePostgresDAO;

import dao.CampagnaMarketingDAO;
import database.ConnessioneDatabase;
import model.CampagnaMarketing;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CampagnaMarketingImplementazionePostgresDAO implements CampagnaMarketingDAO {

    @Override
    public void salvaCampagnaMarketing(CampagnaMarketing campagna) {
        String sql = "INSERT INTO campagna_marketing (id_campagna, piattaforma, costo_stimato, id_dipartimento, codice_release) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, campagna.getIdCampagna());
            pstmt.setString(2, campagna.getPiattaforma());
            pstmt.setDouble(3, campagna.getCostoStimato());

            pstmt.setString(4, campagna.getDipartimentoFinanziatore().getIdDipartimento());

            pstmt.setString(5, campagna.getReleasePromossa().getCodiceCatalogo());

            int righeInserite = pstmt.executeUpdate();
            if (righeInserite > 0) {
                System.out.println("Campagna Marketing '" + campagna.getIdCampagna() + "' salvata con successo nel DB!");
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio della Campagna Marketing nel Database.");
            e.printStackTrace();
        }
    }
}