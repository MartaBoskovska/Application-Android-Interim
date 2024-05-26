package com.example.application_interim.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.EntrepriseViewModel;
import com.example.application_interim.viewmodel.UtilisateurViewModel;

import java.util.Calendar;

public class InscriptionEntrepriseActivity extends AppCompatActivity {


    EntrepriseViewModel entrepriseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inscription_entreprise);
        EditText companynameEditText = findViewById(R.id.nomEditText);
        EditText mailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.mdpEditText);
        EditText phoneNumberEditText = findViewById(R.id.numeroEditText);
        EditText addressText = findViewById(R.id.adresseEditText);
        EditText siteWebEditText = findViewById(R.id.sitewebEditText);
        EditText linkedinEditText = findViewById(R.id.linkedinEditText);
        EditText facebookEditText = findViewById(R.id.facebookEditText);
        EditText otherEditText = findViewById(R.id.autrereseauEditText);


        InscriptionEntrepriseActivity that = this;


        Button buttonInscription = findViewById(R.id.inscriptionButton);
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String companyname = companynameEditText.getText().toString();
                String mail = mailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String address = addressText.getText().toString();
                String siteWeb = siteWebEditText.getText().toString();
                String linkedin = linkedinEditText.getText().toString();
                String facebook = facebookEditText.getText().toString();
                String others = otherEditText.getText().toString();


                // Inscription de l'utilisateur
                entrepriseViewModel = new EntrepriseViewModel();

                try {
                    if (!companyname.isEmpty() && !mail.isEmpty() && !password.isEmpty() && !phoneNumber.isEmpty() && !address.isEmpty() && !siteWeb.isEmpty()) {

                        if (linkedin.isEmpty()) linkedin = "";
                        if (facebook.isEmpty()) facebook = "";
                        if (others.isEmpty()) others = "";
                        entrepriseViewModel.inscriptionEntreprise(companyname, mail, password, address, siteWeb, linkedin, facebook, others, phoneNumber);
                    } else {
                        Toast.makeText(that, "Veuiilez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(that, "Veuillez entrer des informations valides", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(InscriptionEntrepriseActivity.this, ConnexionEntrepriseActivity.class));

            }
        });
    }


}
