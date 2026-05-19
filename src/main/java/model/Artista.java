package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String idArtista;
    private String nomeArte;
    private String genereMusicale;
    private LocalDate dataInizioContratto;
    private LocalDate dataFineContratto;

    private Manager manager;

    private List<Release> pubblicazioni;

    public Artista(String idArtista, String nomeArte, String genereMusicale, LocalDate dataInizioContratto, LocalDate dataFineContratto, Manager manager) {
        this.idArtista = idArtista;
        this.nomeArte = nomeArte;
        this.genereMusicale = genereMusicale;
        this.dataInizioContratto = dataInizioContratto;
        this.dataFineContratto = dataFineContratto;
        this.manager = manager;
        this.pubblicazioni = new ArrayList<>();
    }

    public String getIdArtista() { return idArtista; }
    public void setIdArtista(String idArtista) { this.idArtista = idArtista; }

    public String getNomeArte() { return nomeArte; }
    public void setNomeArte(String nomeArte) { this.nomeArte = nomeArte; }

    public String getGenereMusicale() { return genereMusicale; }
    public void setGenereMusicale(String genereMusicale) { this.genereMusicale = genereMusicale; }

    public LocalDate getDataInizioContratto() { return dataInizioContratto; }
    public void setDataInizioContratto(LocalDate dataInizioContratto) { this.dataInizioContratto = dataInizioContratto; }

    public LocalDate getDataFineContratto() { return dataFineContratto; }
    public void setDataFineContratto(LocalDate dataFineContratto) { this.dataFineContratto = dataFineContratto; }

    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    public List<Release> getPubblicazioni() { return pubblicazioni; }
    public void setPubblicazioni(List<Release> pubblicazioni) { this.pubblicazioni = pubblicazioni; }

    @Override
    public String toString() {
        return "Artista: " + getNomeArte() + " (Genere: " + getGenereMusicale() + ")";
    }
}