package com.example.application_interim.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.CreationOffreViewModel;
import com.example.application_interim.viewmodel.OffreViewModel;

public class CreationOffreActivity extends AppCompatActivity {

        CreationOffreViewModel offreViewModel;
        private String entrepriseID = "";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                this.entrepriseID = extras.getString("entrepriseID");
                //The key argument here must match that used in the other activity
            }
            setContentView(R.layout.activity_creation_offre);

            offreViewModel = new CreationOffreViewModel();

            EditText paysEditText = findViewById(R.id.paysEditText);
            EditText regionEditText = findViewById(R.id.regionEditText);
            EditText villeEditText = findViewById(R.id.villeEditText);

            EditText intituleEditText = findViewById(R.id.intituleEditText);
            EditText secteurEditText = findViewById(R.id.secteurEditText);
            EditText typeContratEditText = findViewById(R.id.typeContratEditText);
            EditText remunerationEditText = findViewById(R.id.remunerationEditText);
            EditText descriptionEditText = findViewById(R.id.descriptionEditText);
            EditText missionsEditText = findViewById(R.id.missionsEditText);
            EditText profilEditText = findViewById(R.id.profilEditText);
            EditText titreEditText = findViewById(R.id.titreEditText);

            Button buttonCreation = findViewById(R.id.button_add_offer);
            buttonCreation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String pays = paysEditText.getText().toString();
                    String region = regionEditText.getText().toString();
                    String ville = villeEditText.getText().toString();

                    String intitule = intituleEditText.getText().toString();
                    String secteur = secteurEditText.getText().toString();
                    String typeContrat = typeContratEditText.getText().toString();
                    String remuneration = remunerationEditText.getText().toString();

                    String description = descriptionEditText.getText().toString();
                    String missions = missionsEditText.getText().toString();
                    String profil = profilEditText.getText().toString();
                    String titreOffre = titreEditText.getText().toString();

                    String entreprise = CreationOffreActivity.this.entrepriseID;
                    CreationOffreActivity that = CreationOffreActivity.this;
                    LiveData<Boolean> success = offreViewModel.addOffre(pays, region, ville, intitule, titreOffre, typeContrat, remuneration, description, secteur, entreprise, missions, profil);
                    success.observe(that, aBoolean -> {
                        if (aBoolean) {
                            Toast.makeText(CreationOffreActivity.this, "Offre ajoutée", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreationOffreActivity.this, "Erreur lors de l'ajout de l'offre", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            });
        }
}
