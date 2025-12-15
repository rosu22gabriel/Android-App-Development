package com.example.exemplucursfaradepreciate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Aici utilizatorul introduce datele, pe care noi le transformam intr-un obiect Comanda
// si le trimitem inapoi in MainActivity sau le salvam direct in baza


// Activitate are doua roluri:
// Adaugare: Campuri goale pe care le salvam
// Editare: Primeste un obiect existent, populeaza campurile, iar la final face update.


public class AddActivity extends AppCompatActivity {
    public static final String ADD_COMANDA = "addComanda";
    private EditText etClient, etAdresa, etPret;
    private Button btnSave;
    private Comanda comandaDeEditat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializareComponente();

        // Verificam daca am primit un obiect pentru editare
        if (getIntent().hasExtra(MainActivity.EDIT_BILET)) {
            comandaDeEditat = (Comanda) getIntent().getSerializableExtra(MainActivity.EDIT_BILET);
            populeazaCampuri(comandaDeEditat);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validare()) {
                    String client = etClient.getText().toString();
                    String adresa = etAdresa.getText().toString();
                    float pret = Float.parseFloat(etPret.getText().toString());
                    if (comandaDeEditat == null) {
                        // Caz adaugare
                        Comanda nouaComanda = new Comanda(client, adresa, pret, "Cash");
                        returnResult(nouaComanda);
                    } else {
                        // Caz de editare
                        comandaDeEditat.setClient(client);
                        comandaDeEditat.setAdresa(adresa);
                        comandaDeEditat.setPret(pret);
                        returnResult(comandaDeEditat);
                    }
                }
            }
        });

    }

    private void initializareComponente() {
        etClient = findViewById(R.id.et_client);
        etAdresa = findViewById(R.id.et_adresa);
        etPret = findViewById(R.id.et_pret);
        btnSave = findViewById(R.id.btn_save);
    }

    private void populeazaCampuri(Comanda c) {
        etClient.setText(c.getClient());
        etAdresa.setText(c.getAdresa());
        etPret.setText(String.valueOf(c.getPret()));
    }

    private boolean validare() {
        if (etClient.getText().toString().isEmpty()) return false;
        if (etPret.getText().toString().isEmpty()) return false;
        return true;
    }

    private void returnResult(Comanda comanda) {
        Intent intent = new Intent();
        intent.putExtra(ADD_COMANDA, comanda);
        setResult(RESULT_OK, intent);
        finish(); // Inchide activitatea si revine la MainActivity
    }
}