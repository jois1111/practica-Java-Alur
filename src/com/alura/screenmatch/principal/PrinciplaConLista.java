package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrinciplaConLista {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(7);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(8);
        var peliculaMathi = new Pelicula("Coco", 2017);
        peliculaMathi.evalua(10);
        Serie lost = new Serie("Lost", 2000);
        Pelicula p2 = miPelicula;

        peliculaMathi.setDuracionEnMinutos(105);
        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(peliculaMathi);
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(lost);


        for (Titulo item : lista) {
            System.out.println(item.getNombre());
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 2) {
                System.out.println(pelicula.getClasificacion());
            }
        }
        List<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Jhonny Deep");

        System.out.println(listaDeArtistas);
        Collections.sort(listaDeArtistas);
        System.out.println(listaDeArtistas);

        Collections.sort(lista);

        System.out.println("Lista de titulos ordenadas "+ lista);
        lista.sort(Comparator.comparing(titulo -> titulo.getFechaDeLanzamiento()));
        System.out.println("Lista ordenada por año de lanzamiento "+ lista);






    }
}
