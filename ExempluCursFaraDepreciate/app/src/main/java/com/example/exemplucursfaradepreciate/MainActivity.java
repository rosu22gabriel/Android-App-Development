package com.example.exemplucursfaradepreciate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {

    public static final String EDIT_BILET = "editBilet";
    private ListView lvComenzi;
    private List<Comanda> listaComenzi = new ArrayList<>();
    private AppDatabase database;

    // Definim launcher-ul ca variabila de instanta
    private ActivityResultLauncher<Intent> addLauncher;

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

        lvComenzi = findViewById(R.id.lv_comenzi); // id-ul din activity_main.xml
        database = AppDatabase.getInstanta(getApplicationContext());

        // Se incarca datele din Room
        listaComenzi = database.getComandaDao().getAll();

        // Se seteaza adapterul
        ComandaAdapter adapter = new ComandaAdapter(getApplicationContext()
        , R.layout.item_comanda, listaComenzi, getLayoutInflater());
        lvComenzi.setAdapter(adapter);

        addLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                   if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                       Comanda comanda = (Comanda) result.getData().getSerializableExtra(AddActivity.ADD_COMANDA);

                       if (comanda != null) {
                           AppDatabase db = AppDatabase.getInstanta(getApplicationContext());

                           if (comanda.getId() != 0) {
                                db.getComandaDao().update(comanda);
                           } else {
                               db.getComandaDao().insert(comanda);
                               // Reincarcam lista din baza de date pentru refresh
                               refreshLista();
                           }
                       }
                   }
                }
        );
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            addLauncher.launch(intent);
        });
    }

    private void refreshLista() {
        listaComenzi.clear();
        listaComenzi.addAll(AppDatabase.getInstanta(this).getComandaDao().getAll());
        ((ComandaAdapter) lvComenzi.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.meniu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Definim Task-ul
        @SuppressLint("StaticFieldLeak") ExtractJSON task = new ExtractJSON() {
            @Override
            protected void onPostExecute(String s) {
                // După ce s-a terminat descărcarea:
                if (this.listaComenziJSON != null && !this.listaComenziJSON.isEmpty()) {
                    // 1. Salvăm în Room (baza de date locală)
                    AppDatabase.getInstanta(getApplicationContext())
                            .getComandaDao().insert(this.listaComenziJSON);

                    // 2. Actualizăm lista vizuală
                    refreshLista();
                    Toast.makeText(MainActivity.this, "Date importate cu succes!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        try {
            // Executăm task-ul cu URL-ul dorit
            task.execute(new URL("https://pastebin.com/raw/XYZ123")); // URL de exemplu
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
}