package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

public class TrainActivity extends AppCompatActivity {
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
        setContentView(R.layout.train_activity);

    }

    public void send(View view) {
        View table = (TableLayout) findViewById(R.id.res);
        table.setVisibility(View.VISIBLE);
    }
}
