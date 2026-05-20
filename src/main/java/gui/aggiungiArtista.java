package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class aggiungiArtista {
    private JPanel mainPanel;
    private JTextField aggiungiArtistaAllaListaTextField;
    private JButton aggiungiQuestoArtistaAllaListaButton;
    private JTextField inserisciIdArtista;
    private JTextField inserisciNomeArte;
    private JTextField inserisciGenereMusicale;
    private JTextField inserisciDataInizio;
    private JTextField inserisciDataFine;

    private Controller controller;

    public aggiungiArtista(Controller controller) {
        this.controller = controller;

        aggiungiQuestoArtistaAllaListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaDatiArtista();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void salvaDatiArtista() {
        try {

            String id = inserisciIdArtista.getText();
            String nome = inserisciNomeArte.getText();
            String genere = inserisciGenereMusicale.getText();


            LocalDate inizio = LocalDate.parse(inserisciDataInizio.getText());
            LocalDate fine = LocalDate.parse(inserisciDataFine.getText());


            controller.registraNuovoArtista(id, nome, genere, inizio, fine, null);


            JOptionPane.showMessageDialog(mainPanel, "Artista " + nome + " aggiunto con successo!");


            inserisciIdArtista.setText("");
            inserisciNomeArte.setText("");
            inserisciGenereMusicale.setText("");
            inserisciDataInizio.setText("");
            inserisciDataFine.setText("");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(mainPanel,
                    "Errore nell'inserimento: " + ex.getMessage() + "\n\nRicorda di inserire le date nel formato YYYY-MM-DD",
                    "Errore di Validazione",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}