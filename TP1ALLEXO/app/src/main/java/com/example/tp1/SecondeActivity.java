package com.example.tp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        Intent intent = getIntent();
        // On vérifie que tout est en place
        if(intent != null ) {
            String nom = "";
            String age = "";
            String prenom = "";
            String competence = "";
            String numero = "";
                nom = intent.getStringExtra("nom");
                prenom = intent.getStringExtra("prenom");
                competence = intent.getStringExtra("competence");
                age = intent.getStringExtra("age");
                numero = intent.getStringExtra("numero");

            TextView textView = (TextView) findViewById(R.id.tv1);
            TextView textView2 = (TextView) findViewById(R.id.tv2);
            TextView textView3 = (TextView) findViewById(R.id.tv3);
            TextView textView4 = (TextView) findViewById(R.id.tv4);
            TextView textView5 = (TextView) findViewById(R.id.tv5);

            textView.setText("Nom : " + nom);
            textView2.setText("Prénom : " + prenom);
            textView3.setText("Age : " + age);
            textView4.setText("Compétence : " + competence);
            textView5.setText(numero);
        }
    }

    public void retour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void inception(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        TextView textNumero = (TextView) findViewById(R.id.tv5);
        String numeroS = textNumero.getText().toString();
        intent.putExtra("numero",numeroS);
        startActivity(intent);
    }
}
