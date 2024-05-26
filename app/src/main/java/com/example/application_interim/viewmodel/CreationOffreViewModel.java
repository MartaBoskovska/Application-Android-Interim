package com.example.application_interim.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.OffreRepository;

import java.util.HashMap;
import java.util.Map;

public class CreationOffreViewModel extends ViewModel {
    private OffreRepository offreRepository = new OffreRepository();
    public LiveData<Boolean> addOffre(String pays, String region, String ville, String intitule, String typeContrat, String remuneration, String description,String secteur, String entreprise) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("country", pays);
        docData.put("region", region);
        docData.put("city", ville);
        docData.put("employerId", entreprise); // Add the employer ID to the offer (to link the offer to the employer
        docData.put("sector", secteur);
        docData.put("intitule", intitule);
        docData.put("contract", typeContrat);
        docData.put("publicationDate", "20/2/2024"); // Add the publication date to the offer (to know when the offer was published)
        docData.put("remuneration", remuneration);
        docData.put("description", description);
        return offreRepository.addOffre(docData);
    }
}
