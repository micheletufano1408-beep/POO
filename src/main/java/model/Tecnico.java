package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Personale {
    private String ruoloSpecializzato;



    public Tecnico(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, String ruoloSpecializzato) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.ruoloSpecializzato = ruoloSpecializzato;

    }

    public String getRuoloSpecializzato() { return ruoloSpecializzato; }
    public void setRuoloSpecializzato(String ruoloSpecializzato) { this.ruoloSpecializzato = ruoloSpecializzato; }


}