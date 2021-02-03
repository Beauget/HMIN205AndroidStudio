package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Accueil :
                Intent intentBase = new Intent(this, MainActivity.class);
                this.startActivity(intentBase);
                return true;
            case  R.id.exo8Train :
                Intent intentTrain = new Intent(this, TrainActivity.class);
                this.startActivity(intentTrain);
                return true;
            case R.id.exo9Calendar :
                Intent intentCalendar = new Intent(this, CalendarActivity.class);
                this.startActivity(intentCalendar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE},
                1);

    }


    // Exercice 5
    public void valider(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Demande de confirmation");

        builder.setMessage("Voulez vous continer ?");
        // Touche oui
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Formulaire validé !", Toast.LENGTH_SHORT).show();
            }

        });

        // Touche non
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Formulaire annulé !", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
    // Fin Exercice 5

    // Exercice 6
    public void changeActivity(View view) {
        Intent intent = new Intent(this, SecondeActivity.class);
        EditText  nom = (EditText) findViewById(R.id.nom);
        EditText  prenom = (EditText) findViewById(R.id.prenom);
        EditText age = (EditText) findViewById(R.id.age);
        EditText  competence = (EditText) findViewById(R.id.competence);
        EditText  numero = (EditText) findViewById(R.id.telephone);

        String nomS = nom.getText().toString();
        String prenomS = prenom.getText().toString();
        String ageS = age.getText().toString();
        String competenceS = competence.getText().toString();
        String numeroS = numero.getText().toString();

        intent.putExtra("nom",nomS);
        intent.putExtra("prenom",prenomS);
        intent.putExtra("age",ageS);
        intent.putExtra("competence",competenceS);
        intent.putExtra("numero",numeroS);
        startActivity(intent);
    }

    // Fin Exercice 6


}