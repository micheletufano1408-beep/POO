package implementazionePostgresDAO;

import dao.ReleaseDAO;
import database.ConnessioneDatabase;
import model.Artista;
import model.Release;

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
    public void salvaRelease(Release release) {

        String sql = "INSERT INTO release (codice_catalogo, titolo, tipo_formato, data_pubblicazione, stato, id_artista) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, release.getCodiceCatalogo());
            pstmt.setString(2, release.getTitolo());
            pstmt.setString(3, release.getTipoFormato());
            pstmt.setDate(4, Date.valueOf(release.getDataPubblicazione()));
            pstmt.setString(5, release.getStato());

            pstmt.setString(6, release.getArtista().getIdArtista());

            int righe = pstmt.executeUpdate();
            if (righe > 0) {
                System.out.println("Release '" + release.getTitolo() + "' salvata con successo nel DB!");
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il salvataggio della Release.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Release> getTutteLeRelease() {
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
            System.out.println("Errore durante il recupero delle Release.");
            e.printStackTrace();
        }

        return listaRelease;
    }
}