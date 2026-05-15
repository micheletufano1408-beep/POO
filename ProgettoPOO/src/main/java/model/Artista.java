package model;

import java.util.Date;
import java.lang.String;

public class Artista {
    private String nomeArte;
    private String genereMusicale;
    private Date dataInizioContratto;
    private Date dataFineContratto;
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

    public Date getDataInizioContratto() {
        return dataInizioContratto;
    }

    public void setDataInizioContratto(Date dataInizioContratto) {
        this.dataInizioContratto = dataInizioContratto;
    }

    public Date getDataFineContratto() {
        return dataFineContratto;
    }

    public void setDataFineContratto(Date dataFineContratto) {
        this.dataFineContratto = dataFineContratto;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public Artista(String nomeArte, String genereMusicale, Date dataInizioContratto, Date dataFineContratto, String idArtista) {
        this.nomeArte = nomeArte;
        this.genereMusicale = genereMusicale;
        this.dataInizioContratto = dataInizioContratto;
        this.dataFineContratto = dataFineContratto;
        this.idArtista = idArtista;
    }
}


