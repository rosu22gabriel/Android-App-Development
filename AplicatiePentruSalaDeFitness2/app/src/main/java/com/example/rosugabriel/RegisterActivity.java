package com.example.rosugabriel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rosugabriel.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView emailInput = findViewById(R.id.email_input_id);
        TextView firstNameInput = findViewById(R.id.first_name_input_id);
        TextView lastNameInput = findViewById(R.id.last_name_input_id);
        TextView phoneNumberInput = findViewById(R.id.phone_number_input_id);

        Button registerButton = findViewById(R.id.inregistreaza_te_button_id);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (emailInput.getText().toString().isEmpty()) {
                    emailInput.setError("Introduceti emailul");
                    return;
                };
                if (firstNameInput.getText().toString().isEmpty()) {
                    firstNameInput.setError("Introduceti prenumele");
                    return;
                };
                if (lastNameInput.getText().toString().isEmpty()) {
                    lastNameInput.setError("Introduceti numele");
                    return;
                }
                if (phoneNumberInput.getText().toString().isEmpty()) {
                    phoneNumberInput.setError("Introduceti numarul de telefon");
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Inregistrare cu succes!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, AccountCreatedActivity.class);

                intent.putExtra("email", emailInput.getText().toString());
                intent.putExtra("firstName", firstNameInput.getText().toString());
                intent.putExtra("lastName", lastNameInput.getText().toString());
                intent.putExtra("phoneNumber", phoneNumberInput.getText().toString());

                startActivity(intent);
            }
        });
    }
}