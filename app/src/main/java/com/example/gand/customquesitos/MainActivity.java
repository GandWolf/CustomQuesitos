package com.example.gand.customquesitos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiagramaQueso pie = (DiagramaQueso) findViewById(R.id.diagrama);
        float [] data = {450, 630, 300, 200, 400, 555, 50};
        int [] color = {0xFF110000,0xFF330000,0xFF550000,0xFF770000,0xFF990000,0xFFbb0000,0xFFdd0000};
        pie.setArrayDatos(data, color);
    }
}
