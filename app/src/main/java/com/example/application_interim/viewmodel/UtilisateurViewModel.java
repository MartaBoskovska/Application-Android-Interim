package com.example.application_interim.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.UtilisateurRepository;
import com.google.android.gms.tasks.Task;
import com.google.type.Date;

import java.util.HashMap;
import java.util.Map;

public class UtilisateurViewModel extends ViewModel {
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public void connexionUtilisateur(String email, String motDePasse) {
        utilisateurRepository.connexionUtilisateur(email, motDePasse);
    }


    public void inscriptionUtilisateur(String firstname, String lastname, String dateofbirth, String nationality, String city, String phoneNumber, String cv, String mail, String password) {

        Map<String, Object> docData = new HashMap<>();
        docData.put("firstname", firstname);
        docData.put("lastname", lastname);
        docData.put("dateofbirth", dateofbirth);
        docData.put("nationality", nationality);
        docData.put("city", city);
        docData.put("phoneNumber", phoneNumber);
        docData.put("cv", cv);
        docData.put("mail", mail);
        docData.put("password", password);

        utilisateurRepository.addUser(docData);
    }
}
