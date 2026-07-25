package gui;

import controller.Controller;
import eccezioni.DatabaseException;
import eccezioni.DatiInvalidiException;
import model.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class aggiungiRelease {
    private JPanel mainPanel;
    private JTextField inserisciCodice;
    private JTextField inserisciTitolo;
    private JTextField inserisciData;
    private JComboBox<String> inserisciFormato;
    private JComboBox<String> inserisciStato;
    private JComboBox<Artista> selezionaArtista;
    private JButton confermaButton;

    private Controller controller;
    private JFrame frame;

    public aggiungiRelease(Controller controller, JFrame frameHome) {
        this.controller = controller;

        frame = new JFrame("Pubblicazione Nuova Release");
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

        inserisciFormato.addItem("Album");
        inserisciFormato.addItem("EP");
        inserisciFormato.addItem("Singolo");

        inserisciStato.addItem("In lavorazione");
        inserisciStato.addItem("Rilasciato");
    try {
        for (Artista a : controller.getTuttiGliArtisti()) {
            selezionaArtista.addItem(a);
        }
    } catch (DatabaseException ex) {
        JOptionPane.showMessageDialog(frame, "Impossibile caricare i dati dal database per i menu a tendina.", "Errore DB", JOptionPane.ERROR_MESSAGE);
    }

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaRelease(frameHome);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void salvaRelease(JFrame frameHome) {
        try {
            String codice = inserisciCodice.getText();
            String titolo = inserisciTitolo.getText();
            LocalDate data = LocalDate.parse(inserisciData.getText());

            String formato = (String) inserisciFormato.getSelectedItem();
            String stato = (String) inserisciStato.getSelectedItem();
            Artista artistaScelto = (Artista) selezionaArtista.getSelectedItem();

            controller.registraNuovaRelease(codice, titolo, formato, data, stato, artistaScelto);

            JOptionPane.showMessageDialog(mainPanel, "Release '" + titolo + "' registrata con successo!", "Successo", JOptionPane.INFORMATION_MESSAGE);

            frameHome.setVisible(true);
            frame.dispose();

        } catch (java.time.format.DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(mainPanel, "Formato data errato!\nAssicurati di usare il formato YYYY-MM-DD.", "Errore Data", JOptionPane.WARNING_MESSAGE);
        }catch (DatiInvalidiException ex) {
            JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "Dati Mancanti", JOptionPane.WARNING_MESSAGE);
        }
        catch (DatabaseException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Errore di connessione al DB:\n" + ex.getMessage(), "Errore Database", JOptionPane.ERROR_MESSAGE);
        }
    }
}