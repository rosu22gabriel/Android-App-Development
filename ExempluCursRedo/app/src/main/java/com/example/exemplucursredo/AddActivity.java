package com.example.exemplucursredo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    public static final String ADD_AVION = "addAvion";

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

        Intent intent = getIntent();

        Spinner spinnerModel = findViewById(R.id.spinnerModel);
        String[] modele = {"A320", "A330", "787", "737"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                modele
        );
        spinnerModel.setAdapter(adapter);

        EditText etProducator = findViewById(R.id.editTextProducator);
        EditText etDataFabricatie = findViewById(R.id.editTextDate);
        EditText etNrLocuri = findViewById(R.id.editTextNrLocuri);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Button btnAdauga = findViewById(R.id.btnCreate);

        if(intent.hasExtra(MainActivity.EDIT_AVION)) {
            Avion avion = (Avion) intent.getSerializableExtra(MainActivity.EDIT_AVION);
            etProducator.setText(avion.getProducator());
            etDataFabricatie.setText(avion.getDataFabricatiei().toString());
            etNrLocuri.setText(String.valueOf(avion.getNrLocuri() + ""));

            ArrayAdapter<String> adapter1 = (ArrayAdapter<String>) spinnerModel.getAdapter();
            int position = adapter1.getPosition(avion.getModel());
            spinnerModel.setSelection(position);

            if(avion.isEsteDefect()==true)
                radioGroup.check(R.id.radioButtonDA);
            else
                radioGroup.check(R.id.radioButtonNU);

            btnAdauga.setText("Editare avion");
        }

        btnAdauga.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(etProducator.getText().toString().isEmpty())
                   etProducator.setError("Introduceti producatorul");
               else
                   if(etDataFabricatie.getText().toString().isEmpty())
                       etDataFabricatie.setError("Introduceti data fabricatiei");
                   else
                       if(etNrLocuri.getText().toString().isEmpty())
                           etNrLocuri.setError("Introduceti numarul de locuri");
                       else
                       {
                           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                           try {
                               sdf.parse(etDataFabricatie.getText().toString());
                               Date dataFabricatie = new Date(etDataFabricatie.getText().toString());
                               String producator = etProducator.getText().toString();
                               int nrLocuri = Integer.parseInt(etNrLocuri.getText().toString());
                               String model = spinnerModel.getSelectedItem().toString();
                               RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                               boolean esteDefect = false;
                               String text = radioButton.getText().toString();
                               if(text.equals("DA"))
                                   esteDefect = true;
                               else esteDefect=false;

                               Avion avion = new Avion(producator, dataFabricatie, nrLocuri, model, esteDefect);
                               Intent intent1 = new Intent(getApplicationContext(), AfisareAvionActivity.class);
                               intent1.putExtra(ADD_AVION, avion);
                               startActivity(intent1);
                           } catch(ParseException e) {
                               throw new RuntimeException(e);
                           } catch(Exception e) {
                               Toast.makeText(getApplicationContext(), "Eroare la adaugarea avionului",
                                       Toast.LENGTH_SHORT).show();
                               Log.e("AddActivity", "Eroare la adaugarea avionului");
                           }
                       }
           }
        });
    }
}