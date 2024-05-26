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
import com.example.application_interim.viewmodel.UtilisateurViewModel;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionUtilisateurActivity extends AppCompatActivity {

    private UtilisateurViewModel connexionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_utilisateur);

        TextView textView_inscription = findViewById(R.id.text_inscription);
        SpannableString content = new SpannableString(textView_inscription.getText());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView_inscription.setText(content);

        textView_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionUtilisateurActivity.this, InscriptionUtilisateurActivity.class);
                startActivity(intent);
            }
        });

        ConnexionUtilisateurActivity that = this;
        Button connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.usernameEditText);
                EditText password = findViewById(R.id.passwordEditText);

                connexionViewModel = new UtilisateurViewModel();

                LiveData<FirebaseUser> userID = connexionViewModel.connexionUtilisateur(email.getText().toString(), password.getText().toString());
                userID.observe(that, id -> {
                            if (id != null) {
                                Toast.makeText(that, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ConnexionUtilisateurActivity.this, RechercheOffreActivity.class));
                            } else {
                                Toast.makeText(that, "Veulliez entrer un email et un mot de passe valides", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });


    }
}
