package com.example.aplicatie_pentru_sala_de_fitness;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;


// GymAdapter leaga datele salii cu elementele de interfata din RecyclerView

// Adaptorul GymAdapter extinde RecyclerView.Adapter cu un GymViewHolder custom
public class GymAdapter extends RecyclerView.Adapter<GymAdapter.GymViewHolder> {
    private List<Gym> gymList;

    public GymAdapter(List<Gym> gyms) {
        this.gymList = gyms;
    }

    // GymViewHolder tine referinte catre fiecare element View al unui obiect de tip
    // gym(gym_item.xml) din interfata
    public static class GymViewHolder extends RecyclerView.ViewHolder {
        ImageView gymImage;
        TextView gymName;
        TextView gymSchedule;
        TextView gymFacilities;
        TextView gymContact;
        Button chooseButton;
        Button detailsButton;


        public GymViewHolder(View itemView) {
            super(itemView);
            gymImage = itemView.findViewById(R.id.gym_image);
            gymName = itemView.findViewById(R.id.gym_name);
            gymSchedule = itemView.findViewById(R.id.gym_schedule);
            gymFacilities = itemView.findViewById(R.id.gym_facilities);
            gymContact = itemView.findViewById(R.id.gym_contact);
            chooseButton = itemView.findViewById(R.id.choose_button);
            detailsButton = itemView.findViewById(R.id.details_button);
        }
    }

    // OnCreateViewHolder este apelata de RecyclerView atunci cand are nevoie
    // de un nou ViewHolder pentru a afisa un obiect de tip gym
    @Override
    public GymViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gym_item, parent, false);
        return new GymViewHolder(view);
    }


    // OnBimdViewHolder leaga datele salii cu elementele de interfata din RecyclerView
    @Override
    public void onBindViewHolder(GymViewHolder holder, int position) {
        // Formatare pentru a putea afisa facilitatile intr-un singur string
        String facilitiesText = String.join(", ", gymList.get(position).getFacilities());

        // Convert obiect de tip Map<String, Pair<String, String>> la String
        // pentru a putea fi afisat in TextView

        StringBuilder scheduleText = new StringBuilder();

        for (Map.Entry<String, Pair<String, String>> entry : gymList.get(position).getSchedule().entrySet()) {
            // Se adauga in StringBuilder fiecare entry din map
            // si se adauga un separator
            scheduleText.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().first)
                    .append(" - ")
                    .append(entry.getValue().second)
                    .append("\n");
        }

        // Setarea datelor salii in elementele de interfata
        Gym gym = gymList.get(position);
        holder.gymName.setText(gym.getName());
        holder.gymImage.setImageResource(gym.getImageResId());
        holder.gymSchedule.setText(scheduleText.toString());
        holder.gymFacilities.setText(facilitiesText);
        holder.gymContact.setText(gym.getContact());
    }

    @Override
    public int getItemCount() {
        return gymList.size();
    }

    // updateList permite actualizarea listei de salile afisate in RecyclerView
    public void updateList(List<Gym> newList) {
        gymList.clear();
        gymList.addAll(newList);
        notifyDataSetChanged();
    }

}
