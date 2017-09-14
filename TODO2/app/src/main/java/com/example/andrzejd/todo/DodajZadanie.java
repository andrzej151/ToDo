package com.example.andrzejd.todo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;


public class DodajZadanie extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final int DATE_DIALOG_ID = 0;
    private TextView dedline, ldedline, lpriorytet;
    private EditText nagroda, zadanieGlowne, hour, min;
    public ImageView priorytet;
    private Button bdodajZadanie, bzapisz;
    private RecyclerView lista;
    Zadanie zadanie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zadanie);

        zadanie = new Zadanie();
        /********************* Inicjalizacja *****************/
        nagroda = (EditText) findViewById(R.id.Nagroda);
        zadanieGlowne = (EditText) findViewById(R.id.ZadanieGlowne);
        hour = (EditText) findViewById(R.id.CzasHour);
        min = (EditText) findViewById(R.id.CzasMin);

    /********************* Inicjalizacja *****************/

        /********************* Data *****************/

        dedline = (TextView) findViewById(R.id.Dedline);
        ldedline = (TextView) findViewById(R.id.LDedline);


        ldedline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        dedline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        zadanie.setYear(c.get(Calendar.YEAR));
        zadanie.setMonth(c.get(Calendar.MONTH));
        zadanie.setDay(c.get(Calendar.DAY_OF_MONTH));

        updateDate();
        /******************** Data *******************/
        /******************** Priorytet *******************/

        lpriorytet = (TextView) findViewById(R.id.LPriorytet);
        lpriorytet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPriorytet();
            }
        });

        priorytet = (ImageView) findViewById(R.id.Priorytet);
        priorytet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setPriorytet();
            }
        });
        priorytet.setImageResource(zadanie.getObrazekPriorytet());
        /******************** Priorytet *******************/
        /******************** Nowe Zadania Etapy *******************/
        bdodajZadanie = (Button) findViewById(R.id.DodajZadanie);
        bdodajZadanie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dodajZadanie();
            }
        });
        /******************** Nowe Zadania Etapy *******************/
        /******************** Zapis *******************/
        bzapisz = (Button) findViewById(R.id.Zapisz);
        bzapisz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zapisz();
            }
        });
        /******************** Zapis *******************/


    }



    /******************** Data *******************/
    private void updateDate() {
        dedline.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(zadanie.getDay()).append("-")
                        .append(zadanie.getMonth() + 1).append("-")
                        .append(zadanie.getYear()));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        zadanie.getYear(), zadanie.getMonth(), zadanie.getDay());
        }
        return null;
    }

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    zadanie.setYear(year);
                    zadanie.setMonth(monthOfYear);
                    zadanie.setDay(dayOfMonth);
                    updateDate();
                }
            };

    /******************** Data *******************/
    /******************** Priorytet *******************/
    public void setPriorytet() {
        final CharSequence[] prio = new CharSequence[10];
        for (int i = 1; i < 11; i++) {
            prio[i - 1] = "" + i;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Priorytet");
        builder.setItems(prio, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                zadanie.setPriorytet(which + 1);
                priorytet.setImageResource(zadanie.getObrazekPriorytet());

            }
        });
        builder.create();
        builder.show();
    }
    /******************** Priorytet *******************/
    /******************** Nowe Zadania Etapy *******************/

    private void dodajZadanie() {
        Intent intent = new Intent(this, DodajEtap.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {


        // sprawdzamy czy przyszło odpowiednie żądanie
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // sprawdzamy czy intencja przyniosła odpowiednią wiadomość
           Zadanie etap = new Zadanie();
            Bundle dane = intent.getExtras();
            if (dane != null) {
                etap.setZadanieGlowne(dane.getString("Zadanie", ""));
                etap.setHour(dane.getInt("Hour", 0));
                etap.setMin(dane.getInt("Min", 0));
                etap.setPriorytet(dane.getInt("Priorytet", 10));
                zadanie.setZadanie(etap);
            }
            lista();
        }
    }
    private void lista() {
        lista = (RecyclerView) findViewById(R.id.ListaZadan);
        // w celach optymalizacji
        lista.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        lista.setLayoutManager(new LinearLayoutManager(this));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
            lista.setItemAnimator(new DefaultItemAnimator());

        // tworzymy źródło danych - tablicę z artykułami

        // tworzymy adapter oraz łączymy go z RecyclerView
        lista.setAdapter(new EtapAdapter(zadanie.getEtapy(), lista));
    }

    /******************** Nowe Zadania Etapy *******************/

    /******** Zapis ******/

    public void zapisz() {
        String nazwaPliku = "Zadania";
        FileOutputStream out;
        PrintWriter o = null;
        pobierz();

        try {
            out = openFileOutput(nazwaPliku, Context.MODE_APPEND);
            o = new PrintWriter(out);
            pobierz();
            o = zadanie.zapisz(o);


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

    private void pobierz() {

        zadanie.setZadanieGlowne(zadanieGlowne.getText().toString());
        zadanie.setNagroda(nagroda.getText().toString());
        zadanie.setHour(Integer.parseInt(hour.getText().toString()));
        zadanie.setMin(Integer.parseInt(min.getText().toString()));

    }
    /******** Zapis ******/


}//koniec