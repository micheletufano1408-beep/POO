package model;

import java.time.LocalDate;

public class Artista {
    private String nomeArte;
    private String genereMusicale;
    private LocalDate dataInizioContratto;
    private LocalDate dataFineContratto;
    private String idArtista;

    public String getNomeArte() {
        return nomeArte;
    }

    public void setNomeArte(String nomeArte) {
        this.nomeArte = nomeArte;
    }

    public String getGenereMusicale() {
        return genereMusicale;
    }

    public void setGenereMusicale(String genereMusicale) {
        this.genereMusicale = genereMusicale;
    }

    public LocalDate getDataInizioContratto() {
        return dataInizioContratto;
    }

    public void setDataInizioContratto(LocalDate dataInizioContratto) {
        this.dataInizioContratto = dataInizioContratto;
    }

    public LocalDate getDataFineContratto() {
        return dataFineContratto;
    }

    public void setDataFineContratto(LocalDate dataFineContratto) {
        this.dataFineContratto = dataFineContratto;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public Artista(String nomeArte, String genereMusicale, LocalDate dataInizioContratto, LocalDate dataFineContratto, String idArtista) {
        this.nomeArte = nomeArte;
        this.genereMusicale = genereMusicale;
        this.dataInizioContratto = dataInizioContratto;
        this.dataFineContratto = dataFineContratto;
        this.idArtista = idArtista;
    }
}


