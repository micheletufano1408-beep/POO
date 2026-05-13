package model;

import java.util.Date;

public class Tecnico extends Personale {
    private Double bonusPercentuale;
}
    public Double getBonusPercentuale() {
        return bonusPercentuale;
    }

    public void setBonusPercentuale(Double bonusPercentuale) {
        this.bonusPercentuale = bonusPercentuale;
    }

    public Tecnico(String idDipendente, String nome, String cognome, Date dataAssunzione, Double bonusPercentuale) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.bonusPercentuale = bonusPercentuale;
    }

