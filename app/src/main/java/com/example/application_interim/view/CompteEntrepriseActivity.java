package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.EntrepriseViewModel;

public class CompteEntrepriseActivity extends AppCompatActivity {

    private EntrepriseViewModel entrepriseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_entreprise);
        Bundle extras = getIntent().getExtras();

        String entrepriseID = extras.getString("entrepriseID");
        entrepriseViewModel = new EntrepriseViewModel();
        entrepriseViewModel.setEntrepriseID(entrepriseID);

        entrepriseViewModel.getEntreprise(entrepriseViewModel.getEntrepriseID()).observe(this, entreprise -> {
            TextView entrepriseName = findViewById(R.id.nomEntrepriseText);
            entrepriseName.setText(entreprise.get("companyname").toString());
        });

        TextView lienVoirCandidatures = findViewById(R.id.autreCandidatures);

        lienVoirCandidatures.setOnClickListener(v -> {
            Intent intent = new Intent(CompteEntrepriseActivity.this, AffichageCandidaturesActivity.class);
            intent.putExtra("entrepriseID", entrepriseID);
            startActivity(intent);
        });

        Button btn_creer_offre = findViewById(R.id.btn_creer_offre);

        btn_creer_offre.setOnClickListener(v -> {
            Intent intent = new Intent(CompteEntrepriseActivity.this, CreationOffreActivity.class);
            intent.putExtra("entrepriseID", entrepriseViewModel.getEntrepriseID());
            startActivity(intent);
        });


    }


}
