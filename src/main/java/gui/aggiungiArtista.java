package gui;

import controller.Controller;
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

        for (Manager m : controller.getTuttiIManager()) {
            inserisciManager.addItem(m);
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

            JOptionPane.showMessageDialog(mainPanel, "Artista " + nome + " aggiunto con successo!");

            frameHome.setVisible(true);
            frame.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Errore nell'inserimento: " + ex.getMessage() + "\n\n(Formato date richiesto: YYYY-MM-DD)",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}