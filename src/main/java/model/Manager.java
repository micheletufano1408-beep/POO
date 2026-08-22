package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Manager extends Personale {
    private Double bonusPercentuale;



    public Manager(String idDipendente, String nome, String cognome, LocalDate dataAssunzione, Double bonusPercentuale) {
        super(idDipendente, nome, cognome, dataAssunzione);
        this.bonusPercentuale = bonusPercentuale;

    }

    public Double getBonusPercentuale() { return bonusPercentuale; }
    public void setBonusPercentuale(Double bonusPercentuale) { this.bonusPercentuale = bonusPercentuale; }


    @Override
    public String toString() {
        return getNome() + " " + getCognome();
    }
}