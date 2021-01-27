package com.example.tp1java;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Mon objectif ici est de reproduire ma version XML en Java


        //Création du Layout
        LinearLayout javaVersion = new LinearLayout(this);
        javaVersion.setOrientation(LinearLayout.VERTICAL);


        //Toute les vues de l'applicatiion
        TextView textViewNom = new TextView(this);
        textViewNom.setText("Nom");

        EditText editTextNom = new EditText(this);
        editTextNom.setHint("Saisir votre nom");

        TextView textViewPrenom = new TextView(this);
        textViewPrenom.setText("Prénom");

        EditText editTextPrenom = new EditText(this);
        editTextPrenom.setHint("Saisir votre prénom");

        TextView textViewAge = new TextView(this);
        textViewAge.setText("Age");

        EditText editTextAge = new EditText(this);
        editTextAge.setHint("Saisir votre âge");

        TextView textViewCompetence = new TextView(this);
        textViewCompetence.setText("Compétence");

        EditText editTextCompetence = new EditText(this);
        editTextCompetence.setHint("Saisir votre compétence");

        TextView textViewNumero = new TextView(this);
        textViewNumero.setText("N°.Tel");

        EditText editTextNumero = new EditText(this);
        editTextNumero.setHint("Saisir votre numéro");
        //Pour obliger la saisie d'un numéro et pas un texte
        editTextNumero.setInputType(InputType.TYPE_CLASS_PHONE);


        Button buttonValidate = new Button(this);
        buttonValidate.setText("Valider");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        buttonValidate.setLayoutParams(lp);
        buttonValidate.setBackgroundColor(Color.parseColor("#FF03DAC5"));
        buttonValidate.setTextColor(Color.parseColor("#FFFFFFFF"));

        //Méthode pour reproduire la validation du bouton comme en XML
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valider((Button)buttonValidate);
            }
        });



    //On ajoute les vues à notre layout
        javaVersion.addView(textViewNom);
        javaVersion.addView(editTextNom);


        javaVersion.addView(textViewPrenom);
        javaVersion.addView(editTextPrenom);

        javaVersion.addView(textViewAge);
        javaVersion.addView(editTextAge);


        javaVersion.addView(textViewCompetence);
        javaVersion.addView(editTextCompetence);

        javaVersion.addView(textViewNumero);
        javaVersion.addView(editTextNumero);

        javaVersion.addView(buttonValidate);

        setContentView(javaVersion);





    }

    public void valider(View v) {
        Toast.makeText(this,"Formulaire validé !", Toast.LENGTH_LONG).show();
    }
}