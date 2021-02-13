package com.example.tpcapteurs;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Accel extends AppCompatActivity implements SensorEventListener {

    protected TextView tv;
    protected  TextView values;
    float x,y,z;
    private SensorManager mSensorManager;
    private static final float SHAKE_THRESHOLD = 3.25f;
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;
    private Sensor mAccelerometer;
    CameraDevice cameraDevice;
    private CameraManager cameraManager;
    String cameraId;
    float [] history = new float[2];
    String [] direction = {"NULL","NULL"};
    protected LinearLayout layout;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        cameraManager = (CameraManager)
                getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel);

        this.layout = findViewById(R.id.layout);
        this.tv = findViewById(R.id.direction);
        this.values = findViewById(R.id.values);


    }
    // Important : on doit nous même lancer/éteindre le capteur qu'on utilise
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener((SensorEventListener) this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorEventListener) this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;

                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    Boolean light = true;
                    if (light) {
                        try {

                            cameraManager.setTorchMode(cameraId, true);
                            System.out.println("CAMERA ON CAMERA ON CAMERA ON");
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                        light = false;
                    } else {

                        try {

                            cameraManager.setTorchMode(cameraId, false);
                            System.out.println("CAMERA OFF CAMERA OFF CAMERA OFF");
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }

                        light = true;
                    }
                }
            }

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            String txt = new String();


            txt = "Valeur Accelerometre => \nX : " + x;

            txt = txt + " \nY : " + y;

            txt = txt + " \nZ : " + z;

            values.setText(txt);


        }
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if ( x <= 0.15 ){
            layout.setBackgroundColor(Color.parseColor("#47b550"));
            tv.setTextColor(Color.parseColor("#0D4C7C"));
            values.setTextColor(Color.parseColor("#0D4C7C"));

        }
        if(x > 0.15 && x <= 1 ){
            layout.setBackgroundColor(Color.parseColor("#000000"));
            tv.setTextColor(Color.parseColor("#0D4C7C"));
            values.setTextColor(Color.parseColor("#0D4C7C"));
        }
        if ( x > 1 ){
            layout.setBackgroundColor(Color.parseColor("#b53333"));
            tv.setTextColor(Color.parseColor("#0D4C7C"));
            values.setTextColor(Color.parseColor("#0D4C7C"));

        }

        float xChange = history[0] - event.values[0];
        float yChange = history[1] - event.values[1];

        history[0] = event.values[0];
        history[1] = event.values[1];

        if (xChange > 2){
            direction[0] = "GAUCHE";
        }
        else if (xChange < -2){
            direction[0] = "DROITE";
        }

        if (yChange > 2){
            direction[1] = "BAS";
        }
        else if (yChange < -2){
            direction[1] = "HAUT";
        }

        String res = "DIRECTION : " + direction[0] + ",  " +  direction[1];
        tv.setText(res);
    }

    // Inutile pour le TP
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
