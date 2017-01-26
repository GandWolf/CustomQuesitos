package com.example.gand.customquesitos;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gand on 17/01/17.
 */

public class DiagramaQueso extends View {

    private int gamacolor;

    private final Paint quesoPaint, circuloPaint, textPaint;
    private RectF rectF;
    private float arrayDatos[];
    private String arrayNombre[];
    private int[] arrayColor;
    Resources r = getResources();

    int top, left, endBottom, endRight, diametro, grosor, distancia;

    public DiagramaQueso(Context context, AttributeSet attrs){
        super(context,attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DiagramaQueso,
                0, 0);

        try {
            gamacolor = a.getInteger(R.styleable.DiagramaQueso_gamacolores, 0);
        } finally {
            a.recycle();
        }

        quesoPaint = new Paint();
        quesoPaint.setAntiAlias(true);
        quesoPaint.setDither(true);
        quesoPaint.setStyle(Paint.Style.FILL);

        circuloPaint = new Paint();
        circuloPaint.setAntiAlias(true);
        circuloPaint.setDither(true);
        circuloPaint.setStyle(Paint.Style.STROKE);
        circuloPaint.setColor(Color.argb(255, 5, 5, 5));
        grosor = 6;
        circuloPaint.setStrokeWidth(grosor);
        distancia = 40;

        textPaint = new Paint();
        textPaint.setTextSize(50);
        textPaint.setColor(0xff000000);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        left = getPaddingLeft() + grosor/2 + distancia*3;
        top = getPaddingTop() + grosor/2 + distancia*3;
        diametro = Math.min(
                w - left - getPaddingRight() - grosor/2 - distancia*3,
                h - top - getPaddingBottom() - grosor/2 - distancia*3);
        endRight = left + diametro;
        endBottom= top + diametro;
    }

    private float[] segmentos(){
        float[] segDatos = new float[this.arrayDatos.length];
        float Total = getTotal();

        for (int i = 0; i < this.arrayDatos.length; i++){
            segDatos[i] = (this.arrayDatos[i]/Total) * 360;
        }

        return segDatos;
    }


    private float getTotal(){
        float total = 0;

        for (float datos : this.arrayDatos){
            total +=datos;
        }
        return total;
    }

    @Override
    protected void onDraw(Canvas canvas){

        if (arrayDatos != null){

            rectF = new RectF(left, top, endRight, endBottom);

            float[] segment = segmentos();
            float segStartPoint = 0;
            float centroX = left + diametro / 2;
            float centroY = top + diametro / 2;

            arrayColor = generarColor(gamacolor);
            for (int i = 0; i < segment.length; i++) {
                quesoPaint.setColor(arrayColor[i]);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, quesoPaint);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, circuloPaint);
                canvas.drawText(arrayNombre[i],centroX + (float)((diametro/1.8)*Math.cos(segStartPoint+segment[i]/2)),
                        centroY + (float)((diametro/1.8)*Math.sin(segStartPoint+segment[i]/2)), textPaint);

                segStartPoint += segment[i];
            }
//            dibujarEtiquetas(canvas, centroX, centroY, segment);
        }
    }

    private void dibujarEtiquetas(Canvas canvas, float centroX, float centroY, float[] segment) {
        textPaint.setTextSize(50);
        textPaint.setColor(0xff000000);

        float rho = diametro/2 + distancia*1.5f;

        Log.e(" Radio" , ""+rho);
        float sum=0;
        float position=0;
        for (int i = 0; i < segment.length; i++) {
            float theta=segment[i]/2;

            Log.d("Segment:"+i, ""+segment[i]);
            Log.d("Position:"+i, ""+position);
            Log.d("Sum:"+i, ""+sum);
            Log.d("theta:"+i , ""+theta);


            Log.e(" Angulo"+i , ""+(position+theta));

            float x = (float) (rho * Math.cos(position+theta));
            float y = (float) (rho * Math.sin(position+theta));


            Log.i("X:"+i, ""+x);
            Log.e("cos"+i, "Math.cos("+(position+theta)+") = "+Math.cos(position+theta));
            Log.i("y:"+i, ""+y);
            Log.e("cos"+i, "Math.sin("+(position+theta)+") = "+Math.sin(position+theta));

            position+=segment[i];
            sum += segment[i];
            canvas.drawText(arrayNombre[i], centroX + (float)(rho * Math.cos(position)), centroY + (float)(rho * Math.sin(position)), textPaint);

//            if (sum<=90){
//                textPaint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText(arrayNombre[i], centroX - x, centroY - y, textPaint);
//            }
//            if (sum>90 && sum<=180){
//                canvas.dra
//                textPaint.setTextAlign(Paint.Align.RIGHT);
//                canvas.drawText(arrayNombre[i], centroX - x - rho, centroY + y, textPaint);
//            }
//            if (sum>180 && sum<=270){
//                textPaint.setTextAlign(Paint.Align.RIGHT);
//                canvas.drawText(arrayNombre[i], centroX + x - rho, centroY + y - rho, textPaint);
//            }
//            if (sum>270 && sum<=360){
//                textPaint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText(arrayNombre[i], centroX - x, centroY + y - rho, textPaint);
//            }

//            if(i == 0) {
//                p.setTextAlign(Paint.Align.CENTER);
//                canvas.drawText(arrayNombre[i], x + centroX, y - distancia + centroY, p);
//            } else if(segment[i] <= 90 || segment[i] >= 270) {
//                p.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText(arrayNombre[i], (float) ((rho+distancia) * Math.cos(theta)) + centroX,
//                        (float) ((rho+distancia) * Math.sin(theta)) + centroY, p);
//            } else {
//                p.setTextAlign(Paint.Align.RIGHT);
//                canvas.drawText(arrayNombre[i], (float) ((rho+distancia) * Math.cos(theta)) + centroX,
//                        (float) ((rho+distancia) * Math.sin(theta)) + centroY, p);
//            }
        }
    }

    int[] generarColor(int c){
        int [] color=null;
        switch (c){
            case 1: color = r.getIntArray(R.array.gamaRojo);
                break;
            case 2: color = r.getIntArray(R.array.gamaVerde);
                break;
            case 3: color = r.getIntArray(R.array.gamaAzul);
                break;
            default: color = r.getIntArray(R.array.gamaVerde);
        }
        return color;
    }

    public void setArrayDatos(float[] arrayDatos, String[] nombre){

        this.arrayDatos = arrayDatos;
        this.arrayNombre = nombre;
        invalidate();
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//
//
//        Bitmap bitmap = ((BitmapDrawable)getBackground()).getBitmap();
//        int pixel = bitmap.getPixel(x,y);
//
//        Toast.makeText(getContext(),"Colo= "+pixel, Toast.LENGTH_LONG).show();
//
//        return super.onTouchEvent(event);
//    }
}//Fin class
