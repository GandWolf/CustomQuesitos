package com.example.gand.customquesitos;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

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
//        quesoPaint.setShadowLayer(20,20,20,0xFF000000);

        circuloPaint = new Paint();
        circuloPaint.setStyle(Paint.Style.STROKE);
        circuloPaint.setColor(Color.argb(255, 0, 0, 0));
        grosor = 10;
        circuloPaint.setStrokeWidth(grosor);
        distancia = 40;

        textPaint = new Paint();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
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

            arrayColor = generarColor(gamacolor);
            for (int i = 0; i < segment.length; i++) {
                quesoPaint.setColor(arrayColor[i]);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, quesoPaint);
                segStartPoint += segment[i];
            }

            canvas.drawCircle(left+(diametro/2),(top + diametro/2),(diametro/2),circuloPaint);
            dibujarEtiquetas(canvas, (left+diametro/2), (top+diametro/2), diametro/2, distancia, textPaint, segment);
        }
    }

    private void dibujarEtiquetas(Canvas canvas, float centroX, float centroY, float radio,
                                  float distancia, Paint p, float[] segment) {
        p.setTextSize(50);
        p.setColor(0xff000000);
        float position=0;
        for (int i = 0; i < segment.length; i++) {
            float theta=segment[i]/2;

//            float theta = (float) (2*Math.PI*i/segment[i]- Math.PI/2);
//            if (i == 0){
//                theta = segment[i]/2;
//            }else {
//                theta = segment[i-1]+segment[i]/2;
//            }
            float rho = radio + distancia;
            float x = (float) (rho * Math.cos(position+theta));
            float y = (float) (rho * Math.sin(position+theta));
            position+=segment[i];
            if(i == 0) {
                p.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(arrayNombre[i], x + centroX, y - distancia + centroY, p);
            } else if(segment[i] <= 90 || segment[i] >= 270) {
                p.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(arrayNombre[i], (float) ((rho+distancia) * Math.cos(theta)) + centroX,
                        (float) ((rho+distancia) * Math.sin(theta)) + centroY, p);
            } else {
                p.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(arrayNombre[i], (float) ((rho+distancia) * Math.cos(theta)) + centroX,
                        (float) ((rho+distancia) * Math.sin(theta)) + centroY, p);
            }
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
            default: color = r.getIntArray(R.array.gamaAzul);
        }
        return color;
    }

    public void setArrayDatos(float[] arrayDatos, String[] nombre){

        this.arrayDatos = arrayDatos;
        this.arrayNombre = nombre;
        invalidate();
    }
}//Fin class
