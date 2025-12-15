package ro.ase.semdam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listViewBilete = findViewById(R.id.listViewBilete);

        listViewBilete.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                BiletAvion biletAvion = listaBilete.get(position);

                ArrayAdapter adapter = (ArrayAdapter) listViewBilete.getAdapter();

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
                                listaBilete.remove(biletAvion);

                                BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                                database.getBileteDao().delete(biletAvion);

                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Am sters: "+biletAvion.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });

        listViewBilete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(EDIT_BILET, listaBilete.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
        listaBilete = database.getBileteDao().getAll();

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemlistview,
                listaBilete, getLayoutInflater());
        listViewBilete.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.meniu_principal, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.optiune1)
        {
            Intent intent1 = new Intent(getApplicationContext(), BNRActivity.class);
            startActivity(intent1);
            return true;
        }
        else
            if(item.getItemId() == R.id.optiune2)
            {
                ExtractXML extractXML = new ExtractXML()
                {
                    @Override
                    protected void onPostExecute(InputStream inputStream) {
                        listaBilete.addAll(this.biletAvionList);

                        BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                        database.getBileteDao().insert(this.biletAvionList);

                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemlistview,
                                listaBilete, getLayoutInflater());
                        listViewBilete.setAdapter(adapter);
                    }
                };
                try {
                    extractXML.execute(new URL("https://pastebin.com/raw/Wk6QzrBs"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                return true;
            }
            else
            if(item.getItemId() == R.id.optiune3)
            {
                ExtractJSON extractJSON = new ExtractJSON()
                {
                    ProgressDialog progressDialog;

                    @Override
                    protected void onPreExecute() {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        progressDialog.cancel();

                        listaBilete.addAll(this.biletAvionListJSON);

                        BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                        database.getBileteDao().insert(this.biletAvionListJSON);

                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.elemlistview,
                                listaBilete, getLayoutInflater());
                        listViewBilete.setAdapter(adapter);
                    }
                };
                try {
                    extractJSON.execute(new URL("https://pastebin.com/raw/261cSq0Z"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                return true;
            }
            else
            if(item.getItemId() == R.id.optiune4)
            {
                return true;
            }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_ADD && resultCode==RESULT_OK && data!=null)
        {
            BiletAvion biletAvion = (BiletAvion)data.getSerializableExtra(AddActivity.ADD_BILET);
            if(biletAvion!=null)
            {
                BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                database.getBileteDao().insert(biletAvion);

                /*Toast.makeText(getApplicationContext(), biletAvion.toString(),
                        Toast.LENGTH_LONG).show();*/
                listaBilete.add(biletAvion);
               /* ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        listaBilete);*/
                CustomAdapter adapter = new CustomAdapter(this, R.layout.elemlistview,
                        listaBilete, getLayoutInflater());
                listViewBilete.setAdapter(adapter);
            }
        }
        else
        if(requestCode==REQUEST_CODE_EDIT && resultCode==RESULT_OK && data!=null)
        {
            BiletAvion biletAvion = (BiletAvion)data.getSerializableExtra(AddActivity.ADD_BILET);
            if(biletAvion!=null)
            {


                listaBilete.get(poz).setDestinatie(biletAvion.getDestinatie());
                listaBilete.get(poz).setDataZbor(biletAvion.getDataZbor());
                listaBilete.get(poz).setPret(biletAvion.getPret());
                listaBilete.get(poz).setCompanie(biletAvion.getCompanie());
                listaBilete.get(poz).setClasaBilet(biletAvion.getClasaBilet());

                BileteAvionDB database = BileteAvionDB.getInstanta(getApplicationContext());
                database.getBileteDao().update(listaBilete.get(poz));

                /*ArrayAdapter<BiletAvion> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        listaBilete);*/
                //ArrayAdapter adapter = (ArrayAdapter)listViewBilete.getAdapter();
                CustomAdapter adapter = (CustomAdapter) listViewBilete.getAdapter();
                adapter.notifyDataSetChanged();

                listViewBilete.setAdapter(adapter);
            }
        }
    }
}