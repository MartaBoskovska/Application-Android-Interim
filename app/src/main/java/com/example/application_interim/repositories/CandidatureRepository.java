package com.example.application_interim.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application_interim.viewmodel.CreationCandidatureViewModel;
import com.example.application_interim.viewmodel.CandidatureViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CandidatureRepository {

    private final FirebaseFirestore firestore;
    private List<Map<String, Object>> matchingCandidatures = new ArrayList<>();

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

    public LiveData<List<Map<String, Object>>> getCandidatures(String offerID, CandidatureViewModel candidatureViewModel) {
        Log.d("CANDIDATURE", "getCandidaturesRepository + " + offerID);
        LiveData<List<Map<String, Object>>> candidatures = new MutableLiveData<>(new ArrayList<>());
        firestore.collection("applications").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot queryDocumentSnapshots = task.getResult();
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot candidature : queryDocumentSnapshots) {
                        Log.d("CANDIDATURE", candidature.getData().toString());
                        if (candidature.getString("offerID").equals(offerID))
                        {
                            Log.d("CANDIDATUREREPO", candidature.getData().toString());
                            Map<String, Object> map = candidature.getData();
                            this.matchingCandidatures.add(map);
                            candidatureViewModel.addCandidature(map);
                            candidatures.getValue().add(map);
                        }

                    }
                }
                candidatureViewModel.onEndQuery();
            } else {
                Log.e("ERROR_GET_OFFERS", "Error getting documents: ", task.getException());

            }
        });
        return candidatures;
    }
}

