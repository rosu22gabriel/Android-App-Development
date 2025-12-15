package com.example.rosugabriel;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rosugabriel.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;

public class AccountCreatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_created);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // setare floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_main);

        floatingActionButton.setOnClickListener(v -> {
            Toast makeText = Toast.makeText(this, "Butonul a fost apasat", Toast.LENGTH_SHORT);
            makeText.show();
        });


        // Inauntrul metodei onCreate()
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        TextView userEmail = findViewById(R.id.user_email_id);
        TextView userPhoneNumber = findViewById(R.id.user_phone_number_id);

        userEmail.setText(email);
        userPhoneNumber.setText(phoneNumber);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meniu_din_profil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.optiune1) {
            Toast.makeText(this, "Optiunea 1 a fost selectata", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.optiune2) {
            Toast.makeText(this, "Optiunea 2 a fost selectata", Toast.LENGTH_SHORT).show();
            @SuppressLint("StaticFieldLeak") ExtractJSON extractJSON = new ExtractJSON()
            {
//                ProgressDialog progressDialog;
//                @Override
//                protected void onPreExecute() {
//                    progressDialog = new ProgressDialog(AccountCreatedActivity.this);
//                    progressDialog.setMessage("Please wait...");
//                    progressDialog.show();
//                }
//                @Override
//                protected void onPostExecute(String s) {
//                    progressDialog.cancel();
//                }
            };
            try {
                extractJSON.execute(new URL("https://pastebin.com/raw/SwzJD0gJ"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}