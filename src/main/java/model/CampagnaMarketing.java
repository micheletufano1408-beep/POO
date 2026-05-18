package model;

public class CampagnaMarketing {
    private String idCampagna;
    private String piattaforma;
    private Double costoStimato;

    public String getIdCampagna() {
        return idCampagna;
    }

    public void setIdCampagna(String idCampagna) {
        this.idCampagna = idCampagna;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public Double getCostoStimato() {
        return costoStimato;
    }

    public void setCostoStimato(Double costoStimato) {
        this.costoStimato = costoStimato;
    }

    public CampagnaMarketing(String idCampagna, String piattaforma, Double costoStimato) {
        this.idCampagna = idCampagna;
        this.piattaforma = piattaforma;
        this.costoStimato = costoStimato;
    }
    }

