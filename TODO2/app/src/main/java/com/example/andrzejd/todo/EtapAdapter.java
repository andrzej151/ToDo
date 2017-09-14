package com.example.andrzejd.todo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EtapAdapter extends RecyclerView.Adapter {

    // źródło danych
    private ArrayList<Zadanie> zrudlo = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;

    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView zadanieGlowne;
        public TextView czswykonania;
        public ImageView priorytet;

        public MyViewHolder(View pItem) {
            super(pItem);
            zadanieGlowne = (TextView) pItem.findViewById(R.id.ZadanieGlowne);
            czswykonania = (TextView) pItem.findViewById(R.id.CzasWykonania);
            priorytet = (ImageView) pItem.findViewById(R.id.Priorytet);

        }
    }

    // konstruktor adaptera
    public EtapAdapter(ArrayList<Zadanie> pArticles, RecyclerView pRecyclerView){
        zrudlo = pArticles;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.zadanie_etap, viewGroup, false);

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);

                // usuwamy element ze źródła danych
                zrudlo.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                notifyItemRemoved(positionToDelete);
            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        Zadanie zad = zrudlo.get(i);
        ((MyViewHolder) viewHolder).priorytet.setImageResource(Zadanie.getObrazekPriorytet(zad.getPriorytet()));
        ((MyViewHolder) viewHolder).zadanieGlowne.setText(zad.getZadanieGlowne());
        ((MyViewHolder) viewHolder).czswykonania.setText(zad.getTime());
    }

    @Override
    public int getItemCount() {
        return zrudlo.size();
    }
}
