package com.example.tp3persistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Inscription extends AppCompatActivity {




    // Key des données (voir cours)
    private static final String _Key_of_nom = "nom";
    private static final String _Key_of_prenom = "prenom";
    private static final String _Key_of_telephone = "tel";
    private static final String _Key_of_age = "age";
    private static final String _Key_of_password = "password";
    private static final String _Key_of_userID = "userID";

    // String pour sauvegardé les données correspondants au clés
    String nom = "";
    String prenom = "";
    String age = "";
    String tel = "";
    String password = "";

    //ID user
    private String userRandomId;
    private Random userRand = new Random();


    //gestion des zones de texte
    EditText onChange;
    TextView onChangeText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) ) {
           nom = savedInstanceState.getString(_Key_of_nom);
           prenom = savedInstanceState.getString(_Key_of_prenom);
           age = savedInstanceState.getString(_Key_of_age);
           tel = savedInstanceState.getString(_Key_of_telephone);
           password = savedInstanceState.getString(_Key_of_password);
           userRandomId = savedInstanceState.getString(_Key_of_userID);

           // On les affiches
            onChange = findViewById(R.id.nom); onChange.setText(nom);
            onChange = findViewById(R.id.prenom); onChange.setText(prenom);
            onChange = findViewById(R.id.age); onChange.setText(nom);
            onChange = findViewById(R.id.numero); onChange.setText(tel);
            onChangeText = findViewById(R.id.userID); onChangeText.setText(userRandomId);

            Toast.makeText(this,"onCreate() méthode cas if : " + nom + prenom + age + tel + password + userRandomId,Toast.LENGTH_SHORT).show();
        }
        else {
            userRandomId = "User" + String.valueOf(userRand.nextInt());
            Toast.makeText(this, "onCreate() méthode cas else : " + userRandomId,Toast.LENGTH_SHORT ).show();
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        onChange = findViewById(R.id.nom);
        nom = onChange.getText().toString();
        savedInstanceState.putString(_Key_of_nom,nom);

        onChange = findViewById(R.id.prenom);
        prenom = onChange.getText().toString();
        savedInstanceState.putString(_Key_of_prenom,prenom);

        onChange = findViewById(R.id.age);
        age = onChange.getText().toString();
        savedInstanceState.putString(_Key_of_age,age);

        onChange = findViewById(R.id.numero);
        tel = onChange.getText().toString();
        savedInstanceState.putString(_Key_of_telephone,tel);

        onChange = findViewById(R.id.password);
        password = onChange.getText().toString();
        savedInstanceState.putString(_Key_of_password,password);

        onChangeText = findViewById(R.id.userID);
        userRandomId = onChangeText.getText().toString();
        savedInstanceState.putString(_Key_of_userID,userRandomId);


        Toast.makeText(this,"onSaveInstanceState() méthode bien appelé",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            nom = savedInstanceState.getString(_Key_of_nom);
            prenom = savedInstanceState.getString(_Key_of_nom);
            age = savedInstanceState.getString(_Key_of_nom);
            tel = savedInstanceState.getString(_Key_of_nom);
            password = savedInstanceState.getString(_Key_of_nom);
            userRandomId = savedInstanceState.getString(_Key_of_userID);

            onChange = findViewById(R.id.nom); onChange.setText(nom);
            onChange = findViewById(R.id.prenom); onChange.setText(prenom);
            onChange = findViewById(R.id.age); onChange.setText(nom);
            onChange = findViewById(R.id.numero); onChange.setText(tel);
            onChangeText = findViewById(R.id.userID); onChangeText.setText(userRandomId);

            Toast.makeText(this,"onRestoreInstanceState() méthode bien appelé",Toast.LENGTH_SHORT).show();
        }
    }
}