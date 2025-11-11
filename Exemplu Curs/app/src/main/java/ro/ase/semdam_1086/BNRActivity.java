package ro.ase.semdam_1086;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BNRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bnr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.e("lifecycle", "Apel onCreate");

        TextView tvData = findViewById(R.id.tvData);
        EditText etEUR = findViewById(R.id.editTextEUR);
        EditText etUSD = findViewById(R.id.editTextUSD);
        EditText etGBP = findViewById(R.id.editTextGBP);
        EditText etXAU = findViewById(R.id.editTextXAU);

        Button btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Click pe buton!", Toast.LENGTH_LONG).show();

                tvData.setText("2025/09/30");
                etEUR.setText("5.2456");
                etUSD.setText("4.3456");
                etGBP.setText("5.7890");
                etXAU.setText("524.6789");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("lifecycle", "Apel onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle", "Apel onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifecycle", "Apel onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("lifecycle", "Apel onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lifecycle", "Apel onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle", "Apel onDestroy");
    }
}