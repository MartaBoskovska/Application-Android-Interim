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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class UtilisateurRepository {


    private final FirebaseAuth mAuth;
    private final FirebaseFirestore firestore;

    public UtilisateurRepository() {
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public LiveData<FirebaseUser> connexionUtilisateur(String email, String motDePasse) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, motDePasse).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.w(TAG, "Connexion reussite");
                userLiveData.setValue(mAuth.getCurrentUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Connexion echouee", e);
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }


    public void addUser(Map<String, Object> user) throws Exception {
        mAuth.createUserWithEmailAndPassword(user.get("mail").toString(), user.get("password").toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d(TAG, "Inscription reussite");
                firestore.collection("users").document(authResult.getUser().getUid()).set(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Inscription echouee", e);
            }
        });
    }

    public LiveData<Map<String, Object>> getUser(String userId) {
        MutableLiveData<Map<String, Object>> userLiveData = new MutableLiveData<>();
        firestore.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        userLiveData.setValue(document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return userLiveData;
    }
}