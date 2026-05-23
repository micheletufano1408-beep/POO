package controller;

import model.Artista;
import model.Manager;
import model.CampagnaMarketing;
import model.Dipartimento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Artista> artistiInMemoria;
    private List<Dipartimento> dipartimentiInMemoria;
    private List<CampagnaMarketing> campagneInMemoria;
    private List<Manager> managerInMemoria;

    public Controller(){
        this.artistiInMemoria = new ArrayList<>();
        this.dipartimentiInMemoria = new ArrayList<>();
        this.campagneInMemoria = new ArrayList<>();
        this.managerInMemoria = new ArrayList<>();

        Dipartimento marketingDept = new Dipartimento("D01", "Ufficio Marketing", 50000.00);
        dipartimentiInMemoria.add(marketingDept);

        Manager m1 = new Manager("M01", "Michele", "Tufano", LocalDate.now(), 15.0);
        managerInMemoria.add(m1);
    }


    // SEZIONE ARTISTI

    public void registraNuovoArtista(String id, String nomeArte, String genereMusicale, LocalDate dataInizio, LocalDate dataFine, Manager manager) throws Exception{
        if (id == null || id.trim().isEmpty()){
            throw new Exception("L'ID Artista è obbligatorio.");
        }
        if (nomeArte == null || nomeArte.trim().isEmpty()) {
            throw new Exception("Il Nome d'Arte non può essere vuoto.");
        }
        if (genereMusicale == null || genereMusicale.trim().isEmpty()) {
            throw new Exception("Il Genere Musicale è obbligatorio.");
        }
        if (dataInizio == null || dataFine == null) {
            throw new Exception("Le date di inizio e fine contratto devono essere valide.");
        }
        if (dataFine.isBefore(dataInizio)) {
            throw new Exception("La data di fine contratto non può essere precedente alla data di inizio.");
        }

        for (Artista a : artistiInMemoria){
            if (a.getIdArtista().equalsIgnoreCase(id)){
                throw new Exception("Un artista con questo id esiste già nel sistema");
            }
        }

        Artista nuovoArtista = new Artista(id, nomeArte, genereMusicale, dataInizio, dataFine, manager);

        if (manager != null){
            manager.addArtista(nuovoArtista);
        }

        artistiInMemoria.add(nuovoArtista);
        System.out.println("[CONTROLLER LOG] Artista registrato con successo: " + nomeArte);
    }

    public List<Artista> getTuttiGliArtisti() {
        return this.artistiInMemoria;
    }


    // SEZIONE MANAGER

    public void registraNuovoManager(String id, String nome, String cognome, LocalDate dataAssunzione, Double bonus) throws Exception {
        if (id == null || id.trim().isEmpty() || nome == null || nome.trim().isEmpty()) {
            throw new Exception("ID e Nome del manager sono obbligatori.");
        }
        Manager nuovoManager = new Manager(id, nome, cognome, dataAssunzione, bonus);
        managerInMemoria.add(nuovoManager);
        System.out.println("[CONTROLLER LOG] Manager registrato: " + nome + " " + cognome);
    }

    public List<Manager> getTuttiIManager() {
        return this.managerInMemoria;
    }

    // SEZIONE CAMPAGNE MARKETING E DIPARTIMENTI

    public void registraCampagnaMarketing(String id, String piattaforma, Double costo, Dipartimento dipartimento) throws Exception {

        if (costo == null || costo <= 0) {
            throw new Exception("Il costo stimato deve essere maggiore di zero.");
        }
        if (dipartimento == null) {
            throw new Exception("Selezionare un dipartimento finanziatore.");
        }

        double speseAttuali = 0.0;
        for (CampagnaMarketing c : dipartimento.getCampagneFinanziate()) {
            speseAttuali += c.getCostoStimato();
        }

        if ((speseAttuali + costo) > dipartimento.getBudgetAnnuale()) {
            throw new Exception("Operazione negata: Il costo di " + costo +
                    " supera il budget residuo del dipartimento " +
                    dipartimento.getNomeDipartimento() +
                    " (Budget totale: " + dipartimento.getBudgetAnnuale() +
                    ", Già speso: " + speseAttuali + ")");
        }

        CampagnaMarketing nuovaCampagna = new CampagnaMarketing(id, piattaforma, costo, null, dipartimento);
        dipartimento.getCampagneFinanziate().add(nuovaCampagna);
        campagneInMemoria.add(nuovaCampagna);

        System.out.println("[CONTROLLER LOG] Campagna " + id + " creata. Costo approvato.");
    }
}