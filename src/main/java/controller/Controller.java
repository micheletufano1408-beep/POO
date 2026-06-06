package controller;

import dao.ManagerDAO;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    private dao.ManagerDAO managerDAO;

    private List<Artista> artistiInMemoria;
    private List<Dipartimento> dipartimentiInMemoria;
    private List<CampagnaMarketing> campagneInMemoria;
    private List<Manager> managerInMemoria;
    private List<Tecnico> tecniciInMemoria;
    private List<Release> releaseInMemoria;
    private dao.TecnicoDAO tecnicoDAO;
    private dao.ArtistaDAO artistaDAO;
    private dao.ReleaseDAO releaseDAO;
    private dao.CampagnaMarketingDAO campagnaMarketingDAO;
    private dao.DipartimentoDAO dipartimentoDAO;
    private dao.RoyaltyReportDAO royaltyReportDAO;

    public Controller() {

        this.managerDAO = new implementazionePostgresDAO.ManagerImplementazionePostgresDAO();
        this.artistiInMemoria = new ArrayList<>();
        this.dipartimentiInMemoria = new ArrayList<>();
        this.campagneInMemoria = new ArrayList<>();
        this.managerInMemoria = new ArrayList<>();
        this.tecniciInMemoria = new ArrayList<>();
        this.releaseInMemoria = new ArrayList<>();
        this.tecnicoDAO = new implementazionePostgresDAO.TecnicoImplementazionePostgresDAO();
        this.artistaDAO = new implementazionePostgresDAO.ArtistaImplementazionePostgresDAO();
        this.releaseDAO = new implementazionePostgresDAO.ReleaseImplementazionePostgresDAO();
        this.campagnaMarketingDAO = new implementazionePostgresDAO.CampagnaMarketingImplementazionePostgresDAO();
        this.dipartimentoDAO = new implementazionePostgresDAO.DipartimentoImplementazionePostgresDAO();
        this.royaltyReportDAO = new implementazionePostgresDAO.RoyaltyReportImplementazionePostgresDAO();


    }

    //METODI PER ARTISTA
    public void registraNuovoArtista(String id, String nomeArte, String genereMusicale, LocalDate dataInizio, LocalDate dataFine, Manager manager) {
        Artista nuovoArtista = new Artista(id, nomeArte, genereMusicale, dataInizio, dataFine, manager);

        artistaDAO.salvaArtista(nuovoArtista);

        System.out.println("Artista Registrato: " + nuovoArtista.getNomeArte());
    }

    public List<Artista> getTuttiGliArtisti() {

        return artistaDAO.getTuttiGliArtisti();
    }

    //METODI PER MANAGER
    public void registraNuovoManager(String id, String nome, String cognome, LocalDate dataAssunzione, Double bonus) {

        Manager nuovoManager = new Manager(id, nome, cognome, dataAssunzione, bonus);


        managerDAO.salvaManager(nuovoManager);


        managerInMemoria.add(nuovoManager);

        System.out.println("Manager Registrato nel DB: " + nuovoManager.getNome() + " " + nuovoManager.getCognome());
    }

    public List<Manager> getTuttiIManager() {

        return managerDAO.getTuttiIManager();
    }
    // METODI PER TECNICI
    public void registraNuovoTecnico(String id, String nome, String cognome, LocalDate dataAssunzione, String ruoloSpecializzato) throws Exception {
        if (id == null || id.trim().isEmpty() || nome == null || nome.trim().isEmpty()) {
            throw new Exception("ID e Nome del tecnico sono obbligatori.");
        }

        model.Tecnico nuovoTecnico = new model.Tecnico(id, nome, cognome, dataAssunzione, ruoloSpecializzato);

        tecnicoDAO.salvaTecnico(nuovoTecnico);

        System.out.println("[CONTROLLER LOG] Tecnico registrato: " + nome + " " + cognome + " | Ruolo: " + ruoloSpecializzato);
    }
    //METODI PER RELEASE
    public void registraNuovaRelease(String codice, String titolo, String tipoFormato, LocalDate dataPubblicazione, String stato, Artista artista) throws Exception {
        if (codice == null || codice.trim().isEmpty() || artista == null) {
            throw new Exception("Codice e Artista sono campi obbligatori.");
        }

        Release nuovaRelease = new Release(codice, titolo, tipoFormato, dataPubblicazione, stato, artista);
        releaseDAO.salvaRelease(nuovaRelease);

        System.out.println("[CONTROLLER LOG] Release registrata: " + titolo + " dell'artista " + artista.getNomeArte());
    }

    public List<Release> getTutteLeRelease() {

        return releaseDAO.getTutteLeRelease();
    }
    //METODI PER ROYALTY REPORT
    public void registraRoyaltyReport(String idReport, String periodo, Double ricavi, Release release) throws Exception {
        if (idReport == null || idReport.trim().isEmpty()) {
            throw new Exception("L'ID del report non può essere vuoto.");
        }

        model.RoyaltyReport nuovoReport = new model.RoyaltyReport(idReport, periodo, ricavi, release);

        royaltyReportDAO.salvaRoyaltyReport(nuovoReport);
    }
    // --- METODI PER CAMPAGNE MARKETING ---
    public void registraCampagnaMarketing(String id, String piattaforma, Double costo, Dipartimento dipartimento, Release release) {
        if (costo > dipartimento.getBudgetAnnuale()) {
            System.out.println("ERRORE: Il costo della campagna supera il budget del dipartimento!");
            return;
        }

        CampagnaMarketing nuovaCampagna = new CampagnaMarketing(id, piattaforma, costo, release, dipartimento);
        campagnaMarketingDAO.salvaCampagnaMarketing(nuovaCampagna);

        Double nuovoBudget = dipartimento.getBudgetAnnuale() - costo;
        dipartimento.setBudgetAnnuale(nuovoBudget);

        System.out.println("Campagna Registrata! Nuovo budget dipartimento: " + dipartimento.getBudgetAnnuale());
    }

    public List<Dipartimento> getTuttiIDipartimenti() {

        return dipartimentoDAO.getTuttiIDipartimenti();
    }
}