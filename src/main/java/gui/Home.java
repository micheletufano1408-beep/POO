package gui;

import controller.Controller;
import eccezioni.DatabaseException;
import model.Artista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Home {
    private JPanel mainPanel;
    private JButton aggiungiArtistaButton;
    private JButton mostraArtistiButton;
    private JButton apriAggiungiManagerButton;
    private JButton assumiNuovoTecnicoButton;
    private JButton aggiungiReleaseButton;
    private JButton creaUnaCampagnaDiButton;
    private JButton aggiungiUnaRoyaltyReportButton;
    private JTable tabellaArtisti;

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
                try {
                    List<Artista> lista = Home.this.controller.getTuttiGliArtisti();

                    String[] colonne = {"ID", "Nome d'Arte", "Genere", "Manager"};

                    javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(colonne, 0) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    for (Artista a : lista) {
                        String nomeManager = (a.getManager() != null) ? a.getManager().toString() : "Nessuno";

                        Object[] riga = {
                                a.getIdArtista(),
                                a.getNomeArte(),
                                a.getGenereMusicale(),
                                nomeManager
                        };
                        tableModel.addRow(riga);
                    }

                    tabellaArtisti.setModel(tableModel);

                } catch (DatabaseException ex) {
                    JOptionPane.showMessageDialog(frameHome,
                            "Impossibile caricare gli artisti.\nDettaglio: " + ex.getMessage(),
                            "Errore di Connessione",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tabellaArtisti.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {

                    try {
                        int rigaSelezionata = tabellaArtisti.getSelectedRow();

                        if (rigaSelezionata != -1) {
                            String idArtista = tabellaArtisti.getValueAt(rigaSelezionata, 0).toString();
                            String nomeArtista = tabellaArtisti.getValueAt(rigaSelezionata, 1).toString();

                            List<model.Release> sueRelease = Home.this.controller.getReleaseDiArtista(idArtista);

                            if (sueRelease.isEmpty()) {
                                JOptionPane.showMessageDialog(frameHome,
                                        nomeArtista + " non ha ancora pubblicato nessuna Release.",
                                        "Discografia",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                StringBuilder messaggio = new StringBuilder("Release pubblicate da " + nomeArtista + ":\n\n");
                                for (model.Release r : sueRelease) {
                                    messaggio.append("🎵 ").append(r.getTitolo())
                                            .append(" (Formato: ").append(r.getTipoFormato()).append(")\n");
                                }
                                JOptionPane.showMessageDialog(frameHome,
                                        messaggio.toString(),
                                        "Discografia: " + nomeArtista,
                                        JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    } catch (eccezioni.DatabaseException ex) {
                        JOptionPane.showMessageDialog(frameHome,
                                "Impossibile caricare le release dell'artista dal Database.\n" + ex.getMessage(),
                                "Errore DB",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace(); // Scrive l'errore rosso nella console di IntelliJ
                        JOptionPane.showMessageDialog(frameHome,
                                "ERRORE DI SISTEMA NASCOSTO:\n" + ex.getMessage(),
                                "Bug Trovato!",
                                JOptionPane.ERROR_MESSAGE);
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