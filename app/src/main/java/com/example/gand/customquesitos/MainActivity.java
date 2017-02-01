package com.example.gand.customquesitos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    DiagramaQueso queso;
    RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Random rnd = new Random();
//
//        main = (RelativeLayout) findViewById(R.id.activity_main);
//        queso = (DiagramaQueso) findViewById(R.id.diagrama);
//        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
////        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
////        int [] color = {0xFF110000,0xFF330000,0xFF550000,0xFF770000,0xFF990000,0xFFbb0000,0xFFdd0000};
//        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ"};
////        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE"};
////        float [] data = {1,5,3};
//
////        float [] data = {50, 50, 50, 50, 50, 50, 50, 50};
////        String [] nombre = {"000","111", "222", "333", "444","555","666","777"};
//        queso.setArrayDatos(data, nombre);

//        Ejemplo ej = (Ejemplo) findViewById(R.id.ejemplo);
//        ej.pintar();
    }
}
