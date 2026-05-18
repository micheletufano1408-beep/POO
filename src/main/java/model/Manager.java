package model;

import java.util.Date;

public class Manager extends Personale {
    private String ruoloSpecializzato;

    public Manager(String idDipendente, String nome, String cognome, Date dataAssunzione, String ruoloSpecializzato) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.ruoloSpecializzato = ruoloSpecializzato;
    }
}
