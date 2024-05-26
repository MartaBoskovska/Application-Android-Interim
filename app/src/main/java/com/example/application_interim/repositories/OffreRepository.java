package com.example.application_interim.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application_interim.viewmodel.OffreViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OffreRepository {

    private final FirebaseFirestore firestore;
    private List<Map<String, Object>> matchingOffers = new ArrayList<>();

    public OffreRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public List<Map<String, Object>> getOffres(Map<String, String> searchItems, OffreViewModel offreViewModel) {
        Log.d("searchItems", searchItems.toString() + "getOffres");
        firestore.collection("offers").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot queryDocumentSnapshots = task.getResult();
                if (queryDocumentSnapshots != null) {
                    for (QueryDocumentSnapshot offre : queryDocumentSnapshots) {
                        Log.d("OFFRE", offre.getData().toString());
                        if (offre.getString("intitule").equals(searchItems.get("intitule")) &&
                                offre.getString("region").equals(searchItems.get("region")) &&
                                offre.getString("publicationDate").equals(searchItems.get("date"))) {
                            this.matchingOffers.add(offre.getData());
                            Log.d("matchingOffersssss", matchingOffers.toString());
                        }
                    }
                    offreViewModel.onEndQuery();
                }
            } else {
                Log.e("ERROR_GET_OFFERS", "Error getting documents: ", task.getException());

            }
        });
        Log.d("matchingOffers2", matchingOffers.toString());
        return matchingOffers;
    }

    public LiveData<Boolean> addOffre(Map<String, Object> offre) {
        MutableLiveData<Boolean> success = new MutableLiveData<>();
        firestore.collection("offers").add(offre).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("OFFRE", "DocumentSnapshot added with ID: " + documentReference.getId());
                success.setValue(true);
            }
        });
        return success;
    }


    /*public List<Map<String, Object>> getOffres(Map<String, String> searchItems) {

        firestore.collection("offers").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot offre : queryDocumentSnapshots) {
                        Log.d("OFFRE", offre.getData().toString());
                        if (offre.getString("intitule").equals(searchItems.get("intitule")) &&
                                offre.getString("region").equals(searchItems.get("region")) &&
                                offre.getString("publicationDate").equals(searchItems.get("date"))) {
                            this.matchingOffers.add(offre.getData());
                            Log.d("matchingOffersssss", matchingOffers.toString());
                        }
                    }

                })
                .addOnFailureListener(e -> {
                    Log.e("ERROR_GET_OFFERS", "Error getting documents: ", e);
                });
        Log.d("matchingOffers2", matchingOffers.toString());

        return matchingOffers;
    }*/


}
