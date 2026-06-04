package controller;

import dao.ManagerDAO;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    private ManagerDAO managerDAO;

    private List<Artista> artistiInMemoria;
    private List<Dipartimento> dipartimentiInMemoria;
    private List<CampagnaMarketing> campagneInMemoria;
    private List<Manager> managerInMemoria;
    private List<Tecnico> tecniciInMemoria;
    private List<Release> releaseInMemoria;


    public Controller() {

        this.managerDAO = new ManagerDAO();
        this.artistiInMemoria = new ArrayList<>();
        this.dipartimentiInMemoria = new ArrayList<>();
        this.campagneInMemoria = new ArrayList<>();
        this.managerInMemoria = new ArrayList<>();
        this.tecniciInMemoria = new ArrayList<>();
        this.releaseInMemoria = new ArrayList<>();

        // Qualche dipartimento di default per i test
        dipartimentiInMemoria.add(new Dipartimento("D01", "Marketing Digitale", 50000.0));
        dipartimentiInMemoria.add(new Dipartimento("D02", "Eventi Live", 120000.0));
    }

    //METODI PER ARTISTA
    public void registraNuovoArtista(String id, String nomeArte, String genereMusicale, LocalDate dataInizio, LocalDate dataFine, Manager manager) {
        Artista nuovoArtista = new Artista(id, nomeArte, genereMusicale, dataInizio, dataFine, manager);
        artistiInMemoria.add(nuovoArtista);
        System.out.println("Artista Registrato: " + nuovoArtista.getNomeArte());
    }

    public List<Artista> getTuttiGliArtisti() {

        return artistiInMemoria;
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

        tecniciInMemoria.add(nuovoTecnico);

        System.out.println("[CONTROLLER LOG] Tecnico registrato: " + nome + " " + cognome + " | Ruolo: " + ruoloSpecializzato);
    }
    //METODI PER RELEASE
    public void registraNuovaRelease(String codice, String titolo, String tipoFormato, LocalDate dataPubblicazione, String stato, Artista artista) throws Exception {
        if (codice == null || codice.trim().isEmpty() || artista == null) {
            throw new Exception("Codice e Artista sono campi obbligatori.");
        }

        Release nuovaRelease = new Release(codice, titolo, tipoFormato, dataPubblicazione, stato, artista);
        releaseInMemoria.add(nuovaRelease);

        System.out.println("[CONTROLLER LOG] Release registrata: " + titolo + " dell'artista " + artista.getNomeArte());
    }

    public List<Release> getTutteLeRelease() {

        return releaseInMemoria;
    }
    //METODI PER ROYALTY REPORT
    public void registraRoyaltyReport(String idReport, String periodo, Double ricavi, Release release) throws Exception {
        if (idReport == null || idReport.trim().isEmpty()) {
            throw new Exception("L'ID del report non può essere vuoto.");
        }

        model.RoyaltyReport nuovoReport = new model.RoyaltyReport(idReport, periodo, ricavi, release);

        System.out.println("[CONTROLLER LOG] Report registrato: " + periodo + " | Ricavi: €" + ricavi + " | Release: " + release.getTitolo());
    }
    // --- METODI PER CAMPAGNE MARKETING ---
    public void registraCampagnaMarketing(String id, String piattaforma, Double costo, Dipartimento dipartimento, Release release) {
        if (costo > dipartimento.getBudgetAnnuale()) {
            System.out.println("ERRORE: Il costo della campagna supera il budget del dipartimento!");
            return;
        }

        CampagnaMarketing nuovaCampagna = new CampagnaMarketing(id, piattaforma, costo, release, dipartimento);
        campagneInMemoria.add(nuovaCampagna);

        Double nuovoBudget = dipartimento.getBudgetAnnuale() - costo;
        dipartimento.setBudgetAnnuale(nuovoBudget);

        System.out.println("Campagna Registrata! Nuovo budget dipartimento: " + dipartimento.getBudgetAnnuale());
    }

    public List<Dipartimento> getTuttiIDipartimenti() {
        return dipartimentiInMemoria;
    }
}