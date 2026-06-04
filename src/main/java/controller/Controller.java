package controller;

import dao.ManagerDAO;
import dao.ManagerDAO;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    private ManagerDAO managerDAO;

    // --- Liste in memoria
    private List<Artista> artistiInMemoria;
    private List<Dipartimento> dipartimentiInMemoria;
    private List<CampagnaMarketing> campagneInMemoria;
    private List<Manager> managerInMemoria;

    public Controller() {

        this.managerDAO = new ManagerDAO();
        this.artistiInMemoria = new ArrayList<>();
        this.dipartimentiInMemoria = new ArrayList<>();
        this.campagneInMemoria = new ArrayList<>();
        this.managerInMemoria = new ArrayList<>();

        // Qualche dipartimento di default per i test
        dipartimentiInMemoria.add(new Dipartimento("D01", "Marketing Digitale", 50000.0));
        dipartimentiInMemoria.add(new Dipartimento("D02", "Eventi Live", 120000.0));
    }

    // --- METODI PER ARTISTA ---
    public void registraNuovoArtista(String id, String nomeArte, String genereMusicale, LocalDate dataInizio, LocalDate dataFine, Manager manager) {
        Artista nuovoArtista = new Artista(id, nomeArte, genereMusicale, dataInizio, dataFine, manager);
        artistiInMemoria.add(nuovoArtista);
        System.out.println("Artista Registrato: " + nuovoArtista.getNomeArte());
    }

    public List<Artista> getTuttiGliArtisti() {
        return artistiInMemoria;
    }

    // --- METODI PER MANAGER ---
    public void registraNuovoManager(String id, String nome, String cognome, LocalDate dataAssunzione, Double bonus) {

        Manager nuovoManager = new Manager(id, nome, cognome, dataAssunzione, bonus);


        managerDAO.salvaManager(nuovoManager);


        managerInMemoria.add(nuovoManager);

        System.out.println("Manager Registrato nel DB: " + nuovoManager.getNome() + " " + nuovoManager.getCognome());
    }

    public List<Manager> getTuttiIManager() {

        return managerDAO.getTuttiIManager();
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