package com.example.tpcapteurs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.BreakIterator;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class Proximite extends AppCompatActivity implements SensorEventListener {

    ImageView iv;
    ImageView iv2;
    TextView tv;
    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximite);

        iv = (ImageView) findViewById(R.id.mesImages);
        iv2 = (ImageView) findViewById(R.id.mesImages2);
        tv = (TextView) findViewById(R.id.test);
        mySensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {

        } else {
            mySensorManager.registerListener(proximitySensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] < 5) {
                    iv.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.INVISIBLE);
                    tv.setText(R.string.proximity);
                   // iv.setImageDrawable(ContextCompat.getDrawable(null,R.drawable.radar));
                } else {
                    iv.setVisibility(View.INVISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    tv.setText(R.string.proximity2);
                }

            }
        }
    };



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
