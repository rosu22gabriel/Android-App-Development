package com.example.redoexemplucurs1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<BiletAvion> {
    private Context context;
    private int resource;

    private List<BiletAvion> listaBilete;

    private LayoutInflater layoutInflater;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<BiletAvion> lista, LayoutInflater layoutInflater) {
        super(context, resource, lista);
        this.context = context;
        this.resource = resource;
        this.listaBilete = listaBilete;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        BiletAvion biletAvion = listaBilete.get(position);

        if(biletAvion!=null) {
            TextView tvDestinatie = view.findViewById(R.id.textView);
            tvDestinatie.setText(biletAvion.getDestinatie());

            TextView tvDataZbor = view.findViewById(R.id.textView2);
            tvDataZbor.setText(biletAvion.getDataZbor().toString());

            TextView tvPret = view.findViewById(R.id.textView3);
            tvPret.setText(String.valueOf(biletAvion.getPret()));

            TextView tvCompanie = view.findViewById(R.id.textView4);
            tvCompanie.setText(biletAvion.getCompanie());

            TextView tvClasaBilet = view.findViewById(R.id.textView5);
            tvClasaBilet.setText(biletAvion.getClasaBilet());
        }
        return view;
    }
}
