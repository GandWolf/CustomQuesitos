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

        Random rnd = new Random();

        main = (RelativeLayout) findViewById(R.id.activity_main);
        queso = (DiagramaQueso) findViewById(R.id.diagrama);
//        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
//        float [] data = {rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75),rnd.nextInt(75)};
//        int [] color = {0xFF110000,0xFF330000,0xFF550000,0xFF770000,0xFF990000,0xFFbb0000,0xFFdd0000};
//        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG", "HHH", "III", "JJJ"};
//        String [] nombre = {"AAA", "BBB", "CCC", "DDD", "EEE"};
//        float [] data = {1,5,3};

        float [] data = {50, 50, 50, 50, 50, 50, 50, 50};
        String [] nombre = {"000","111", "222", "333", "444","555","666","777"};
        queso.setArrayDatos(data, nombre);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//
//        Bitmap drawable = getBitmapFromView(main);
//        int pixel = drawable.getPixel(x,y);
//
//        int red = Color.red(pixel);
//        int green = Color.green(pixel);
//        int blue = Color.blue(pixel);
//
//
//
//        Toast.makeText(getApplicationContext(),"Colo R= "+red+" G="+green+" B="+blue, Toast.LENGTH_SHORT).show();
//
//        return super.onTouchEvent(event);
//    }
//
//    public static Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable =view.getBackground();
//        if (bgDrawable!=null)
//            bgDrawable.draw(canvas);
//        else
//            canvas.drawColor(Color.WHITE);
//        view.draw(canvas);
//        return returnedBitmap;
//    }
}
