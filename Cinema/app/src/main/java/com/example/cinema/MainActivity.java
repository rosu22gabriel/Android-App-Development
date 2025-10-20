package com.example.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    Button button2;
    String value="hello man";

    Cinema cinema;
    Film film;
    List<Actor> listaActori = new ArrayList<>();
    List<Film> tabelaFilme = new ArrayList<>();

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

        tabelaFilme.add(film);

        cinema = new Cinema("Unirea", "Pietonalul Unirii Botosani", tabelaFilme);

        List<String> output = listaActori.stream()
                .filter(actor -> actor.getVarsta() < 60)
                .map(Actor::getNume).collect(Collectors.toList());

        Log.d("Cinema si film", "Cinema: " + cinema.getNume() + "ruleaza filmele: " + cinema.getTabelaFilme());

        Log.d("Actor cu varsta mai mica de 40 de ani:", "" + output);

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