package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.EntrepriseViewModel;
import com.example.application_interim.viewmodel.UtilisateurViewModel;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionEntrepriseActivity extends AppCompatActivity {

    private EntrepriseViewModel connexionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_entreprise);


        TextView textView_inscription = findViewById(R.id.text_inscription);

        SpannableString content = new SpannableString(textView_inscription.getText());

        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        textView_inscription.setText(content);

        textView_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionEntrepriseActivity.this, InscriptionEntrepriseActivity.class);
                startActivity(intent);
            }
        });


        ConnexionEntrepriseActivity that = this;
        Button connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.usernameEditText);
                EditText password = findViewById(R.id.passwordEditText);

                connexionViewModel = new EntrepriseViewModel();

                LiveData<FirebaseUser> userID = connexionViewModel.connexionEntreprise(email.getText().toString(), password.getText().toString());
                userID.observe(that, id -> {
                            if (id != null) {
                                Toast.makeText(that, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ConnexionEntrepriseActivity.this, CreationOffreActivity.class);
                                intent.putExtra("entrepriseID", id.getUid());
                                startActivity(intent);
                            } else {
                                Toast.makeText(that, "Veulliez entrer un email et un mot de passe valides", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });

    }


}
