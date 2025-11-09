package com.example.aplicatie_pentru_sala_de_fitness;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class BecomeMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_become_member);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // RecyclerView pentru afisarea listei de sali din orasul selectat (Recycler view pentru afisarea unor itemi mai complecsi -> poza, nume, adresa)
        RecyclerView gymsRecyclerView = findViewById(R.id.gyms_recycler_view_id);

        gymsRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Lista cu toate salile disponibile (nefiltrate)
        List<Gym> allGyms = new ArrayList<>();

        // Lista cu salile afisate in RecyclerView (filtrate)
        List<Gym> shownGyms = new ArrayList<>();


        allGyms.add(new Gym(
                "City Gym",
                "Strada Florilor Nr. 4",
                "Iasi",
                Map.of(
                        "Luni - Vineri", new Pair<>("8:00", "20:00"),

                        "Sambata - Duminica", new Pair<>("10:00", "18:00")
                ),
                "0744 123 456",
                List.of("Fitness", "Group Classes", "Cycling", "Cardio"),
                R.drawable.ic_gym_placeholder,
                "Basic"
                ));

        allGyms.add(new Gym(
                "Downtown Fitness",
                "Bulevardul Ion Mihalache",
                "Bucuresti",
                Map.of(
                        "Luni - Vineri", new Pair<>("8:00", "20:00"),

                        "Sambata - Duminica", new Pair<>("10:00", "18:00")
                ),
                "0744 123 456",
                List.of("Fitness", "Group Classes", "cycling", "Cardio"),
                R.drawable.ic_gym_placeholder,
                "Premium"
        ));

        // Se afiseaza toate salile
        shownGyms = new ArrayList<>(allGyms);


        // Adapter pentru RecyclerView
        GymAdapter gymAdapter = new GymAdapter(shownGyms);
        gymsRecyclerView.setAdapter(gymAdapter);


        // Spinner pentru selectarea unui oras

        // Inauntrul metodei onCreate()
        Spinner citiesSpinner = findViewById(R.id.cities_spinner_id);

        // Lista de orase selectabile in spinner
        String[] cities = {"Iasi", "Bucuresti", "Cluj-Napoca"};

        // Adapter pentru spinner
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item, // layout pentru elementele din spinner
                cities
        );

        // Setarea adapter-ului pentru spinner
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(cityAdapter);

        // Listener pentru selectarea unui oras din spinner
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // Supraincarcrea metodei onItemSelected()
            // Metoda se apeleaza atunci cand utilizatorul selecteaza un oras din spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = parent.getItemAtPosition(position).toString();

                // Se filtreaza salile in functie de orasul selectat
                List<Gym> filteredGyms = new ArrayList<>();
                for (Gym gym : allGyms) {
                    if (gym.getCity().equals(selectedCity)) {
                        filteredGyms.add(gym);
                    }
                }

                // Se actualizeaza lista de salile afisate in RecyclerView
                gymAdapter.updateList(filteredGyms);
            }

            // Supraincarcarea metodei onNothingSelected()
            // Metoda se apeleaza atunci cand utilizatorul nu selecteaza niciun oras
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}