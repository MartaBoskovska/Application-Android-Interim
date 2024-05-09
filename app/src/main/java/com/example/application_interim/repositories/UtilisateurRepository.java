package com.example.application_interim.repositories;

import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class UtilisateurRepository {


    private FirebaseAuth mAuth;

    public UtilisateurRepository() {
        mAuth = FirebaseAuth.getInstance();
    }
    public Task connexionUtilisateur(String email, String motDePasse) {
        return mAuth.signInWithEmailAndPassword(email, motDePasse);
    }
}
