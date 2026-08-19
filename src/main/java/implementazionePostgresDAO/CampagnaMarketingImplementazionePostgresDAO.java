package implementazionePostgresDAO;

import dao.CampagnaMarketingDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.CampagnaMarketing;

import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CampagnaMarketingImplementazionePostgresDAO implements CampagnaMarketingDAO {

    @Override
    public void salvaCampagnaMarketing(CampagnaMarketing campagna) throws DatabaseException { // <-- Aggiunto il throws
        String sql = "INSERT INTO campagna_marketing (id_campagna, piattaforma, costo_stimato, id_dipartimento, codice_release) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, campagna.getIdCampagna());
            pstmt.setString(2, campagna.getPiattaforma());
            pstmt.setDouble(3, campagna.getCostoStimato());
            pstmt.setString(4, campagna.getDipartimentoFinanziatore().getIdDipartimento());
            pstmt.setString(5, campagna.getReleasePromossa().getCodiceCatalogo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            String messaggioPulito = e.getMessage();

            if (e instanceof PSQLException) {
                PSQLException pgEx = (PSQLException) e;
                if (pgEx.getServerErrorMessage() != null) {
                    messaggioPulito = pgEx.getServerErrorMessage().getMessage();
                }
            }

            throw new DatabaseException("Impossibile salvare la campagna di marketing.\n" + messaggioPulito);
        }
    }
}