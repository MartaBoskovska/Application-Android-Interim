package com.example.application_interim.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CandidatureRepository {

    private final FirebaseFirestore firestore;

    public CandidatureRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public LiveData<Boolean> addCandidature(Map<String, Object> candidature) {

        MutableLiveData<Boolean> success = new MutableLiveData<>();
        firestore.collection("applications").add(candidature).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("CANDIDATURE", "DocumentSnapshot added with ID: " + documentReference.getId());
                success.setValue(true);
            }
        });
        return success;
    }
}

