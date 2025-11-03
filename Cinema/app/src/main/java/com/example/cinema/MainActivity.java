package com.example.cinema;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    Button button2;
    Spinner spinner;
    String value="hello man";

    Cinema cinema;
    Film film;
    List<Actor> listaActori = new ArrayList<>();
    List<Film> tabelaFilme = new ArrayList<>();
    List<Cinema> listaCinemauri = new ArrayList<>();

    List<String> listaNumeCinemauri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listaActori.add(new Actor(60, "Marlon Brando"));
        listaActori.add(new Actor(40, "Martin Sheen"));

        film = new Film("Apocalypse Now", 3.2F, listaActori, "Francis Ford Coppola");

        // the list will be empty after this call
        listaActori.clear();

        tabelaFilme.add(film);

        listaActori.add(new Actor(66, "Matthew Modine"));
        listaActori.add(new Actor(63, "Adam Baldwin"));

        film = new Film("Full Metal Jacket", 1.9F, listaActori, "Stanley Kubrick");

        tabelaFilme.add(film);

        cinema = new Cinema("Unirea", "Pietonalul Unirii Botosani", tabelaFilme);

        List<String> output = listaActori.stream()
                .filter(actor -> actor.getVarsta() < 60)
                .map(Actor::getNume).collect(Collectors.toList());

        List<String> output2 = tabelaFilme.stream()
                .filter(film -> film.getDenumire() == "Full Metal Jacket")
                .map(Film::getNumeRegizor)
                .collect(Collectors.toList());


        Log.d("Cinema si film", "Cinema: " + cinema.getNume() + "ruleaza filmele: " + cinema.getTabelaFilme());

        Log.d("Actor cu varsta mai mica de 40 de ani:", "" + output);

        Log.d("Regizorul filmului \"Full Metal Jacket\" este", "" + output2);


        //Cinema::getNume referinta la metoda
        listaNumeCinemauri = listaCinemauri.stream().map(Cinema::getNume).collect(Collectors.toList());

        // Splitting declaration and initialization is needed
        spinner = (Spinner)findViewById(R.id.spinner);

        // ArrayAdapter - sursa de date simpla
        // ArrayList, Array etc
        // Obiecte de orice tip

        // Constructor: - contextul(activitate)
        // - Identificatorul resursei de tip macheta asociat unui element din lista
        // - Identificatorul controlului de tip TextView (daca este cazul)
        // - siruri de elemente
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                listaNumeCinemauri
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adapter: asigura legatura dintre sursa de date si controale de tip lista de elemente
        // Gestioneaza componentele vizuale care trebuie afisate in cadrul colectiei
        // Creeaza pentru fiecare elemenet din sursa de date care trebuie afisat
        spinner.setAdapter(adapter);


        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("key", value);
                startActivity(intent);
            }
        });
    }
}