package com.example.td1exo4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button button;

    private Button button2;
    private Button Long;
    private TextView t;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button) findViewById(R.id.mainbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Message Bouton 1", Toast.LENGTH_LONG).show();
            }
        });


        /* Bouton 2 sans clique long
        button2 = (Button) findViewById(R.id.bouton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Message Toast !", Toast.LENGTH_LONG).show();
            }
        }); */

        /* Bouton avec clique long */

        button2 = (Button) findViewById(R.id.bouton2);
        button2.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Message Toast !", Toast.LENGTH_LONG).show();

                return true;
            }
        });


    // Afficher "Exercice 4"
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Afficher : ");
        alertDialog.setMessage("Exercice 4");
        // Ajout d'un bouton ok pour permettre de voir le reste
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}