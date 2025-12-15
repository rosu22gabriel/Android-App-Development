package com.example.redoexemplucurs1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD = 100;
    public static final int REQUEST_CODE_EDIT = 200;
    public static final String EDIT_BILET = "editBilet";
    public int poz;
    ListView listViewBilete;

    List<BiletAvion> listaBilete = new ArrayList<>();

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton4);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        listViewBilete = findViewById(R.id.listViewBilete);
        listViewBilete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                BiletAvion biletAvion = listaBilete.get(position);
                ArrayAdapter adapter = (ArrayAdapter) listViewBilete.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Nu am sters nimic",
                                        Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listaBilete.remove(biletAvion);
                                BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                                database.getBileteDAO().delete(biletAvion);

                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Am sters " + biletAvion.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        }).create();

                dialog.show();
                return true;
            }
        });

        listViewBilete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EDIT_BILET, listaBilete.get(position));
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
        listaBilete = database.getBileteDAO().getAll();

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemlistview,
                listaBilete, getLayoutInflater());
        listViewBilete.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meniu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.optiune1) {
            Intent intent1 = new Intent(getApplicationContext(), BNRActivity.class);
            startActivity(intent1);
            return true;
        } else
            if(item.getItemId() == R.id.optiune2){
                ExtractXML extractXML = new ExtractXML() {

                }



        }
    });

}