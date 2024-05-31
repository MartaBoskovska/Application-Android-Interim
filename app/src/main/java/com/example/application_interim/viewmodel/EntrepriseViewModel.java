package com.example.application_interim.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.EntrepriseRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class EntrepriseViewModel extends ViewModel {

    private EntrepriseRepository entrepriseRepository = new EntrepriseRepository();
    private LiveData<Map<String, Object>> entrepriseLiveData = null;
    private String entrepriseID = "";
    public LiveData<FirebaseUser> connexionEntreprise (String email, String motDePasse) {
        return entrepriseRepository.connexionEntreprise(email, motDePasse);
    }


    public void inscriptionEntreprise(String companyname, String mail, String password, String address, String siteweb, String linkedin, String facebook, String others, String phonenumber) throws Exception{

        Map<String, Object> docData = new HashMap<>();
        docData.put("companyname", companyname);
        docData.put("mail", mail);
        docData.put("password", password);
        docData.put("address", address);
        docData.put("siteweb", siteweb);
        docData.put("linkedin", linkedin);
        docData.put("facebook", facebook);
        docData.put("others", others);
        docData.put("phonenumber", phonenumber);

        entrepriseRepository.addEntreprise(docData);
    }

    public void setEntrepriseID(String entrepriseID) {
        this.entrepriseID = entrepriseID;
    }

    public String getEntrepriseID() {
        return entrepriseID;
    }

    public LiveData<Map<String, Object>> getEntreprise(String entrepriseID) {
        if (entrepriseLiveData != null) {
            return entrepriseLiveData;
        }
        this.entrepriseLiveData = entrepriseRepository.getEntreprise(entrepriseID);
        return entrepriseLiveData ;
    }
}
