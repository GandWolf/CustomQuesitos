package com.example.gand.customquesitos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();

        DiagramaQueso pie = (DiagramaQueso) findViewById(R.id.diagrama);
//        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
//        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
//        int [] color = {0xFF110000,0xFF330000,0xFF550000,0xFF770000,0xFF990000,0xFFbb0000,0xFFdd0000};
//        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ"};
//        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE"};
//        float [] data = {1,5,3};

        float [] data = {50, 50, 50, 50, 50, 50, 50, 50};
        String [] nombre = {"000","111", "222", "333", "444","555","666","777"};
        pie.setArrayDatos(data, nombre);
    }
}
