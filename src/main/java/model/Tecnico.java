package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Personale {
    private String ruoloSpecializzato;

    private List<Release> releaseLavorate;

    public Tecnico(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, String ruoloSpecializzato) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.ruoloSpecializzato = ruoloSpecializzato;
        this.releaseLavorate = new ArrayList<>();
    }

    public String getRuoloSpecializzato() { return ruoloSpecializzato; }
    public void setRuoloSpecializzato(String ruoloSpecializzato) { this.ruoloSpecializzato = ruoloSpecializzato; }

    public List<Release> getReleaseLavorate() { return releaseLavorate; }
    public void setReleaseLavorate(List<Release> releaseLavorate) { this.releaseLavorate = releaseLavorate; }
}