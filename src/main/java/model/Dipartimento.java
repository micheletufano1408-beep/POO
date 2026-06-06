package model;

import java.util.ArrayList;
import java.util.List;

public class Dipartimento {
    private String idDipartimento;
    private String nomeDipartimento;
    private Double budgetAnnuale;

    private List<CampagnaMarketing> campagneFinanziate;

    public Dipartimento(String idDipartimento, String nomeDipartimento, Double budgetAnnuale) {
        this.idDipartimento = idDipartimento;
        this.nomeDipartimento = nomeDipartimento;
        this.budgetAnnuale = budgetAnnuale;
        this.campagneFinanziate = new ArrayList<>();
    }

    public String getIdDipartimento() { return idDipartimento; }
    public void setIdDipartimento(String idDipartimento) { this.idDipartimento = idDipartimento; }

    public String getNomeDipartimento() { return nomeDipartimento; }
    public void setNomeDipartimento(String nomeDipartimento) { this.nomeDipartimento = nomeDipartimento; }

    public Double getBudgetAnnuale() { return budgetAnnuale; }
    public void setBudgetAnnuale(Double budgetAnnuale) { this.budgetAnnuale = budgetAnnuale; }

    public List<CampagnaMarketing> getCampagneFinanziate() { return campagneFinanziate; }
    public void setCampagneFinanziate(List<CampagnaMarketing> campagneFinanziate) { this.campagneFinanziate = campagneFinanziate; }

    @Override
    public String toString() {
        return this.nomeDipartimento;
    }
}