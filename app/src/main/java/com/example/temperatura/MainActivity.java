package com.example.temperatura;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager SensorManager;
    private Sensor temperatureSensor;
    private TextView txtTemperature;

    @Override
    protected void onCreate(Bundle instancia)
    {
        super.onCreate(instancia);
        setContentView(R.layout.activity_main);
txtTemperature = findViewById(R.id.txtTemperature);
SensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
temperatureSensor = SensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }
@Override
protected void onResume()
{
    super.onResume();
    if(ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
        !=PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BODY_SENSORS},
    1);
}else{startTemperatureAmbientSensoring();}

}
private void startTemperatureAmbientSensoring()
{
    SensorManager.registerListener(this, temperatureSensor,SensorManager.SENSOR_DELAY_NORMAL);
}
    private void stoptTemperatureAmbientSensoring()
    {
        SensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
    txtTemperature.setText("Temperaturiña: "+ event.values[0]);
    }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
