package com.example.gand.customquesitos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiagramaQueso pie = (DiagramaQueso) findViewById(R.id.diagrama);
        float[] data = {450, 630, 300, 200, 400};
        pie.setData(data);
    }
}
