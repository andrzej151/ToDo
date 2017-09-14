package com.example.andrzejd.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DodajEtap extends AppCompatActivity {


    private TextView lpriorytet;
    private EditText zadanie,godz,min;
    public ImageView priorytet;
    int prioryte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_etap);

        zadanie = (EditText) findViewById(R.id.Etap);
        godz = (EditText) findViewById(R.id.CzasHour);
        min = (EditText) findViewById(R.id.CzasMin);

        zadanie.setText("");
        godz.setText("0");
        min.setText("0");


        /******************** Priorytet *******************/

        lpriorytet = (TextView) findViewById(R.id.Lpriorytet);
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
        prioryte = 1;
        priorytet.setImageResource(Zadanie.getObrazekPriorytet(prioryte));
        /******************** Priorytet *******************/

    }
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
               prioryte = which+1;
                priorytet.setImageResource(Zadanie.getObrazekPriorytet(prioryte));

            }
        });
        builder.create();
        builder.show();
    }
    /******************** Priorytet *******************/

    public void closeActivity(View view) {
        // poniższa metoda kończy działanie aktywności
        finish();
    }

    @Override
    public void finish() {

        Intent intent = new Intent();
        Bundle dane=null;
        if(dane==null) {
            dane = new Bundle();
        }

        dane.putString("Zadanie", zadanie.getText().toString());
        dane.putInt("Hour", Integer.parseInt(godz.getText().toString()));
        dane.putInt("Min", Integer.parseInt(min.getText().toString()));
        dane.putInt("Priorytet", prioryte);
        intent.putExtras(dane);

        setResult(RESULT_OK, intent);
        super.finish();
    }
}
