package com.example.exemplucursfaradepreciate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ComandaAdapter extends ArrayAdapter<Comanda> {
    private Context context;
    private int resource;
    private List<Comanda> comenzi;

    private LayoutInflater inflater;


    public ComandaAdapter(@NonNull Context context, int resource, @NonNull List<Comanda> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.comenzi = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Inflate la layout-ul randului daca nu exista deja
        View view = inflater.inflate(resource, parent, false);

        // Se ia obiectul curent
        Comanda comanda = comenzi.get(position);
        if (comanda != null) {
            // Se leaga componentele vizuale
            TextView tvClient = view.findViewById(R.id.tv_client);
            TextView tvAdresa = view.findViewById(R.id.tv_adresa);
            TextView tvPret = view.findViewById(R.id.tv_pret);

            // Se populeaza cu date
            tvClient.setText(comanda.getClient());
            tvAdresa.setText(comanda.getAdresa());
            tvPret.setText(String.valueOf(comanda.getPret()) + " RON");
        }
        return view;
    }
}
