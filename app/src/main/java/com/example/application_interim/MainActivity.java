package com.example.application_interim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    DatabaseReference dataBaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dataBaseReference = FirebaseDatabase.getInstance().getReference("this is the path");
        dataBaseReference.setValue("Hello, World!").addOnSuccessListener(this, aVoid -> {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(this, e -> {
            Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
        }).addOnCompleteListener(this, task -> {
            Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show();
        });


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