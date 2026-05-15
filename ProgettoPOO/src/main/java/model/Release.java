package model;

import java.util.Date;

public class Release {
    private String codiceCatalogo;
    private String titolo;
    private String tipoFormato;
    private Date dataPubblicazione;
    private String stato;

    public String getCodiceCatalogo() {
        return codiceCatalogo;
    }

    public void setCodiceCatalogo(String codiceCatalogo) {
        this.codiceCatalogo = codiceCatalogo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTipoFormato() {
        return tipoFormato;
    }

    public void setTipoFormato(String tipoFormato) {
        this.tipoFormato = tipoFormato;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(Date dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Release(String codiceCatalogo, String titolo, String tipoFormato, Date dataPubblicazione, String stato) {
        this.codiceCatalogo = codiceCatalogo;
        this.titolo = titolo;
        this.tipoFormato = tipoFormato;
        this.dataPubblicazione = dataPubblicazione;
        this.stato = stato;
    }
    }

