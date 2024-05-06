package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.widget.AdapterView;

import com.example.application_interim.R;


public class RechercheOffreActivity extends AppCompatActivity {

    EditText editQuand,editLieu,editQuoi; // Déclaration de l'EditText editQuand

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_offre);

        // Initialisation de l'EditText editQuand
        editQuand = findViewById(R.id.edit_text_quand);
        editLieu = findViewById(R.id.edit_text_lieu);
        editQuoi = findViewById(R.id.edit_text_quoi);


        //Listederoulante
        Spinner spinnerLieu = findViewById(R.id.spinner_lieu);
        ArrayAdapter<CharSequence> adapterLieu = ArrayAdapter.createFromResource(this, R.array.lieu, android.R.layout.simple_spinner_item);
        adapterLieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLieu.setAdapter(adapterLieu);

        // Ecouteur de sélection pour le Spinner "Lieu" pour remplir l editText avec ce sui est selectionne
        spinnerLieu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOption = parentView.getItemAtPosition(position).toString();
                // Mettez à jour l'EditText avec la valeur sélectionnée
                editLieu.setText(selectedOption);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Faites quelque chose si rien n'est sélectionné
            }
        });

        Spinner spinnerQuoi = findViewById(R.id.spinner_quoi);
        ArrayAdapter<CharSequence> adapterQuoi = ArrayAdapter.createFromResource(this, R.array.quoi, android.R.layout.simple_spinner_item);
        adapterQuoi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuoi.setAdapter(adapterQuoi);

        // Ecouteur de sélection pour le Spinner "Quoi"
        spinnerQuoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOption = parentView.getItemAtPosition(position).toString();
                // Mettez à jour l'EditText avec la valeur sélectionnée
                editQuoi.setText(selectedOption);
            }
            // Ecouteur de sélection pour le Spinner "Quoi" pour remplir l editText avec ce sui est selectionne

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Faites quelque chose si rien n'est sélectionné
            }
        });

        // Ajout d'un OnClickListener à l'EditText editQuand
        editQuand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button anonymeButton = findViewById(R.id.btn_rechercher_offre);
        anonymeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheOffreActivity.this, AffichageOffreActivity.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour afficher la boîte de dialogue de sélection de la date
    private void showDatePickerDialog() {
        // Obtenez la date actuelle
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Créez une nouvelle instance de DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(RechercheOffreActivity.this,
                R.style.CustomDatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Mettez à jour le texte de l'EditText avec la date sélectionnée
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        editQuand.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}

