package com.example.application_interim.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.OffreRepository;
import com.example.application_interim.view.AffichageOffreActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OffreViewModel extends ViewModel {

    private OffreRepository offreRepository = new OffreRepository();
    private List<Map<String, Object>> offresData;
    private AffichageOffreActivity activity;

    public OffreViewModel(AffichageOffreActivity activity) {
        offreRepository = new OffreRepository();
        this.activity = activity;
    }

    public void getListeOffres(String intitule, String date, String region) {

        Map<String, String> searchData = new HashMap<>();
        searchData.put("intitule", intitule);
        searchData.put("date", date);
        searchData.put("region", region);


        List<Map<String, Object>> offresData = offreRepository.getOffres(searchData, this);
        Log.d("offresDataView", offresData.toString());
        this.offresData = offresData;
    }

    public List<Map<String, Object>> getOffresData() {
        return offresData;
    }

    public void onEndQuery(){
        activity.createFragments();
    }
}
