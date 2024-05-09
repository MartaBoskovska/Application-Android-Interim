package com.example.application_interim.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.application_interim.R;

public class ConnexionEntrepriseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_entreprise);


        TextView textView_inscription = findViewById(R.id.text_inscription);

        SpannableString content = new SpannableString(textView_inscription.getText());

        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        textView_inscription.setText(content);

        textView_inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnexionEntrepriseActivity.this, InscriptionEntrepriseActivity.class);
                startActivity(intent);
            }
        });
    }


}
