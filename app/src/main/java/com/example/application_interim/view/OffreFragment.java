package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application_interim.R;

import org.w3c.dom.Text;

public class OffreFragment extends Fragment {

    private String offreID;
    private String userId;
    private String titre;
    private String description;
    private String date;
    private String lieu;

    private String typeContrat;

    private String missions;
    private String profil;

    public OffreFragment() {
        super(R.layout.fragment_offre);
    }

    public void setArguments(String offreID, String userId, String titre, String date, String lieu, String typeContrat, String description, String missions, String profil) {
        //Log.e("OffreFragment", "setArguments"+offreID+userId+titre+date+lieu+typeContrat+description+missions+profil);
        this.offreID = offreID;
        this.userId = userId;
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
        this.typeContrat = typeContrat;
        this.description = description;
        this.missions = missions;
        this.profil = profil;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_offre, container, false);

        TextView titreView = view.findViewById(R.id.text_title);
        TextView dateView = view.findViewById(R.id.text_date);
        TextView typeDeContratView = view.findViewById(R.id.text_contract_type);
        TextView lieuView = view.findViewById(R.id.text_region);

        String textFinalDate = dateView.getText().toString() + this.date;
        String textFinalContrat = typeDeContratView.getText().toString() + this.typeContrat;

        titreView.setText(titre);
        dateView.setText(textFinalDate);
        lieuView.setText(lieu);
        typeDeContratView.setText(textFinalContrat);

        Button buttonVoirPLus = view.findViewById(R.id.btn_see_more);

        buttonVoirPLus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OffreActivity.class);

                i.putExtra("offreID", offreID);
                i.putExtra("userId", userId);
                i.putExtra("titre", titre);
                i.putExtra("date", date);
                i.putExtra("lieu", lieu);
                i.putExtra("typeContrat", typeContrat);
                i.putExtra("description", description);
                i.putExtra("missions", missions);
                i.putExtra("profil", profil);

                startActivity(i);

            }
        });
        return view;
    }
}
