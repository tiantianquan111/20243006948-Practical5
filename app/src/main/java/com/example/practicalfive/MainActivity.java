
package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText municipalityInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        municipalityInput = findViewById(R.id.municipalityInput);

        municipalityInput.setSingleLine(true);
        municipalityInput.setImeOptions(EditorInfo.IME_ACTION_GO);

        municipalityInput.setOnEditorActionListener((v, actionId, event) -> {

            boolean enterPressed =
                    actionId == EditorInfo.IME_ACTION_GO
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || (event != null
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                            && event.getAction() == KeyEvent.ACTION_DOWN);

            if (enterPressed) {
                handleMunicipalityInput();
                return true;
            }

            return false;
        });
    }

    private void handleMunicipalityInput() {

        String input = municipalityInput.getText().toString();

        String municipality =
                TextProcessor.processMunicipality(input);

        if (municipality == null) {
            municipalityInput.setError(
                    "Enter Beijing, Shanghai, Tianjin or Chongqing"
            );

            Toast.makeText(
                    MainActivity.this,
                    "Invalid municipality. Please try again.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        Intent intent = new Intent(
                MainActivity.this,
                Municipality.class
        );

        intent.putExtra("municipality_name", municipality);

        startActivity(intent);
    }
}
