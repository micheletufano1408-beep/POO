package gui;

import controller.Controller;
import eccezioni.DatabaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JFrame frame;
    private Controller controller;

    public Login(Controller controller) {
        this.controller = controller;
        inizializzaGUI();
    }

    private void inizializzaGUI() {

        frame = new JFrame("Login di Sistema");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Crea un margine di 10 pixel tra ogni elemento
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField campoUsername = new JTextField(15); // 15 è la larghezza del campo
        mainPanel.add(campoUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JPasswordField campoPassword = new JPasswordField(15);
        mainPanel.add(campoPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton accediButton = new JButton("Accedi");
        mainPanel.add(accediButton, gbc);

        frame.getRootPane().setDefaultButton(accediButton);


        accediButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = campoUsername.getText();
                String pass = new String(campoPassword.getPassword());

                try {
                    if (controller.effettuaLogin(user, pass)) {
                        frame.dispose();
                        new Home(controller);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Username o password errati!", "Credenziali errate", JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(frame, "Impossibile collegarsi al server.\nControlla che il database sia acceso.\nDettaglio: " + ex.getMessage(), "Errore di Connessione", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
}