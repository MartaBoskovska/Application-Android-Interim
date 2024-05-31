package com.example.application_interim.view;

import android.os.Bundle;
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
        if (extras != null) {
            String entrepriseID = extras.getString("entrepriseID");
            entrepriseViewModel = new EntrepriseViewModel();
            entrepriseViewModel.setEntrepriseID(entrepriseID);
        }
        entrepriseViewModel.getEntreprise(entrepriseViewModel.getEntrepriseID()).observe(this, entreprise -> {
            TextView entrepriseName = findViewById(R.id.nomEntrepriseText);
            entrepriseName.setText(entreprise.get("companyname").toString());
        });

    }



}
