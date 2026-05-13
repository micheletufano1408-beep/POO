package model;

public class Dipartimento {
    private String nomeDipartimento;
    private Double budgetAnnuale;
    private String idDipartimento;
}
    public String getNomeDipartimento() {
        return nomeDipartimento;
    }

    public void setNomeDipartimento(String nomeDipartimento) {
        this.nomeDipartimento = nomeDipartimento;
    }

    public Double getBudgetAnnuale() {
        return budgetAnnuale;
    }

    public void setBudgetAnnuale(Double budgetAnnuale) {
        this.budgetAnnuale = budgetAnnuale;
    }

    public String getIdDipartimento() {
        return idDipartimento;
    }

    public void setIdDipartimento(String idDipartimento) {
        this.idDipartimento = idDipartimento;
    }

    public Dipartimento(String nomeDipartimento, Double budgetAnnuale, String idDipartimento) {
        this.nomeDipartimento = nomeDipartimento;
        this.budgetAnnuale = budgetAnnuale;
        this.idDipartimento = idDipartimento;
    }

