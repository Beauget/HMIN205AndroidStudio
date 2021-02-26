package com.example.tp3persistance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileActivity extends AppCompatActivity {
    public static String FILENAME;
    TextView t;
    String concat = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        FILENAME = intent.getStringExtra(FILENAME);
        setContentView(R.layout.file_activity);
        try {
            Context context = getApplicationContext();
            InputStream inputStream = context.openFileInput(FILENAME);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                while ((receiveString = bufferedReader.readLine()) != null) {
                    concat += receiveString;
                    Log.e("File", "Read: " + receiveString);
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("File", "Not found: " + e.toString());
        } catch (IOException e) {
            Log.e("File", "Can't read: " + e.toString());
        }
        t = findViewById(R.id.text1);
        t.setText(concat.toString());


    }

}
