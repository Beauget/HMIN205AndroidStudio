package com.example.tpcapteurs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    protected ArrayList<String> missingSensor = new ArrayList<String>();
    protected boolean haveSensor[]= new boolean[35];
    public static HashMap<Integer, String> allSensors = new HashMap<Integer, String>();
    protected ListView lv;
    protected  ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},
                1);

        allSensors.put(-1, "INVALID");
        allSensors.put(1, "ACCELEROMETER");
        allSensors.put(2, "MAGNETIC_FIELD");
        allSensors.put(4, "GYROSCOPE");
        allSensors.put(5, "LIGHT");
        allSensors.put(6, "PRESSURE");
        allSensors.put(8, "PROXIMITY");
        allSensors.put(9, "GRAVITY");
        allSensors.put(10, "LINEAR_ACCELERATION");
        allSensors.put(11, "ROTATION_VECTOR");
        allSensors.put(12, "RELATIVE_HUMIDITY");
        allSensors.put(13, "AMBIENT_TEMPERATURE");
        allSensors.put(14, "MAGNETIC_FIELD_UNCALIBRATED");
        allSensors.put(15, "GAME_ROTATION_VECTOR");
        allSensors.put(16, "GYROSCOPE_UNCALIBRATED");
        allSensors.put(17, "SIGNIFICANT_MOTION");
        allSensors.put(18, "STEP_DETECTOR");
        allSensors.put(19, "STEP_COUNTER");
        allSensors.put(20, "GEOMAGNETIC_ROTATION_VECTOR");
        allSensors.put(21, "HEART_RATE");
        allSensors.put(28, "POSE_6DOF");
        allSensors.put(29, "STATIONARY_DETECT");
        allSensors.put(30, "MOTION_DETECT");
        allSensors.put(31, "HEART_BEAT");
        allSensors.put(33, "ADDITIONAL_INFO");
        allSensors.put(34, "LOW_LATENCY_OFFBODY_DETECT");
        allSensors.put(35, "ACCELEROMETER_UNCALIBRATED");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        setMissingSensor();


        Button b1 = (Button) findViewById(R.id.button2);
        lv = (ListView) findViewById(R.id.listView1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, missingSensor);

                lv.setAdapter(adapter);
            }
        });

        Button acc = (Button) findViewById(R.id.buttonAcc);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Accel.class);
                startActivity(intent);
            }
        });


    Button proximite = (Button) findViewById(R.id.proximite);

        proximite.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Proximite.class);
            startActivity(intent);
        }
    });
}

    public void listSensor(View v) {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer sensorDesc = new StringBuffer();
        for (Sensor sensor : sensors) {
            sensorDesc.append("\tNom : " + sensor.getName() + "\r\n");
        }
        Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();
    }

    public void listSensorDetails(View v) {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer sensorDesc = new StringBuffer();
        for (Sensor sensor : sensors) {
            sensorDesc.append("CAPTEURS DETECTES : \r\n");
            sensorDesc.append("\tNom : " + sensor.getName() + "\r\n");
            sensorDesc.append("\tType : " + sensor.getType() + "\r\n");
            sensorDesc.append("Version : " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution : " +
                    sensor.getResolution() + "\r\n");
            sensorDesc.append("Consommation éléctrique en utilisation : " +
                    sensor.getPower() +"\r\n");
            sensorDesc.append("Vendeur : " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." +
                    sensor.getMaximumRange() + "\r\n");
        }
        Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();
    }

    public void setMissingSensor(){
        for (int i = 0; i < haveSensor.length; i++ ){
            if ( !haveSensor[i]) {
                if (allSensors.get(i) != null) {
                    missingSensor.add(allSensors.get(i));
                }
            }
        }
    }


}
