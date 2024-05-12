package com.example.application_interim.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application_interim.R;

import org.w3c.dom.Text;

public class OffreFragment extends Fragment {

    private String titre;
    private String description;
    private String date;
    private String lieu;

    public OffreFragment() {
        super(R.layout.fragment_offre);
    }

    public void setArguments(String titre, String date, String lieu) {
        this.titre = titre;
        this.date = date;
        this.lieu = lieu;
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

        String dateText = dateView.getText().toString();
        String contractText = typeDeContratView.getText().toString();

        String text = dateText + " " + this.date;
        titreView.setText(titre);

        dateView.setText(text);
        lieuView.setText(lieu);

        return view;
    }
}
