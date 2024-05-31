package com.example.application_interim.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.application_interim.R;
import com.example.application_interim.viewmodel.CandidatureViewModel;
import com.example.application_interim.viewmodel.CreationCandidatureViewModel;
import com.example.application_interim.viewmodel.OffreViewModel;

import java.util.List;
import java.util.Map;

public class AffichageCandidaturesActivity extends AppCompatActivity {
    private CandidatureViewModel candidatureViewModel;
    private String offreId;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_candidatures);

        this.candidatureViewModel = new CandidatureViewModel(this);
        Log.d("AffichageCandidatureActivity", "onCreateCandidatureActivity");
        LiveData<List<Map<String, Object>>> candidatureData = candidatureViewModel.getCompleteListeCandidatures();
        candidatureData.observe(this, candidaturesData -> {
            this.createFragments();
        });

    }

    public void createFragments() {
        Log.d("AffichageCandidatureActivity", "createFragmentsCandidature");
        Log.d("AffichageCandidatureActivity", "createFragmentsCandidature");
        List<Map<String, Object>> candidatureData = candidatureViewModel.getCandidaturesData();

        Log.d("CANDIDATURES",candidatureData.toString());
        for (Map<String, Object> candidature : candidatureData) {
            String name = (String) candidature.get("name");
            String surname = (String) candidature.get("surname");
            String offreID = (String) candidature.get("offerID");
            String userID = (String) candidature.get("userID");
            String titreOffre = (String) candidature.get("offerTitle");


            // Creer un fragment pour chaque offre
            ApercuCandidatureFragment candidatureFragment = new ApercuCandidatureFragment();
            candidatureFragment.setArguments(offreID, userID, titreOffre, name, surname);

            // Ajouter le fragment a la liste
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, candidatureFragment).commit();

        }
    }
}
