package ro.ase.semdam_1086;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    public static final int REQUEST_CODE_ADD = 100;

    public List<Avion> listaAvioane = new ArrayList<>();

    private ListView listView;

    public int poz;

    public static final int REQUEST_CODE_EDIT = 200;

    public static final String EDIT_AVION = "editAvion";

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

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listView = findViewById(R.id.listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                Avion avion = listaAvioane.get(position);

                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nu am sters nimic!",
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listaAvioane.remove(avion);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Am sters "+avion.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EDIT_AVION, listaAvioane.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_ADD && resultCode==RESULT_OK && data!=null)
        {
            Avion avion = (Avion) data.getSerializableExtra(AddActivity.ADD_AVION);
            if(avion!=null)
            {
               /* Toast.makeText(getApplicationContext(), avion.toString(),
                        Toast.LENGTH_LONG).show();*/
                listaAvioane.add(avion);
                /*ArrayAdapter<Avion> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        listaAvioane);*/
                CustomAdapter adapter = new CustomAdapter(this, R.layout.elem_listview,
                        listaAvioane, getLayoutInflater());
                listView.setAdapter(adapter);
            }
        }
        else
        if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null)
        {
            Avion avion = (Avion) data.getSerializableExtra(AddActivity.ADD_AVION);
            if(avion!=null)
            {
                listaAvioane.get(poz).setProducator(avion.getProducator());
                listaAvioane.get(poz).setDataFabricatiei(avion.getDataFabricatiei());
                listaAvioane.get(poz).setNrLocuri(avion.getNrLocuri());
                listaAvioane.get(poz).setModel(avion.getModel());
                listaAvioane.get(poz).setEsteDefect(avion.isEsteDefect());

                //ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                CustomAdapter adapter = (CustomAdapter) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.meniu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.optiune1)
        {
            Intent intent1 = new Intent(getApplicationContext(), BNRActivity.class);
            startActivity(intent1);
            return true;
        }
        else
            if(item.getItemId()==R.id.optiune2)
            {
                return true;
            }
            else
            if(item.getItemId()==R.id.optiune3)
            {
                return true;
            }
            else
            if(item.getItemId()==R.id.optiune4)
            {
                return true;
            }

        return false;
    }
}