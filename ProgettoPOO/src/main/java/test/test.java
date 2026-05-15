package test;

import model.Artista;
import java.lang.String;

public class test
{
    static void main(String[] args)
    {
        Artista michele = new Artista("Ciccio", "bello", "2026-01-01", "2030-01-01");
        System.out.println("L'artista creato è" + michele.getNomeArte());
    }
}
