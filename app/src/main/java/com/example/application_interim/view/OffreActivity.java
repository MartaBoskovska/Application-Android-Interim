package com.example.application_interim.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;

public class OffreActivity extends AppCompatActivity {

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
    }
}
