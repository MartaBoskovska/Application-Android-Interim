package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.application_interim.R;


public class ApercuCandidatureFragment extends Fragment {

    private String offreID;
    private String userId;
    private String titreOffre;
    private String name;
    private String surname;

    public ApercuCandidatureFragment() {
        super(R.layout.fragment_apercu_candidature);
    }

    public void setArguments(String offreID, String userId, String titreOffre, String name, String surname) {
        this.offreID = offreID;
        this.userId = userId;
        this.titreOffre = titreOffre;
        this.name = name;
        this.surname = surname;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_apercu_candidature, container, false);

        TextView titreView = view.findViewById(R.id.text_title);
        TextView nameView = view.findViewById(R.id.text_nom_user);
        String name_surname = name + " " + surname;
        nameView.setText(name_surname);
        titreView.setText(titreOffre);

        return view;
    }
}