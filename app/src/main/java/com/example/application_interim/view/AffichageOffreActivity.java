package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.OffreViewModel;

import java.util.List;
import java.util.Map;

public class AffichageOffreActivity extends AppCompatActivity {

    private OffreViewModel offreViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_offre);

        Intent intent = getIntent();
        if (intent != null) {
            Log.d("AffichageOffreActivity", "Intent is not null");
            // Extraire les données de l'Intent
            Map<String,String> extra = (Map<String, String>) intent.getSerializableExtra("searchdata");
            this.offreViewModel = new OffreViewModel(this);
            String intitule = extra.get("intitule");
            String date = extra.get("date");
            String region = extra.get("region");
            offreViewModel.getListeOffres(intitule, date, region);
            List<Map<String, Object>> offreData = offreViewModel.getOffresData();

        }
        else
        {
            Log.d("AffichageOffreActivity", "Intent is null");
        }
    }

    public void createFragments(){
        Log.d("AffichageOffreActivity", "createFragments");
        List<Map<String, Object>> data = offreViewModel.getOffresData();
        for (Map<String, Object> offre : data) {
            String titre = (String) offre.get("intitule");
            String date = (String) offre.get("date");
            String lieu = (String) offre.get("region");

            // Creer un fragment pour chaque offre
            OffreFragment offreFragment = new OffreFragment();
            offreFragment.setArguments(titre, date, lieu);

            // Ajouter le fragment a la liste
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, offreFragment).commit();

        }
    }



}
