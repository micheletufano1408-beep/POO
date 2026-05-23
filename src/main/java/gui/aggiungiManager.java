package gui;

import controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class aggiungiManager {
    private JPanel mainPanel;
    private JTextField inserisciId;
    private JTextField inserisciNome;
    private JTextField inserisciCognome;
    private JTextField inserisciDataAssunzione;
    private JTextField inserisciBonus;
    private JButton confermaButton;

    private Controller controller;

    public aggiungiManager(Controller controller){
        this.controller = controller;

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaManager();
            }
        });
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
    private void salvaManager(){
        try {
            String id = inserisciId.getText();
            String nome = inserisciNome.getText();
            String cognome = inserisciCognome.getText();
            LocalDate data = LocalDate.parse(inserisciDataAssunzione.getText());
            Double bonus = Double.parseDouble(inserisciBonus.getText());

            controller.registraNuovoManager(id, nome, cognome, data, bonus);

            JOptionPane.showMessageDialog(mainPanel, "Manager " + nome + " " + cognome + " assunto con successo!");

            inserisciId.setText("");
            inserisciNome.setText("");
            inserisciCognome.setText("");
            inserisciDataAssunzione.setText("");
            inserisciBonus.setText("");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(mainPanel, "Errore nell'inserimento: " + ex.getMessage() + "\nVerifica il formato dei dati (Data : YYYY-MM-DD, Bonus: Numero)", "Errore",JOptionPane.ERROR_MESSAGE);
        }
    }
}
