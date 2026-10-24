
package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private String municipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView title = findViewById(R.id.municipalityTitle);
        ImageView image = findViewById(R.id.municipalityImage);
        TextView description =
                findViewById(R.id.municipalityDescription);
        Button visitButton = findViewById(R.id.visitSiteButton);

        String input = getIntent().getStringExtra("municipality_name");

        municipality = TextProcessor.processMunicipality(input);

        if (municipality == null) {
            Toast.makeText(this, "Invalid municipality",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int imageRes;
        int colourRes;
        int descriptionIndex;
        String website;

        switch (municipality.toLowerCase(java.util.Locale.ROOT)) {
            case "beijing":
                municipality = "Beijing";
                imageRes = R.drawable.beijing;
                colourRes = R.color.municipality_beijing;
                descriptionIndex = 0;
                website = "https://english.beijing.gov.cn/";
                break;

            case "shanghai":
                municipality = "Shanghai";
                imageRes = R.drawable.shanghai;
                colourRes = R.color.municipality_shanghai;
                descriptionIndex = 1;
                website = "https://english.shanghai.gov.cn/";
                break;

            case "chongqing":
                municipality = "Chongqing";
                imageRes = R.drawable.chongqing;
                colourRes = R.color.municipality_chongqing;
                descriptionIndex = 2;
                website = "https://english.cq.gov.cn/";
                break;

            case "tianjin":
                municipality = "Tianjin";
                imageRes = R.drawable.tianjin;
                colourRes = R.color.municipality_tianjin;
                descriptionIndex = 3;
                website = "https://en.tj.gov.cn/";
                break;

            default:
                Toast.makeText(this, "Invalid municipality",
                        Toast.LENGTH_SHORT).show();
                finish();
                return;
        }

        int colour = ContextCompat.getColor(this, colourRes);
        int lightText = ContextCompat.getColor(
                this, R.color.color_text_light);

        title.setText(municipality);
        title.setBackgroundColor(colour);
        title.setTextColor(lightText);

        image.setImageResource(imageRes);

        String[] descriptions = getResources().getStringArray(
                R.array.municipalities_array);
        description.setText(descriptions[descriptionIndex]);
        description.setTextColor(colour);

        visitButton.setBackgroundTintList(
                ColorStateList.valueOf(colour));
        visitButton.setTextColor(lightText);
        visitButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW, Uri.parse(website));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this,
                        "No application available to open website",
                        Toast.LENGTH_SHORT).show();
            }
        });

        View root = findViewById(android.R.id.content);
        root.setBackgroundColor(ContextCompat.getColor(
                this, R.color.color_default_background));
    }
}
