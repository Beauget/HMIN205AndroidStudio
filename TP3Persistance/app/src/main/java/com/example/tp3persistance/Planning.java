package com.example.tp3persistance;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planning extends AppCompatActivity {
    private static final int MAX_LENGTH = 50;


    //méthode lire/ecrire fichier pour exo5
    // https://mathias-seguy.developpez.com/tutoriels/android/gerer-fichiers-applications/

    public static void ecrireFichier(String data, String fileName, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            e.toString();
        }

    }

    public static void lireFichier(List<String> lignes, String fileName, Context context) {
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                while ((receiveString = bufferedReader.readLine()) != null) {
                    lignes.add(receiveString);
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            e.toString();
        } catch (IOException e) {
            e.toString();
        }

    }

    //https://stackoverflow.com/questions/12116092/android-random-string-generator/12116194
    public static String randomString() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(MAX_LENGTH);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
    //Exercice 5
    private PlanningView model8h;
    private PlanningView model10h;
    private PlanningView model14h;
    private PlanningView model16h;
    private final static String fileName = "planning";
    private static List<String> planningRes = new ArrayList<String>();
    TextView setMyText;

    PlanningDAO planningDAO;






    @Override
    public void onCreate(Bundle savedInstanceState) {

        //Exercice 6
        AppRoom myDb = Room.databaseBuilder(getApplicationContext(),AppRoom.class,"planning").allowMainThreadQueries().build();
        PlanningDAO myDAO = myDb.planningDAO();

        myDAO.delete(new PlanningDatabase(0,"08h-10h", "Rencontre client Dupont"));
        myDAO.delete(new PlanningDatabase(1,"10h-12h", "Travailler le dossier recrutement"));
        myDAO.delete(new PlanningDatabase(2,"14h-16h", "Réunion équipe"));
        myDAO.delete(new PlanningDatabase(3,"14h-16h", "Préparation dossier vente"));
        myDAO.insert(new PlanningDatabase(0,"08h-10h", "Rencontre client Dupont"));
        myDAO.insert(new PlanningDatabase(1,"10h-12h", "Travailler le dossier recrutement"));
        myDAO.insert(new PlanningDatabase(2,"14h-16h", "Réunion équipe"));
        myDAO.insert(new PlanningDatabase(3,"14h-16h", "Préparation dossier vente"));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.planning_activity);


        //récupération des data du viewModel
        PlanningView modelView = new ViewModelProvider(this).get(PlanningView.class);
        setMyText = findViewById(R.id.rdv8hExo4);
        setMyText.setText(modelView.Rdv_8h);

        setMyText = findViewById(R.id.rdv10hExo4);
        setMyText.setText(modelView.Rdv_10h);

        setMyText = findViewById(R.id.rdv14hExo4);
        setMyText.setText(modelView.Rdv_14h);

        setMyText = findViewById(R.id.rdv16hExo4);
        setMyText.setText(modelView.Rdv_16h);


        //récupération via le ViewModel
        model8h = new ViewModelProvider(this).get(PlanningView.class);
        model10h = new ViewModelProvider(this).get(PlanningView.class);
        model14h = new ViewModelProvider(this).get(PlanningView.class);
        model16h = new ViewModelProvider(this).get(PlanningView.class);

        StringBuilder dataRdv = new StringBuilder();
        dataRdv.append("Rencontre client Dupont").append("\n");
        dataRdv.append("Travailler le dossier recrutement").append("\n");
        dataRdv.append("Réunion équipe").append("\n");
        dataRdv.append("Préparation dossier vente").append("\n");


        //Ecriture rdv de base + lecture
        ecrireFichier(dataRdv.toString(),fileName,getApplicationContext());
        lireFichier(planningRes,fileName,this);



        //Création observer + appel méthode get ViewModel par rdv
        final Observer<String> rdv8hObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String data) {
                setMyText = findViewById(R.id.rdv8h);
                setMyText.setText(data);
            }
        };

        model8h.getRdv_8h().observe(this,rdv8hObserver);
        //    model8h.setRdv_8h(planningRes.get(0));
        model8h.setRdv_8h(myDAO.getByHeure("08h-10h").intitule_rdv);

        //Création observer + appel méthode get ViewModel par rdv
        final Observer<String> rdv10hObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String data) {
                setMyText = findViewById(R.id.rdv10h);
                setMyText.setText(data);
            }
        };

        model10h.getRdv_10h().observe(this,rdv10hObserver);
     //   model10h.setRdv_10h(planningRes.get(1));
        model10h.setRdv_10h(myDAO.getByHeure("10h-12h").intitule_rdv);

        //Création observer + appel méthode get ViewModel par rdv
        final Observer<String> rdv14hObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String data) {
                setMyText = findViewById(R.id.rdv14h);
                setMyText.setText(data);
            }
        };

        model14h.getRdv_14h().observe(this,rdv14hObserver);
       // model14h.setRdv_14h(planningRes.get(2));
        model14h.setRdv_14h(myDAO.getByHeure("14h-16h").intitule_rdv);

        //Création observer + appel méthode get ViewModel par rdv
        final Observer<String> rdv16hObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String data) {
                setMyText = findViewById(R.id.rdv16h);
                setMyText.setText(data);
            }
        };

        model16h.getRdv_16h().observe(this,rdv16hObserver);
        //model16h.setRdv_16h(planningRes.get(2));
        model16h.setRdv_16h(myDAO.getByHeure("14h-16h").intitule_rdv);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                model8h.setRdv_8h(planningRes.get(0));
                model10h.setRdv_10h(planningRes.get(1));
                model14h.setRdv_14h(planningRes.get(2));
                model16h.setRdv_16h(planningRes.get(3));
                planningRes.clear();

           /*     StringBuilder dataRandom = new StringBuilder();
                dataRandom.append(randomString()).append("\n");
                dataRandom.append(randomString()).append("\n");
                dataRandom.append(randomString()).append("\n");
                dataRandom.append(randomString()).append("\n");*/

              //  ecrireFichier(dataRandom.toString(),fileName,getApplicationContext());
                lireFichier(planningRes,fileName,getApplicationContext());

                handler.postDelayed(this, 1000);
            }
        });



    }

}
