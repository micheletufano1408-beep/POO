package implementazionePostgresDAO;

import dao.ReleaseDAO;
import database.ConnessioneDatabase;
import eccezioni.DatabaseException;
import model.Artista;
import model.Release;

import org.postgresql.util.PSQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReleaseImplementazionePostgresDAO implements ReleaseDAO {

    @Override
    public void salvaRelease(Release release) throws DatabaseException{

        String sql = "INSERT INTO release (codice_catalogo, titolo, tipo_formato, data_pubblicazione, stato, id_artista) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, release.getCodiceCatalogo());
            pstmt.setString(2, release.getTitolo());
            pstmt.setString(3, release.getTipoFormato());
            pstmt.setDate(4, Date.valueOf(release.getDataPubblicazione()));
            pstmt.setString(5, release.getStato());

            pstmt.setString(6, release.getArtista().getIdArtista());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            String messaggioPulito = e.getMessage();

            if (e instanceof PSQLException) {
                PSQLException pgEx = (PSQLException) e;
                if (pgEx.getServerErrorMessage() != null) {
                    messaggioPulito = pgEx.getServerErrorMessage().getMessage();
                }
            }

            throw new DatabaseException("Impossibile salvare la release.\n" + messaggioPulito);
        }
    }

    @Override
    public List<Release> getTutteLeRelease() throws DatabaseException{
        List<Release> listaRelease = new ArrayList<>();

        String sql = "SELECT r.*, a.nome_arte, a.genere_musicale " +
                "FROM release r " +
                "LEFT JOIN artista a ON r.id_artista = a.id_artista";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String codice = rs.getString("codice_catalogo");
                String titolo = rs.getString("titolo");
                String formato = rs.getString("tipo_formato");
                LocalDate dataPubb = rs.getDate("data_pubblicazione").toLocalDate();
                String stato = rs.getString("stato");

                String idArtista = rs.getString("id_artista");
                String nomeArte = rs.getString("nome_arte");
                String genere = rs.getString("genere_musicale");

                Artista artista = new Artista(idArtista, nomeArte, genere, LocalDate.now(), LocalDate.now(), null);

                Release release = new Release(codice, titolo, formato, dataPubb, stato, artista);
                listaRelease.add(release);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Errore durante il recupero della release: " + e.getMessage());
        }

        return listaRelease;
    }
}