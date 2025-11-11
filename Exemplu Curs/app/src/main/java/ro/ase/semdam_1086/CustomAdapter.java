package ro.ase.semdam_1086;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<Avion> {

    private Context context;
    private int resource;
    private List<Avion> avionList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(@NonNull Context context, int resource, List<Avion> list, LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.avionList = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);

        Avion avion = avionList.get(position);

        if(avion!=null)
        {
            TextView tvProducator = view.findViewById(R.id.tvProducator);
            tvProducator.setText(avion.getProducator());

            TextView tvDataFabricatie = view.findViewById(R.id.tvDataFabricatie);
            tvDataFabricatie.setText(avion.getDataFabricatiei().toString());

            TextView tvNrLocuri = view.findViewById(R.id.tvNrLocuri);
            tvNrLocuri.setText(String.valueOf(avion.getNrLocuri()));

            TextView tvModel = view.findViewById(R.id.tvModel);
            tvModel.setText(avion.getModel());

            TextView tvIsDefect = view.findViewById(R.id.tvIsDefect);
            if (avion.isEsteDefect()) {
                tvIsDefect.setText("DA");
            } else {
                tvIsDefect.setText("NU");
            }
        }

        return view;
    }
}
