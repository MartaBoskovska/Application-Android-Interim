package com.example.application_interim.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.application_interim.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CompteUtilisateurActivity extends AppCompatActivity {

    private TextView nomUtilisateurText,localisationText,aproposText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_utilisateur);

        ImageView logomsg = findViewById(R.id.logomsg);
        ImageView logorecherche = findViewById(R.id.logorecherche);
        ImageView logoprofil = findViewById(R.id.logoprofil);
        TextView textmsg = findViewById(R.id.textmsg);
        TextView textrechercher = findViewById(R.id.textrechercher);
        TextView textprofil = findViewById(R.id.textprofil);
        nomUtilisateurText = findViewById(R.id.nom_utilisateur_Text);

        aproposText=findViewById(R.id.apropos);


        logomsg.setOnClickListener(v -> {
            textmsg.setTextColor(getResources().getColor(R.color.vert));
            textrechercher.setTextColor(getResources().getColor(R.color.black));
            textprofil.setTextColor(getResources().getColor(R.color.black));
        });

        logorecherche.setOnClickListener(v -> {
            textmsg.setTextColor(getResources().getColor(R.color.black));
            textrechercher.setTextColor(getResources().getColor(R.color.vert));
            textprofil.setTextColor(getResources().getColor(R.color.black));
        });

        logoprofil.setOnClickListener(v -> {
            textmsg.setTextColor(getResources().getColor(R.color.black));
            textrechercher.setTextColor(getResources().getColor(R.color.black));
            textprofil.setTextColor(getResources().getColor(R.color.vert));
        });

        // Récupérer l'ID de l'utilisateur à partir de l'intent
        String userId = getIntent().getStringExtra("USER_ID");
        if (userId != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document("ku1JzmVL1pSYDKM3h7xE");

            docRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String firstName = document.getString("firstname");
                        String lastName = document.getString("lastname");
                        String nationality=document.getString("nationality");
                        String phone=document.getString("phoneNumber");
                        String birth=document.getString("dateofbirth");
                        String city=document.getString("city");

                        String fullName = firstName + " " + lastName;
                        nomUtilisateurText.setText(fullName);

                        aproposText.setText("NOM  :"+ firstName+"\n");
                        aproposText.append("Prénom :"+ lastName+"\n");
                        aproposText.append("Ville :"+ city+"\n");
                        aproposText.append("Nationalité :"+ nationality+"\n");
                        aproposText.append("Numéro de télephone :"+ phone+"\n");
                        aproposText.append("Date de naissance:"+ birth+"\n");
                    } else {
                        Toast.makeText(CompteUtilisateurActivity.this, "No such document", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CompteUtilisateurActivity.this, "get failed with " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User ID not found", Toast.LENGTH_SHORT).show();
        }
    }
}
