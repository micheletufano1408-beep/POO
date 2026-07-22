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
    private JButton mostraArtistiButton;
    private JTextArea areaTestoArtisti;
    private JButton apriAggiungiManagerButton;
    private JButton assumiNuovoTecnicoButton;
    private JButton aggiungiReleaseButton;
    private JButton creaUnaCampagnaDiButton;
    private JButton aggiungiUnaRoyaltyReportButton;

    private static JFrame frameHome;
    private Controller controller;

    public Home(Controller controller) {
        this.controller = controller;

        aggiungiArtistaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aggiungiArtista(Home.this.controller, frameHome);
                frameHome.setVisible(false);
            }
        });

        mostraArtistiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<Artista> lista = Home.this.controller.getTuttiGliArtisti();


                if (lista.isEmpty()) {
                    areaTestoArtisti.setText("Nessun artista presente nel sistema.");
                } else {

                    areaTestoArtisti.setText("--- ELENCO ARTISTI ---\n\n");
                    for (Artista a : lista) {
                        areaTestoArtisti.append("ID: " + a.getIdArtista() +
                                " | Nome: " + a.getNomeArte() +
                                " | Genere: " + a.getGenereMusicale() + "\n" +
                                " | Manager: " + (a.getManager() != null ? a.getManager().toString() : "Nessuno") + "\n");
                    }
                }
            }
        });
apriAggiungiManagerButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       new aggiungiManager(Home.this.controller, frameHome);
       frameHome.setVisible(false);
    }

});
        assumiNuovoTecnicoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aggiungiTecnico(Home.this.controller, frameHome);
                frameHome.setVisible(false);
            }
        });
        aggiungiReleaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aggiungiRelease(Home.this.controller, frameHome);
                frameHome.setVisible(false);
            }
        });
        creaUnaCampagnaDiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aggiungiCampagnaMarketing(Home.this.controller, frameHome);
                frameHome.setVisible(false);
            }
        });
        aggiungiUnaRoyaltyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new aggiungiRoyaltyReport(Home.this.controller, frameHome);
                frameHome.setVisible(false);
            }
        });
        frameHome = new JFrame("Home Page Discografica");
        frameHome.setContentPane(this.mainPanel);
        frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHome.setSize(750, 500);
        frameHome.setLocationRelativeTo(null); // Centra lo schermo
        frameHome.setVisible(true);
    }
}