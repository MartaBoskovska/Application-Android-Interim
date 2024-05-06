package com.example.application_interim.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;

import java.util.Calendar;

public class InscriptionEntrepriseActivity extends AppCompatActivity {
    EditText dateNaissanceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_utilisateur);
        dateNaissanceEditText= findViewById(R.id.dateNaissanceEditText);

        dateNaissanceEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });




    }


    private void showDatePickerDialog() {
        // Obtenez la date actuelle
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Créez une nouvelle instance de DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(InscriptionEntrepriseActivity.this,
                R.style.CustomDatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Mettez à jour le texte de l'EditText avec la date sélectionnée
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        dateNaissanceEditText.setText(selectedDate);
                        dateNaissanceEditText.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

}
