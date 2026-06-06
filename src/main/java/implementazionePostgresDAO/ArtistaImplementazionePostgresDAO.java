package implementazionePostgresDAO;

import dao.ArtistaDAO;
import database.ConnessioneDatabase;
import model.Artista;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtistaImplementazionePostgresDAO implements ArtistaDAO {

    @Override
    public void salvaArtista(Artista artista) {
        String sql = "INSERT INTO artista (id_artista, nome_arte, genere_musicale, data_inizio_contratto, data_fine_contratto, id_manager) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, artista.getIdArtista());
            pstmt.setString(2, artista.getNomeArte());
            pstmt.setString(3, artista.getGenereMusicale());
            pstmt.setDate(4, Date.valueOf(artista.getDataInizioContratto()));
            pstmt.setDate(5, Date.valueOf(artista.getDataFineContratto()));


            if (artista.getManager() != null) {
                pstmt.setString(6, artista.getManager().getIdDipendente());
            } else {
                pstmt.setNull(6, java.sql.Types.VARCHAR);
            }

            int righeInserite = pstmt.executeUpdate();

            if (righeInserite > 0) {
                System.out.println("✅ Artista " + artista.getNomeArte() + " salvato con successo nel DB!");
            }

        } catch (SQLException e) {
            System.out.println(" Errore durante il salvataggio dell'artista.");
            e.printStackTrace();
        }
    }
    @Override
    public List<Artista> getTuttiGliArtisti() {
        List<Artista> listaArtisti = new ArrayList<>();

        String sql = "SELECT a.*, m.nome AS mgr_nome, m.cognome AS mgr_cognome, m.data_assunzione AS mgr_data, m.bonus_percentuale AS mgr_bonus " +
                "FROM artista a " +
                "LEFT JOIN manager m ON a.id_manager = m.id_dipendente";

        try (Connection conn = ConnessioneDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String idArtista = rs.getString("id_artista");
                String nomeArte = rs.getString("nome_arte");
                String genere = rs.getString("genere_musicale");
                LocalDate dataInizio = rs.getDate("data_inizio_contratto").toLocalDate();
                LocalDate dataFine = rs.getDate("data_fine_contratto").toLocalDate();

                String idManager = rs.getString("id_manager");
                model.Manager manager = null;

                if (idManager != null) {
                    String mgrNome = rs.getString("mgr_nome");
                    String mgrCognome = rs.getString("mgr_cognome");
                    LocalDate mgrData = rs.getDate("mgr_data").toLocalDate();
                    Double mgrBonus = rs.getDouble("mgr_bonus");

                    manager = new model.Manager(idManager, mgrNome, mgrCognome, mgrData, mgrBonus);
                }

                Artista artista = new Artista(idArtista, nomeArte, genere, dataInizio, dataFine, manager);
                listaArtisti.add(artista);
            }

        } catch (SQLException e) {
            System.out.println("Errore durante il recupero degli artisti dal Database.");
            e.printStackTrace();
        }

        return listaArtisti;
    }
}