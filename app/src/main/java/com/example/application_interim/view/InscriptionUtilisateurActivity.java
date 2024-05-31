package com.example.application_interim.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.UtilisateurViewModel;

import java.util.Calendar;

public class InscriptionUtilisateurActivity extends AppCompatActivity {
    private static final int PICK_PDF_REQUEST = 1;
    private Uri pdfUri;

    EditText dateNaissanceEditText;
    UtilisateurViewModel utilisateurViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_utilisateur);

        InscriptionUtilisateurActivity that = this;

        dateNaissanceEditText = findViewById(R.id.dateNaissanceEditText);
        dateNaissanceEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        EditText lastnameEditText = findViewById(R.id.nomEditText);
        EditText firstnameEditText = findViewById(R.id.prenomEditText);
        EditText nationalityEditText = findViewById(R.id.nationnaliteEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.mdpEditText);
        EditText phoneNumberEditText = findViewById(R.id.numeroEditText);
        EditText cityEditText = findViewById(R.id.villeEditText);

        Button buttonInscription = findViewById(R.id.inscriptionButton);
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastname = lastnameEditText.getText().toString();
                String firstname = firstnameEditText.getText().toString();
                String nationality = nationalityEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String dateNaissance = dateNaissanceEditText.getText().toString();

                utilisateurViewModel = new UtilisateurViewModel();

                try {
                    if (!lastname.isEmpty() && !firstname.isEmpty() && !nationality.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                        utilisateurViewModel.inscriptionUtilisateur(firstname, lastname, dateNaissance, nationality, city, phoneNumber, "", email, password);
                    } else {
                        Toast.makeText(that, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(that, "Veuillez entrer des informations valides", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(InscriptionUtilisateurActivity.this, ConnexionUtilisateurActivity.class));
            }
        });

        Button cvButton = findViewById(R.id.cvButton);
        cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir le sélecteur de fichiers pour choisir un PDF
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Sélectionnez un fichier PDF"), PICK_PDF_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            pdfUri = data.getData();
            if (pdfUri != null) {
                Toast.makeText(this, "CV sélectionné: " + pdfUri.getPath(), Toast.LENGTH_LONG).show();

                uploadPdfToServer(pdfUri);
            }
        }
    }
 //Logique enregistrement a complete
    private void uploadPdfToServer(Uri pdfUri) {

    }

    private void showDatePickerDialog() {
        // Obtenez la date actuelle
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Créez une nouvelle instance de DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(InscriptionUtilisateurActivity.this, R.style.CustomDatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Mettez à jour le texte de l'EditText avec la date sélectionnée
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                dateNaissanceEditText.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
