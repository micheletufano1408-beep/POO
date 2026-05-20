package gui;

import controller.Controller;
import model.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Home {
    private JPanel mainPanel;
    private JButton aggiungiArtistaButton;
    private JButton mostraArtistiButton; // Il tuo nuovo bottone
    private JTextArea areaTestoArtisti;  // Il tuo nuovo "schermo"

    private static JFrame frameHome;
    private Controller controller;

    public Home() {
        controller = new Controller();

        aggiungiArtistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameAggiungi = new JFrame("Inserimento nuovo Artista");
                aggiungiArtista schermataAggiungi = new aggiungiArtista(controller);

                frameAggiungi.setContentPane(schermataAggiungi.getMainPanel());
                frameAggiungi.setDefaultCloseOperation((JFrame.DISPOSE_ON_CLOSE));
                frameAggiungi.pack();
                frameAggiungi.setLocationRelativeTo(null);
                frameAggiungi.setVisible(true);
            }
        });

        mostraArtistiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chiediamo al controller di darci la lista
                List<Artista> lista = controller.getTuttiGliArtisti();

                // Se la lista è vuota
                if (lista.isEmpty()) {
                    areaTestoArtisti.setText("Nessun artista presente nel sistema.");
                } else {
                    // Se ci sono artisti, puliamo lo schermo e li stampiamo uno per riga
                    areaTestoArtisti.setText("--- ELENCO ARTISTI ---\n\n");
                    for (Artista a : lista) {
                        areaTestoArtisti.append("ID: " + a.getIdArtista() +
                                " | Nome: " + a.getNomeArte() +
                                " | Genere: " + a.getGenereMusicale() + "\n");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        frameHome = new JFrame("Home Page Discografica");
        frameHome.setContentPane(new Home().mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.setSize(500, 400);
        frameHome.setLocationRelativeTo(null);
        frameHome.setVisible(true);
    }
}