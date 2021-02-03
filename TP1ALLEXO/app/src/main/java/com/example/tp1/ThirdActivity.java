package com.example.tp1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        String numero = "";
        numero = intent.getStringExtra("numero");
        TextView textView = (TextView) findViewById(R.id.tv1);
        textView.setText("Numéro : " + numero);

        Button button = (Button) findViewById(R.id.ButtonCall);

        String finalNumero = numero;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent callIntent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+finalNumero));
                startActivity(callIntent);

            }

        });
    }

    public void again(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
