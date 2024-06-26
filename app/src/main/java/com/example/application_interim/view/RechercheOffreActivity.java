package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.app.DatePickerDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import android.widget.AdapterView;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.OffreViewModel;


public class RechercheOffreActivity extends AppCompatActivity {


    EditText editTextQuoi, editQuand, editTextOu;
    ListView listViewQuoi, listViewOu;

    String userId;
    String[] valuesQuoi = {
            "Equipier",
            "Menage",
            "Vente",
            "Babyssiting",
            "BTP"

    };
    String[] valuesOu = {
            "Dijon",
            "Grenoble",
            "Lyon",
            "Marseille",
            "Montpellier",
            "Paris",
            "Tours",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("RechercheOffreActivity", "onCreate");
        if (getIntent().getExtras() != null) {
            userId = getIntent().getStringExtra("USER_ID");
            Log.i("USER_ID RechercheOffreActivity", userId);
        }
        setContentView(R.layout.activity_recherche_offre);
        listViewQuoi = findViewById(R.id.listViewQuoi);
        editTextQuoi = findViewById(R.id.editTextQuoi);

        listViewOu = findViewById(R.id.listViewOu);
        editTextOu = findViewById(R.id.editTextOu);




        ArrayAdapter<String> adapterQuoi = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesQuoi);
        listViewQuoi.setAdapter(adapterQuoi);

        ArrayAdapter<String> adapterOu = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesOu);
        listViewOu.setAdapter(adapterOu);

        listViewQuoi.setVisibility(View.GONE);
        listViewOu.setVisibility(View.GONE);

        // Ajouter un écouteur de clic à l'EditText pour afficher ou masquer le ListView
        editTextQuoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleListViewVisibilityQuoi();

            }
        });

        editTextOu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleListViewVisibilityOu();
            }
        });

        // Écouteur de clic pour gérer la sélection d'un élément dans le ListView
        listViewQuoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                editTextQuoi.setText(selectedItem);
                listViewQuoi.setVisibility(View.GONE); // Masquer le ListView après la sélection
            }
        });

        listViewOu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                editTextOu.setText(selectedItem);
                listViewOu.setVisibility(View.GONE); // Masquer le ListView après la sélection
            }
        });

        editQuand = findViewById(R.id.quandEditText);

        editQuand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button buttonRechercher = findViewById(R.id.btn_rechercher_offre);
        buttonRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des EditText
                String intitule = editTextQuoi.getText().toString();
                String ville = editTextOu.getText().toString();
                String date = editQuand.getText().toString();

                // Rechercher les offres correspondantes

                Map<String, String> searchData = new HashMap<>();
                searchData.put("intitule", intitule);
                searchData.put("date", date);
                searchData.put("ville", ville);



                Intent intent = new Intent(RechercheOffreActivity.this, AffichageOffreActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("searchdata", (Serializable) searchData);
                startActivity(intent);
            }
        });


    }

    // Méthode pour afficher ou masquer le ListView
    private void toggleListViewVisibilityQuoi() {

        if (listViewQuoi.getVisibility() == View.VISIBLE) {
            listViewQuoi.setVisibility(View.GONE);
        } else {
            listViewQuoi.setVisibility(View.VISIBLE);
            hideKeyboardQuoi(); // Masquer le clavier lorsque le ListView est rendu visible
        }
    }

    private void toggleListViewVisibilityOu() {

        if (listViewOu.getVisibility() == View.VISIBLE) {
            listViewOu.setVisibility(View.GONE);
        } else {
            listViewOu.setVisibility(View.VISIBLE);
            hideKeyboardOu(); // Masquer le clavier lorsque le ListView est rendu visible
        }
    }

    // Méthode pour masquer le clavier
    private void hideKeyboardQuoi() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextQuoi.getWindowToken(), 0);
    }

    private void hideKeyboardOu() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextOu.getWindowToken(), 0);
    }

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
                        editQuand.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }


}

