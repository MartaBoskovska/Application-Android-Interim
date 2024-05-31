package com.example.application_interim.repositories;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
        mAuth.createUserWithEmailAndPassword(enterprise.get("mail").toString(), enterprise.get("password").toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d(TAG, "Inscription reussite");
                authResult.getUser().getUid();
                firestore.collection("enterprises").document(authResult.getUser().getUid()).set(enterprise);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Inscription echouee", e);
            }
        });
    }

    public LiveData<Map<String,Object>> getEntreprise(String entrepriseID) {
        MutableLiveData<Map<String, Object>> entrepriseLiveData = new MutableLiveData<>();
        firestore.collection("enterprises").document(entrepriseID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    entrepriseLiveData.setValue(documentSnapshot.getData());
                } else {
                    Log.d(TAG, "No such document");
                    entrepriseLiveData.setValue(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "get failed with ", e);
                entrepriseLiveData.setValue(null);
            }
        });
        return entrepriseLiveData;
    }
}
