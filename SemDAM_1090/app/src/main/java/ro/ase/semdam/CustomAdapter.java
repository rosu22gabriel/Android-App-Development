package ro.ase.semdam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<BiletAvion> {

    private Context context;

    private int resource;

    private List<BiletAvion> listaBilete;

    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<BiletAvion> lista, LayoutInflater layoutInflater) {
        super(context, resource, lista);
        this.context = context;
        this.resource = resource;
        this.listaBilete = lista;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);

        BiletAvion biletAvion = listaBilete.get(position);

        if(biletAvion!=null)
        {
            TextView tvDestinatie = view.findViewById(R.id.tvDestinatie);
            tvDestinatie.setText(biletAvion.getDestinatie());

            TextView tvDataZbor = view.findViewById(R.id.tvDataZbor);
            tvDataZbor.setText(biletAvion.getDataZbor().toString());

            TextView tvPret = view.findViewById(R.id.tvPret);
            tvPret.setText(String.valueOf(biletAvion.getPret()));

            TextView tvCompanie = view.findViewById(R.id.tvCompanie);
            tvCompanie.setText(biletAvion.getCompanie());

            TextView tvClasaBilet = view.findViewById(R.id.tvClasaBilet);
            tvClasaBilet.setText(biletAvion.getClasaBilet());
        }

        return view;
    }
}
