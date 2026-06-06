package gui;

import controller.Controller;
import model.Release;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class aggiungiRoyaltyReport {
    private JPanel mainPanel;
    private JTextField inserisciIdReport;
    private JTextField inserisciPeriodo;
    private JTextField inserisciRicavi;
    private JComboBox<Release> selezionaRelease;
    private JButton confermaButton;

    private Controller controller;
    private JFrame frame;

    public aggiungiRoyaltyReport(Controller controller, JFrame frameHome) {
        this.controller = controller;

        frame = new JFrame("Nuovo Royalty Report");
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

        for (Release r : controller.getTutteLeRelease()) {
            selezionaRelease.addItem(r);
        }

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaReport(frameHome);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void salvaReport(JFrame frameHome) {
        try {
            String id = inserisciIdReport.getText();
            String periodo = inserisciPeriodo.getText();
            Double ricavi = Double.parseDouble(inserisciRicavi.getText());

            Release releaseScelta = (Release) selezionaRelease.getSelectedItem();

            if (releaseScelta == null) {
                throw new Exception("Devi selezionare una Release!");
            }

            controller.registraRoyaltyReport(id, periodo, ricavi, releaseScelta);

            JOptionPane.showMessageDialog(mainPanel, "Royalty Report salvato con successo!");

            frameHome.setVisible(true);
            frame.dispose();

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(mainPanel, "Errore: I ricavi devono essere un numero.", "Errore", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}