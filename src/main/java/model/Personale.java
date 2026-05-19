package model;

import java.time.LocalDate;

public abstract class Personale {
    private String idDipendente;
    private String nome;
    private String cognome;
    private LocalDate dataAssunzione;

    protected Personale(String idDipendente, String nome, String cognome, LocalDate dataAssunzione) {
        this.idDipendente = idDipendente;
        this.nome = nome;
        this.cognome = cognome;
        this.dataAssunzione = dataAssunzione;
    }

    public String getIdDipendente() { return idDipendente; }
    public void setIdDipendente(String idDipendente) { this.idDipendente = idDipendente; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public LocalDate getDataAssunzione() { return dataAssunzione; }
    public void setDataAssunzione(LocalDate dataAssunzione) { this.dataAssunzione = dataAssunzione; }
}




