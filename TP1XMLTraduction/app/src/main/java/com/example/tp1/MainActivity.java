package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Petite méthode d'affichage post validation (pour l'expérience utilisateur)

    public void valider(Button v) {
        v.setText(R.string.formulaire_valide);
        Toast.makeText(this, R.string.formulaire_valide, Toast.LENGTH_LONG).show();
    }
}