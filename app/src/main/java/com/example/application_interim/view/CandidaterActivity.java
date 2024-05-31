package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.CreationCandidatureViewModel;

public class CandidaterActivity extends AppCompatActivity {

    CreationCandidatureViewModel creationCandidatureViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidater);

        String titre = getIntent().getStringExtra("titreOffre");
        TextView titreOffre = findViewById(R.id.titre_offre);
        titreOffre.setText(titre);
        Button buttonCandidater = findViewById(R.id.envoyer);
        buttonCandidater.setOnClickListener(v -> {

            TextView nom = findViewById(R.id.nomEditText);
            TextView prenom = findViewById(R.id.prenomEditText);
            TextView nationality = findViewById(R.id.nationnaliteEditText);
            TextView date = findViewById(R.id.dateNaissanceEditText);
            TextView lettreDeMotivation = findViewById(R.id.motivationText);

            String offreID = getIntent().getStringExtra("offreID");
            String userId = getIntent().getStringExtra("userId");


            creationCandidatureViewModel = new CreationCandidatureViewModel();
            CandidaterActivity that = CandidaterActivity.this;

            LiveData<Boolean> success = creationCandidatureViewModel.addCandidature(offreID, userId, titre, prenom.getText().toString(), nom.getText().toString(), nationality.getText().toString(), date.getText().toString(), lettreDeMotivation.getText().toString());
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
