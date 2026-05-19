package model;

import java.time.LocalDate;

public class Manager extends Personale {
    private String ruoloSpecializzato;

    public Manager(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, String ruoloSpecializzato) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.ruoloSpecializzato = ruoloSpecializzato;
    }
}
