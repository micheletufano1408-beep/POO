package gui;

import controller.Controller;
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
        frame.pack();
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

        for (Artista a : controller.getTuttiGliArtisti()) {
            selezionaArtista.addItem(a);
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

            JOptionPane.showMessageDialog(mainPanel, "Release '" + titolo + "' registrata con successo!");

            frameHome.setVisible(true);
            frame.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, "Errore nell'inserimento: " + ex.getMessage() + "\nControlla il formato della data (YYYY-MM-DD)", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}