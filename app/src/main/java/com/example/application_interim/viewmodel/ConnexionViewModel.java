package com.example.application_interim.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.application_interim.repositories.UtilisateurRepository;
import com.google.android.gms.tasks.Task;

public class ConnexionViewModel extends ViewModel {
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public Task connexionUtilisateur(String email, String motDePasse) {
        return utilisateurRepository.connexionUtilisateur(email, motDePasse);
    }

}
