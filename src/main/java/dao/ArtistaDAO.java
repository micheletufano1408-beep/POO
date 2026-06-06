package dao;

import model.Artista;

import java.util.List;


public interface ArtistaDAO {
    void salvaArtista(Artista artista);
    List<Artista> getTuttiGliArtisti();
}
