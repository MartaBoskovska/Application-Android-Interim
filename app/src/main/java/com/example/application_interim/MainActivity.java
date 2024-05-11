package com.example.application_interim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.view.CandidaterActivity;
import com.example.application_interim.view.CompteUtilisateurActivity;
import com.example.application_interim.view.ConnexionEntrepriseActivity;
import com.example.application_interim.view.ConnexionUtilisateurActivity;
import com.example.application_interim.view.OffreActivity;
import com.example.application_interim.view.RechercheOffreActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    DatabaseReference dataBaseReference;

    private FirebaseAuth mAuth;
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


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword("marta.boskovska@yahoo.com", "password");
        mAuth.signInWithEmailAndPassword("marta.boskovska@yahoo.com", "password");


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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}