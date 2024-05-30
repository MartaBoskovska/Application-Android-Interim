package com.example.application_interim.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.CandidatureRepository;

import java.util.HashMap;
import java.util.Map;

public class CandidatureViewModel extends ViewModel {

    CandidatureRepository candidatureRepository = new CandidatureRepository();

    public LiveData<Boolean> addCandidature(String offreID, String userID, String prenom, String nom, String nationality, String date, String lettreDeMotivation) {
        Map<String, Object> candidature = new HashMap<>();
        candidature.put("userID", userID);
        candidature.put("offerID", offreID);
        candidature.put("name", prenom);
        candidature.put("surname", nom);
        candidature.put("nationality", nationality);
        candidature.put("date", date);
        candidature.put("coverLetter", lettreDeMotivation);

        return candidatureRepository.addCandidature(candidature);
    }
}
