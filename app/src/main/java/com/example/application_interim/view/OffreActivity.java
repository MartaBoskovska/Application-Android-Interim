package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;

public class OffreActivity extends AppCompatActivity {

    private String offreID;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);

        TextView titre = findViewById(R.id.titre_offre);
        TextView localisation = findViewById(R.id.localisation_offre);
        TextView description = findViewById(R.id.description_offre);
        TextView missions = findViewById(R.id.mission);
        TextView profil = findViewById(R.id.profil);

        // Recuperer les donnees de l'offre
        offreID = getIntent().getStringExtra("offreID");
        userId = getIntent().getStringExtra("userId");
        String titreOffre = getIntent().getStringExtra("titre");
        String localisationOffre = getIntent().getStringExtra("localisation");
        String descriptionOffre = getIntent().getStringExtra("description");
        String missionsOffre = getIntent().getStringExtra("missions");
        String profilOffre = getIntent().getStringExtra("profil");

        // Afficher les donnees de l'offre
        titre.setText(titreOffre);
        localisation.setText(localisationOffre);
        description.setText(descriptionOffre);
        missions.setText(missionsOffre);
        profil.setText(profilOffre);


        Button buttonPostuler = findViewById(R.id.postuler);

        buttonPostuler.setOnClickListener(v -> {
            // Rediriger vers la page de candidature
            Intent intent = new Intent(OffreActivity.this, CandidaterActivity.class);
            intent.putExtra("offreID", offreID);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
    }
}
