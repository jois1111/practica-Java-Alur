package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.ErrorEnConversionDeDuracionExeption;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalHttp {
    public static void main(String[] args) throws IOException, InterruptedException {
        String claveConversor = "63b8da58e2a753b83c82188f";
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


        Scanner lectura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        while (true){
            System.out.println("Escriba el nombre de una pelicula: ");
            var busqueda = lectura.nextLine();
            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }

            String busquedaEncoder = URLEncoder.encode(busqueda,"UTF-8");
            String clave = "fcb1df68";
            String direccion = "https://www.omdbapi.com/?t="+busquedaEncoder+"&apikey="+clave;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                System.out.println("Omdb:"+miTituloOmdb);


                Titulo miTitulo = new Titulo(miTituloOmdb);
                System.out.println("mi titulo:"+miTitulo);

                titulos.add(miTitulo);
            }catch (NumberFormatException e){
                System.out.println("Ocurrió un error");
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println("Error en la URL");
            }catch (ErrorEnConversionDeDuracionExeption e){

                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                System.out.println("No se encontro informacion de la pelicula");
            }

        }
        System.out.println(titulos);
        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("Finalizo la ejecucion del programa");
    }
}
