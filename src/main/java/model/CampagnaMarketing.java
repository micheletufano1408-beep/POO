package model;

public class CampagnaMarketing {
    private String idCampagna;
    private String piattaforma;
    private Double costoStimato;

    private Release releasePromossa;

    private Dipartimento dipartimentoFinanziatore;

    public CampagnaMarketing(String idCampagna, String piattaforma, Double costoStimato, Release releasePromossa, Dipartimento dipartimentoFinanziatore) {
        this.idCampagna = idCampagna;
        this.piattaforma = piattaforma;
        this.costoStimato = costoStimato;
        this.releasePromossa = releasePromossa;
        this.dipartimentoFinanziatore = dipartimentoFinanziatore;
    }

    public String getIdCampagna() { return idCampagna; }
    public void setIdCampagna(String idCampagna) { this.idCampagna = idCampagna; }

    public String getPiattaforma() { return piattaforma; }
    public void setPiattaforma(String piattaforma) { this.piattaforma = piattaforma; }

    public Double getCostoStimato() { return costoStimato; }
    public void setCostoStimato(Double costoStimato) { this.costoStimato = costoStimato; }

    public Release getReleasePromossa() { return releasePromossa; }
    public void setReleasePromossa(Release releasePromossa) { this.releasePromossa = releasePromossa; }

    public Dipartimento getDipartimentoFinanziatore() { return dipartimentoFinanziatore; }
    public void setDipartimentoFinanziatore(Dipartimento dipartimentoFinanziatore) { this.dipartimentoFinanziatore = dipartimentoFinanziatore; }
}