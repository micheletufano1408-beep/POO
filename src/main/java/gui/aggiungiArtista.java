package gui;

import controller.Controller;
import eccezioni.DatabaseException;
import eccezioni.DatiInvalidiException;
import model.Artista;
import model.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class aggiungiArtista {
    private JPanel mainPanel;
    private JButton aggiungiQuestoArtistaAllaListaButton;
    private JTextField inserisciIdArtista;
    private JTextField inserisciNomeArte;
    private JTextField inserisciGenereMusicale;
    private JTextField inserisciDataInizio;
    private JTextField inserisciDataFine;
    private JComboBox<Manager> inserisciManager;

    private Controller controller;
    private JFrame frame;

    public aggiungiArtista(Controller controller, JFrame frameHome) {
        this.controller = controller;

        frame = new JFrame("Inserimento nuovo Artista");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frameHome.setVisible(true);
            }
        });

        try {
            for (Manager m : controller.getTuttiIManager()) {
                inserisciManager.addItem(m);
            }
        } catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(frame, "Impossibile caricare la lista dei manager dal database.", "Errore DB", JOptionPane.ERROR_MESSAGE);
        }

        aggiungiQuestoArtistaAllaListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaDatiArtista(frameHome);
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    private void salvaDatiArtista(JFrame frameHome){
        try{
            String id = inserisciIdArtista.getText();
            String nome = inserisciNomeArte.getText();
            String genere = inserisciGenereMusicale.getText();

            LocalDate inizio = LocalDate.parse(inserisciDataInizio.getText());
            LocalDate fine = LocalDate.parse(inserisciDataFine.getText());

            Manager managerSelezionato = (Manager) inserisciManager.getSelectedItem();

            controller.registraNuovoArtista(id, nome, genere, inizio, fine, managerSelezionato);

            JOptionPane.showMessageDialog(mainPanel, "Artista " + nome + " aggiunto con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

            frameHome.setVisible(true);
            frame.dispose();

        }
        catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Formato data errato!\nAssicurati di usare il formato YYYY-MM-DD.", "Errore Data", JOptionPane.WARNING_MESSAGE);
        }
        catch (DatiInvalidiException ex) {
            JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "Dati Mancanti", JOptionPane.WARNING_MESSAGE);
        }
        catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Errore di connessione al DB: \n" + ex.getMessage(), "Errore di Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
}