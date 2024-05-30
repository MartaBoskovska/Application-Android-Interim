package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.CandidatureViewModel;

public class CandidaterActivity extends AppCompatActivity {

    CandidatureViewModel candidatureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidater);

        Button buttonCandidater = findViewById(R.id.envoyer);
        buttonCandidater.setOnClickListener(v -> {

            TextView nom = findViewById(R.id.nomEditText);
            TextView prenom = findViewById(R.id.prenomEditText);
            TextView nationality = findViewById(R.id.nationnaliteEditText);
            TextView date = findViewById(R.id.dateNaissanceEditText);
            TextView lettreDeMotivation = findViewById(R.id.motivationText);

            String offreID = getIntent().getStringExtra("offreID");
            String userId = getIntent().getStringExtra("userId");

            candidatureViewModel = new CandidatureViewModel();
            CandidaterActivity that = CandidaterActivity.this;

            LiveData<Boolean> success = candidatureViewModel.addCandidature(offreID, userId, prenom.getText().toString(), nom.getText().toString(), nationality.getText().toString(), date.getText().toString(), lettreDeMotivation.getText().toString());
            success.observe(that, aBoolean -> {
                if (aBoolean) {
                    Toast.makeText(that, "Candidature ajoutée", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(that, "Erreur lors de l'ajout de le candidature", Toast.LENGTH_SHORT).show();
                }
            });

            startActivity(new Intent(CandidaterActivity.this, AffichageOffreActivity.class));
        });


    }


}
