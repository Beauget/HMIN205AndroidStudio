package com.example.td1exo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        /* Exercice 1  Code 1
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Hello Android");
        setContentView(tv); */

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_file_name);
        }
}
