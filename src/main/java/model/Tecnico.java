package model;

import java.time.LocalDate;

public class Tecnico extends Personale {
    private Double bonusPercentuale;

    public Double getBonusPercentuale() {
        return bonusPercentuale;
    }

    public void setBonusPercentuale(Double bonusPercentuale) {
        this.bonusPercentuale = bonusPercentuale;
    }

    public Tecnico(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, Double bonusPercentuale) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.bonusPercentuale = bonusPercentuale;
    }
    }

