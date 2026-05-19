package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Personale {
    private Double bonusPercentuale;

    private List<Artista> artistiGestiti;

    public Manager(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, Double bonusPercentuale) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.bonusPercentuale = bonusPercentuale;
        this.artistiGestiti = new ArrayList<>();
    }

    public Double getBonusPercentuale() { return bonusPercentuale; }
    public void setBonusPercentuale(Double bonusPercentuale) { this.bonusPercentuale = bonusPercentuale; }

    public List<Artista> getArtistiGestiti() { return artistiGestiti; }
    public void setArtistiGestiti(List<Artista> artistiGestiti) { this.artistiGestiti = artistiGestiti; }

    public void addArtista(Artista artista) {
        this.artistiGestiti.add(artista);
    }
    @Override
    public String toString() {
        return getNome() + " " + getCognome();
    }
}