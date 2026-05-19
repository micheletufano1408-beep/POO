package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Release {
    private String codiceCatalogo;
    private String titolo;
    private String tipoFormato;
    private LocalDate dataPubblicazione;
    private String stato;

    private Artista artista;

    private List<Tecnico> tecniciAssegnati;

    private List<CampagnaMarketing> campagneMarketing;

    private List<RoyaltyReport> royaltyReports;

    public Release(String codiceCatalogo, String titolo, String tipoFormato, LocalDate dataPubblicazione, String stato, Artista artista) {
        this.codiceCatalogo = codiceCatalogo;
        this.titolo = titolo;
        this.tipoFormato = tipoFormato;
        this.dataPubblicazione = dataPubblicazione;
        this.stato = stato;
        this.artista = artista;
        this.tecniciAssegnati = new ArrayList<>();
        this.campagneMarketing = new ArrayList<>();
        this.royaltyReports = new ArrayList<>();
    }

    public String getCodiceCatalogo() { return codiceCatalogo; }
    public void setCodiceCatalogo(String codiceCatalogo) { this.codiceCatalogo = codiceCatalogo; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getTipoFormato() { return tipoFormato; }
    public void setTipoFormato(String tipoFormato) { this.tipoFormato = tipoFormato; }

    public LocalDate getDataPubblicazione() { return dataPubblicazione; }
    public void setDataPubblicazione(LocalDate dataPubblicazione) { this.dataPubblicazione = dataPubblicazione; }

    public String getStato() { return stato; }
    public void setStato(String stato) { this.stato = stato; }

    public Artista getArtista() { return artista; }
    public void setArtista(Artista artista) { this.artista = artista; }

    public List<Tecnico> getTecniciAssegnati() { return tecniciAssegnati; }
    public void setTecniciAssegnati(List<Tecnico> tecniciAssegnati) { this.tecniciAssegnati = tecniciAssegnati; }

    public List<CampagnaMarketing> getCampagneMarketing() { return campagneMarketing; }
    public void setCampagneMarketing(List<CampagnaMarketing> campagneMarketing) { this.campagneMarketing = campagneMarketing; }

    public List<RoyaltyReport> getRoyaltyReports() { return royaltyReports; }
    public void setRoyaltyReports(List<RoyaltyReport> royaltyReports) { this.royaltyReports = royaltyReports; }
}