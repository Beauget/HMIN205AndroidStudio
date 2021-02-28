package com.example.tp3persistance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Inscription extends AppCompatActivity {

    // Key des données (voir cours)
    private static final String _Key_of_nom = "nom";
    private static final String _Key_of_prenom = "prenom";
    private static final String _Key_of_telephone = "tel";
    private static final String _Key_of_age = "age";
    private static final String _Key_of_userID = "userID";

    // String pour sauvegardé les données correspondants au clés
    String nom = "";
    String prenom = "";
    String age = "";
    String tel = "";
    String FILENAME = "";

    //ID user
    private String userRandomId;
    private final Random userRand = new Random();


    //gestion des zones de texte
    EditText onChange;
    TextView onChangeText;


    public void setRandId() {
        userRandomId = "User" + String.valueOf(userRand.nextInt());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null)) {
            nom = savedInstanceState.getString(_Key_of_nom);
            prenom = savedInstanceState.getString(_Key_of_prenom);
            age = savedInstanceState.getString(_Key_of_age);
            tel = savedInstanceState.getString(_Key_of_telephone);
            userRandomId = savedInstanceState.getString(_Key_of_userID);

            // On les affiches
            onChange = findViewById(R.id.nom);
            onChange.setText(nom);
            onChange = findViewById(R.id.prenom);
            onChange.setText(prenom);
            onChange = findViewById(R.id.age);
            onChange.setText(age);
            onChange = findViewById(R.id.numero);
            onChange.setText(tel);
            onChangeText = findViewById(R.id.userID);
            onChangeText.setText(userRandomId);

            try {
                FileOutputStream file = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                String concat = nom + prenom + age + tel + userRandomId;
                file.write(concat.getBytes());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(this, "onCreate() méthode cas if : " + nom + prenom + age + tel + userRandomId, Toast.LENGTH_SHORT).show();
        } else {
            setRandId();
            Toast.makeText(this, "onCreate() méthode cas else : " + userRandomId, Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_main);

        Button soum = (Button) findViewById(R.id.soum);

        soum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inscription.this, FileActivity.class);

                onChange = findViewById(R.id.nom);
                nom = onChange.getText().toString();

                onChange = findViewById(R.id.prenom);
                prenom = onChange.getText().toString();

                onChange = findViewById(R.id.age);
                age = onChange.getText().toString();

                onChange = findViewById(R.id.numero);
                tel = onChange.getText().toString();

                FILENAME = prenom.toLowerCase() + userRandomId;

                try {
                    FileOutputStream file = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    String concat = "UserId : " + userRandomId + " || " + "Nom : " + nom + " || " + "Prénom : " + prenom + " || " + "Age : " + age + " || " + "Tèl : " + tel + " Nombre d'utilisation : " + Utilisation.cpt;
                    file.write(concat.getBytes());
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                intent.putExtra(FileActivity.FILENAME, FILENAME);
                startActivity(intent);
            }
        });

        Button planning = (Button) findViewById(R.id.planning);

        planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inscription.this, Planning.class);
                startActivity(intent);

            }
        });

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

        onChangeText = findViewById(R.id.userID);
        userRandomId = onChangeText.getText().toString();
        savedInstanceState.putString(_Key_of_userID,userRandomId);


        Toast.makeText(this,"onSaveInstanceState() méthode bien appelé",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause() bien appelé", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getLifecycle().addObserver(new Utilisation());
        Toast.makeText(this,"OnResume() bien appelé",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy() bien appelé", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nom = savedInstanceState.getString(_Key_of_nom);
        prenom = savedInstanceState.getString(_Key_of_prenom);
        age = savedInstanceState.getString(_Key_of_age);
        tel = savedInstanceState.getString(_Key_of_telephone);
        userRandomId = savedInstanceState.getString(_Key_of_userID);

        // On les affiches
        onChange = findViewById(R.id.nom);
        onChange.setText(nom);
        onChange = findViewById(R.id.prenom);
        onChange.setText(prenom);
        onChange = findViewById(R.id.age);
        onChange.setText(age);
        onChange = findViewById(R.id.numero);
        onChange.setText(tel);
        onChangeText = findViewById(R.id.userID);
        onChangeText.setText(userRandomId);

        Toast.makeText(this,"onRestore() méthode bien appelé: " + nom + prenom + age + tel  + userRandomId,Toast.LENGTH_SHORT).show();
    }

    public static class Utilisation implements LifecycleObserver {
        public static int cpt = 0;
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public static void NombreUtilisation() {
            cpt++;
        }
    }
}