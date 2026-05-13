package model;

public class RoyaltyReport {
    private String idReport;
    private String periodoRiferimento;
    private Double ricaviTotali;
}
    public String getIdReport() {
        return idReport;
    }

    public void setIdReport(String idReport) {
        this.idReport = idReport;
    }

    public String getPeriodoRiferimento() {
        return periodoRiferimento;
    }

    public void setPeriodoRiferimento(String periodoRiferimento) {
        this.periodoRiferimento = periodoRiferimento;
    }

    public Double getRicaviTotali() {
        return ricaviTotali;
    }

    public void setRicaviTotali(Double ricaviTotali) {
        this.ricaviTotali = ricaviTotali;
    }

    public RoyaltyReport(String idReport, String periodoRiferimento, Double ricaviTotali) {
        this.idReport = idReport;
        this.periodoRiferimento = periodoRiferimento;
        this.ricaviTotali = ricaviTotali;
    }

