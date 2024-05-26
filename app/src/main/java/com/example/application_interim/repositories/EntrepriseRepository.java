package com.example.application_interim.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class EntrepriseRepository {


    private final FirebaseAuth mAuth;
    private final FirebaseFirestore firestore;

    public EntrepriseRepository() {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public LiveData<FirebaseUser> connexionEntreprise(String email, String motDePasse) {
        MutableLiveData<FirebaseUser> entrepriseLiveData = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, motDePasse).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.w(TAG, "Connexion reussite");
                entrepriseLiveData.setValue(mAuth.getCurrentUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Connexion echouee", e);
                entrepriseLiveData.setValue(null);
            }
        });
        return entrepriseLiveData;
    }


    public void addEntreprise(Map<String, Object> enterprise) throws Exception {
        firestore.collection("enterprises").add(enterprise).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
        mAuth.createUserWithEmailAndPassword(enterprise.get("mail").toString(), enterprise.get("password").toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d(TAG, "Inscription reussite");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Inscription echouee", e);
            }
        });
    }
}
