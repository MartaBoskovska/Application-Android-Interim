package com.example.application_interim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button utilisateurButton = findViewById(R.id.button1);
        utilisateurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConnexionUtilisateurActivity.class);
                startActivity(intent);
            }
        });

        Button anonymeButton = findViewById(R.id.button2);
        anonymeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RechercheOffreActivity.class);
                startActivity(intent);
            }
        });



        Button entrepriseButton = findViewById(R.id.button3);
        entrepriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConnexionEntrepriseActivity.class);
                startActivity(intent);
            }
        });




    }
}