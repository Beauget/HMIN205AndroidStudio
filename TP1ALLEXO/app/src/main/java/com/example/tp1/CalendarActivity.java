package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    protected EditText monEvenement;
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
        setContentView(R.layout.calendar_activity);

        this.monEvenement = (EditText) findViewById(R.id.monEvenement);

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendrier);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int annee, int mois,
                                            int jours) {
                Toast.makeText(getApplicationContext(), "Evenement valider : " + ""+jours+"/"+mois+"/"+annee + ", Titre : " + monEvenement.getText() , Toast.LENGTH_LONG).show();

            }
        });

    }

    }
