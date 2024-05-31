package com.example.application_interim.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.CandidatureRepository;
import com.example.application_interim.repositories.OffreRepository;
import com.example.application_interim.view.AffichageCandidaturesActivity;
import com.example.application_interim.view.AffichageOffreActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidatureViewModel {
    private CandidatureRepository candidatureRepository;
    private List<Map<String, Object>> candidaturesData;
    private List<Map<String, Object>> offresData;
    private AffichageCandidaturesActivity activity;

    private OffreRepository offreRepository;

    public CandidatureViewModel(AffichageCandidaturesActivity activity) {
        this.candidatureRepository = new CandidatureRepository();
        this.offreRepository = new OffreRepository();
        this.offresData = new ArrayList<>();
        this.candidaturesData = new ArrayList<>();
        this.activity = activity;
    }

//    public void getListeCandidatures(String offreID) {
//        this.candidaturesData = candidatureRepository.getCandidatures(offreID, this);
//    }

    public LiveData<List<Map<String, Object>>> getCompleteListeCandidatures() {
        Log.d("CANDIDATURE", "getCompleteListeCandidatures");
        LiveData<List<Map<String, Object>>> candidatures = new MutableLiveData<>();
        LiveData<List<Map<String, Object>>> offres = offreRepository.getAllOffers(this);
        return candidatures;
    }


    public List<Map<String, Object>> getCandidaturesData() {
        return candidaturesData;
    }

    public void addOffre(Map<String, Object> offre) {
        Log.d("CANDIDATUREVM", "addOffre" + offre.toString());
        offresData.add(offre);
    }

    public void addAllOffres(List<Map<String, Object>> offres) {
        this.offresData.addAll(offres);
    }

    public void onEndQueryEntreprises() {
        Log.d("CANDIDATURE", "onEndQueryEntreprises" + offresData.toString());
        for (Map<String, Object> offre : offresData) {
            String offreID = (String) offre.get("offreID");
            Log.d("CANDIDATUREVM", "EnDQueryoffreID" + offreID);
            candidatureRepository.getCandidatures(offreID, this);
        }
    }

    public void addCandidature(Map<String, Object> candidature) {
        Log.d("CANDIDATUREVM", "addCandidature" + candidature.toString());
        candidaturesData.add(candidature);
    }

    public void onEndQuery() {
        Log.d("CANDIDATUREVM", "onEndQuery" + candidaturesData.toString());
        activity.createFragments();
    }
}
