package com.example.aplicatie_pentru_sala_de_fitness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button becomeAMemberButton = findViewById(R.id.become_a_member_button_id);

        // La apasarea butonului Become A Member porneste activitatea BecomeMemberActivity
        becomeAMemberButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, BecomeMemberActivity.class);
            startActivity(intent);
        });

        // Validarea datelor de conectare ale utilizatorului
        EditText emailInput = findViewById(R.id.email_input_id);
        EditText pincodeInput = findViewById(R.id.pincode_input_id);

        // Valorile text dinauntrul EditText-urilor ca string-uri
        String email = emailInput.getText().toString();
        String pincode = pincodeInput.getText().toString();

        // Definirea unui model de expresie regulata pentru validarea inputului utilizatorului

        // ^ -> inceputul expresiei
        // [...] defineste o clasa de caractere, oricare dintre cele dinauntrul parantezelor
        // A-za-z -> litere de la A-Z si de la a-z
        // 0-9 -> cifre de la 0-9
        // +_.- -> caractere speciale +_.-

        // + in afara parantezelor + una sau mai multe caractere din urmatoara clasa de caractere
        // @ -> caracterul @ din adresa de email
        // $ -> sfarsitul expresiei
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-za-z0-9.-]+$";
        String pincodeRegex = "^[0-9]+$";

        // Verificarea daca emailul este valid dupa modelul de expresie regulata
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        // Verificarea daca pincode-ul este valid dupa modelul de expresie regulata
        Pattern pincodePattern = Pattern.compile(pincodeRegex);
        Matcher pincodeMatcher = pincodePattern.matcher(pincode);


        // Logarea utilizatorului
        // Daca datele sunt valide se va duce in activitatea BecomeMemberActivity
        Button logInButton = findViewById(R.id.log_in_button_id);
        logInButton.setOnClickListener(v -> {
            if (email.isEmpty() || pincode.isEmpty()) {
                Toast.makeText(this, "Lipsesc datele de conectare", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!emailMatcher.matches()) {
                Toast.makeText(this, "Email invalid", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pincodeMatcher.matches()) {
                Toast.makeText(this, "Pincode invalid", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Conectare cu succes", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BecomeMemberActivity.class);
            startActivity(intent);
        });

    }
}

