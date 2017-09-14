package com.example.andrzejd.todo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Ustawienia extends AppCompatActivity {

            Button priorytet, dedline, czas, zapis, wyczysc;
            int Ipriorytet, Idedline, Iczas;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ustawienia);

                priorytet = (Button) findViewById(R.id.Upriorytet);
                priorytet.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setPriorytet();
                    }
                });

                dedline = (Button) findViewById(R.id.Udedline);
                dedline.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setDedline();
                    }
                });

                czas = (Button) findViewById(R.id.Uczas);
                czas.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        setCzas();
                    }
                });

                zapis = (Button) findViewById(R.id.Zapisz);
                zapis.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        zapisz();
                    }
                });

                wyczysc = (Button) findViewById(R.id.Wyczysc);
                wyczysc.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        wyczysc();
                    }
                });

                wczytaj();
            }

            private void zmien(int ip, int id , int ic)
            {

                Ipriorytet = ip;
                Idedline = id;
                Iczas = ic;
                priorytet.setText(Ipriorytet+"");
                dedline.setText(Idedline+"");
                czas.setText(Iczas+"");
            }


            public void setPriorytet() {
                final CharSequence[] prio = new CharSequence[3];
                for (int i = 1; i < 4; i++) {
                    prio[i-1] = "" + i;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Priorytet");
                builder.setItems(prio, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which+1 == Ipriorytet)
                        {
                            zmien(Ipriorytet,Idedline,Iczas);
                        }
                        else
                        {
                            if(which+1 == Idedline)
                            {
                                zmien(Idedline,Ipriorytet,Iczas);
                            }
                            else
                            {
                                if(which+1 == Iczas)
                                    zmien(Iczas,Idedline,Ipriorytet);
                            }
                        }
                    }
                });
                builder.create();
                builder.show();
            }

            public void setDedline() {
                final CharSequence[] prio = new CharSequence[3];
                for (int i = 1; i < 4; i++) {
                    prio[i-1] = "" + i;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Dedline");
                builder.setItems(prio, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which+1 == Ipriorytet)
                        {
                            zmien(Idedline,Ipriorytet,Iczas);
                        }
                        else
                        {
                            if(which+1 == Idedline)
                            {
                                zmien(Ipriorytet,Idedline,Iczas);
                            }
                            else
                            {
                                if(which+1 == Iczas)
                                    zmien(Ipriorytet,Iczas,Idedline);
                            }
                        }
                    }
                });
                builder.create();
                builder.show();
            }

            public void setCzas() {
                final CharSequence[] prio = new CharSequence[3];
                for (int i = 1; i < 4; i++) {
                    prio[i - 1] = "" + i;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Czas wykonania");
                builder.setItems(prio, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which+1 == Ipriorytet)
                        {
                            zmien(Iczas,Idedline,Ipriorytet);
                        }
                        else
                        {
                            if(which+1 == Idedline)
                            {
                                zmien(Ipriorytet,Iczas,Idedline);
                            }
                            else
                            {
                                if(which+1 == Iczas)
                                    zmien(Ipriorytet,Idedline,Iczas);
                            }
                        }
                    }
                });
                builder.create();
                builder.show();
            }

            public void zapisz() {
                String nazwaPliku = "Ustawienia";
                FileOutputStream out;
                PrintWriter o = null;

                try {
                    out = openFileOutput(nazwaPliku, Context.MODE_WORLD_WRITEABLE);
                    o = new PrintWriter(out);

                    o.println(Ipriorytet);
                    o.println(Idedline);
                    o.println(Iczas);


                    Context context = getApplicationContext();
                    CharSequence text = "Zapisano";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                finally {
                    o.close();
                }
            }

            public void wczytaj()
            {
                zmien(1,2,3);
                String nazwaPliku = "Ustawienia";
                FileInputStream in;
                Scanner i = null;

                try {
                    in = openFileInput(nazwaPliku);
                    i = new Scanner(in);

                    Ipriorytet = i.nextInt();
                    Idedline = i.nextInt();
                    Iczas = i.nextInt();

                    i.close();

                    zmien(Ipriorytet,Idedline,Iczas);


                    Context context = getApplicationContext();
                    CharSequence text = "Wczytano";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                } catch (FileNotFoundException e) {
                    //e.printStackTrace();
                }
                catch(Exception e)
                {

                }





            }



    public void wyczysc(String nazwaPliku) {

        FileOutputStream out;
        PrintWriter o = null;

        try {
            out = openFileOutput(nazwaPliku, Context.MODE_WORLD_WRITEABLE);
            o = new PrintWriter(out);
            o.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void wyczysc() {

        String nazwaPliku = "Ustawienia";
        FileOutputStream out;
        PrintWriter o = null;

        try {
            out = openFileOutput(nazwaPliku, Context.MODE_WORLD_WRITEABLE);
            o = new PrintWriter(out);
            o.println(1);
            o.println(2);
            o.println(3);
            o.close();
            zmien(1, 2, 3);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        wyczysc("Zadania");
        wyczysc("ListaSukcesow");
        wyczysc("Nagrody");


            }
}