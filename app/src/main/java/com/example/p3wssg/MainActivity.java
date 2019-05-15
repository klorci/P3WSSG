package com.example.p3wssg;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.img = findViewById(R.id.imageView1);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        final MediaPlayer mpslow = MediaPlayer.create(this, R.raw.my_soundslow);
        final MediaPlayer mpplease = MediaPlayer.create(this, R.raw.my_soundplease);

        if(gyroscopeSensor == null){
            createToast("A készülék nem rendelkezik giroszkóppal! ");
            finish();
        }

        gyroscopeEventListener= new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[2]<= 0.5f && sensorEvent.values[2]> 0f ||
                        sensorEvent.values[0]<= 0.5f && sensorEvent.values[0]> 0f ||
                        sensorEvent.values[1]<= 0.5f && sensorEvent.values[1]> 0f){
                    img.setImageResource(R.mipmap.the5);

                } else if(sensorEvent.values[2]<= 1f && sensorEvent.values[2]> 0.5f ||
                        sensorEvent.values[0]<= 1f && sensorEvent.values[0]> 0.5f ||
                        sensorEvent.values[1]<= 1f && sensorEvent.values[1]> 0.5f){
                    img.setImageResource(R.mipmap.the4);
                } else if(sensorEvent.values[2]<= 1.5f && sensorEvent.values[2]> 1f ||
                        sensorEvent.values[0]<= 1.5f && sensorEvent.values[0]> 1f ||
                        sensorEvent.values[1]<= 1.5f && sensorEvent.values[1]> 1f){
                    img.setImageResource(R.mipmap.the3);
                } else if(sensorEvent.values[2]<= 2f && sensorEvent.values[2]> 1.5f ||
                        sensorEvent.values[0]<= 2f && sensorEvent.values[0]> 1.5f ||
                        sensorEvent.values[1]<= 2f && sensorEvent.values[1]> 1.5f){
                    img.setImageResource(R.mipmap.the2);
                    mpslow.start();
                } else if(sensorEvent.values[2]<= 2.5f && sensorEvent.values[2]> 2f ||
                        sensorEvent.values[0]<= 2.5f && sensorEvent.values[0]> 2f ||
                        sensorEvent.values[1]<= 2.5f && sensorEvent.values[1]> 2f){
                    img.setImageResource(R.mipmap.the1);
                    mpplease.start();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener,gyroscopeSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

    public void imageClick(View view) {
        createToast( "ez egy uzenet!");
    }

    public void createToast(CharSequence msg) {
        Toast toast = Toast.makeText(getApplicationContext(),
                msg,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}
