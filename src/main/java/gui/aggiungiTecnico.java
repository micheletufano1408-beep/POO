package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class aggiungiTecnico {
    private JPanel mainPanel;
    private JButton aggiungiTecnicoButton;
    private JTextField inserisciId;
    private JTextField inserisciNome;
    private JTextField inserisciCognome;
    private JTextField inserisciDataAssunzione;
    private JTextField inserisciRuolo;

    private Controller controller;
    private JFrame frame;

    public aggiungiTecnico(Controller controller, JFrame frameHome) {
        this.controller = controller;

        frame = new JFrame("Assunzione nuovo Tecnico");
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
        aggiungiTecnicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaTecnico(frameHome);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void salvaTecnico(JFrame frameHome) {
        try {
            String id = inserisciId.getText();
            String nome = inserisciNome.getText();
            String cognome = inserisciCognome.getText();
            LocalDate data = LocalDate.parse(inserisciDataAssunzione.getText());
            String ruolo = inserisciRuolo.getText();

            controller.registraNuovoTecnico(id, nome, cognome, data, ruolo);

            JOptionPane.showMessageDialog(mainPanel, "Tecnico " + nome + " " + cognome + " assunto con successo!");

            frameHome.setVisible(true);
            frame.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, "Errore nell'inserimento: " + ex.getMessage() + "\nVerifica il formato dei dati (Data: YYYY-MM-DD)", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
