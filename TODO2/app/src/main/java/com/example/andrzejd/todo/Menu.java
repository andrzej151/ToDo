package com.example.andrzejd.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

    private Button dodajZadanie, edytuj, toDo, listaSukcesow,ustawieia, autor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        dodajZadanie = (Button) findViewById(R.id.DodajZadanie);
        dodajZadanie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), DodajZadanie.class);
                startActivity(iii);
            }
        });

        toDo = (Button) findViewById(R.id.ToDo);
        toDo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), ToDo.class);
                startActivity(iii);
            }
        });

        edytuj = (Button) findViewById(R.id.Edytuj);
        edytuj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), Edytuj.class);
                startActivity(iii);
            }
        });

        listaSukcesow = (Button) findViewById(R.id.ListaSukcesow);
        listaSukcesow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), ListaSukcesow.class);
                startActivity(iii);
            }
        });

        ustawieia = (Button) findViewById(R.id.Ustawienia);
        ustawieia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), Ustawienia.class);
                startActivity(iii);
            }
        });

        autor = (Button) findViewById(R.id.Autor);
        autor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iii = new Intent(getApplicationContext(), Autor.class);
                startActivity(iii);
            }
        });

    }


}