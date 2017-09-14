package com.example.andrzejd.todo;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ListaSukcesow extends AppCompatActivity {

    RecyclerView lista;
    ArrayList<Zadanie> zadania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sukcesow);
        zadania = new ArrayList<Zadanie>();
        wczytaj();
        list();

    }

     public void list() {
        lista = (RecyclerView) findViewById(R.id.ListaToDo);
        // w celach optymalizacji
        lista.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        lista.setLayoutManager(new LinearLayoutManager(this));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
        lista.setItemAnimator(new DefaultItemAnimator());

        // tworzymy źródło danych - tablicę z artykułami

        // tworzymy adapter oraz łączymy go z RecyclerView
        lista.setAdapter(new ZadanieAdapter(zadania, lista));
    }

    /******** Odczyt ******/
    public void wczytaj() {
        String nazwaPliku = "Zadania";
        FileInputStream in;
        Scanner i;
        Zadanie zad;

        try {

            in = openFileInput(nazwaPliku);
            i = new Scanner(in);
            // wielkosc = i.nextInt();
            zadania = new ArrayList<>();
            while (i.hasNext()) {
                zad = new Zadanie();
                i = zad.wczytaj(i);
                zadania.add(zad);
            }
            i.close();

            Context context = getApplicationContext();
            CharSequence text = "Wczytano!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /******** Odczyt ******/
    }
}
