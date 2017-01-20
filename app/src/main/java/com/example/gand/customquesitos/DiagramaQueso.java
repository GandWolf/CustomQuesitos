package com.example.gand.customquesitos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by gand on 17/01/17.
 */

public class DiagramaQueso extends View {

    private Paint quesoPaint, circuloPaint;
    private RectF rectF;
    private float arrayDatos[];
    private static int arrayColor[];

    int top, left, endBottom, endRight, diametro, grosor;

    public DiagramaQueso(Context context, AttributeSet attrs){
        super(context,attrs);

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


    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // Determine horizontal and vertical padding
//        int paddingX = getPaddingLeft() + getPaddingRight() + grosor;
//        int paddingY = getPaddingBottom() + getPaddingTop() + grosor;
//
//        int minW, minH, w, h;
//        switch (MeasureSpec.getMode(heightMeasureSpec)) {
//            case MeasureSpec.EXACTLY:
//                // Try for a height based on our minimum including vertical padding
//                minH = getSuggestedMinimumHeight() + paddingY + grosor;
//                h = MeasureSpec.getSize(heightMeasureSpec);
//
//                // Set the width according to the height as our control should be
//                // square, again compensating for padding
//                minW = MeasureSpec.getSize(h) - paddingY + paddingX;
//                w = resolveSize(minW, widthMeasureSpec);
//                break;
//            default:
//                // Try for a width based on our minimum including horizontal padding
//                minW = getSuggestedMinimumWidth() + paddingX;
//                w = resolveSize(minW, widthMeasureSpec);
//
//                // Set the height according to the width as our control should be
//                // square, again compensating for padding
//                minH = MeasureSpec.getSize(w) - paddingX + paddingY;
//                h = resolveSize(minH, heightMeasureSpec);
//                break;
//        }
//
//        setMeasuredDimension(w, h);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
        left = getPaddingLeft() + grosor/2;
        top = getPaddingTop() + grosor/2;
        diametro = Math.min(
                w - left - getPaddingRight() - grosor/2,
                h - top - getPaddingBottom() - grosor/2);
        endRight = left + diametro;
        endBottom= top + diametro;
    }

    private float[] segmentos(){

        float[] segValues = new float[this.arrayDatos.length];
        float Total = getTotal();

        for (int i = 0; i < this.arrayDatos.length; i++){

            segValues[i] = (this.arrayDatos[i]/Total) * 360;
        }

        return segValues;
    }


    private float getTotal(){

        float total = 0;

        for (float val : this.arrayDatos){
            total +=val;
        }

        return total;
    }

    @Override
    protected void onDraw(Canvas canvas){

        if (arrayDatos != null){

            rectF = new RectF(left, top, endRight, endBottom);

            float[] segment = segmentos();
            float segStartPoint = 0;

//            if (arrayColor == null) {
//                arrayColor = new int[segment.length];
//                for (int i = 0; i < segment.length; i++) {
//                    Random rnd = new Random();
//                    int color;
//                    if ((int) segment[i] < 100) {
//                        color = Color.argb(255, (int) segment[i], (int) segment[i], rnd.nextInt(150) + 100);
//                        quesoPaint.setColor(color);
//                    } else {
//                        color = Color.argb(255, 255 - (int) segment[i], 255 - (int) segment[i], rnd.nextInt(150) + 100);
//                        quesoPaint.setColor(color);
//                    }
//                    arrayColor[i] = color;
//                    canvas.drawArc(rectF, segStartPoint, segment[i], true, quesoPaint);
//                    segStartPoint += segment[i];
//                }
//            }else {
//                for (int i = 0; i < segment.length; i++) {
//                    quesoPaint.setColor(arrayColor[i]);
//                    canvas.drawArc(rectF, segStartPoint, segment[i], true, quesoPaint);
//                    segStartPoint += segment[i];
//                }
//            }

            generarColor(2);
            for (int i = 0; i < segment.length; i++) {
                quesoPaint.setColor(arrayColor[i]);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, quesoPaint);
                segStartPoint += segment[i];
            }

            canvas.drawCircle(left+(diametro/2),(top + diametro/2),(diametro/2),circuloPaint);
        }
    }

    void generarColor(int c){
        int []c1=new int[arrayDatos.length];
        int []c2=new int[c1.length];
        int []c3=new int[c1.length];

        Random rnd = new Random();

        for (int i = 0; i < c1.length; i++){
            if (i%2==0){
                c1[i] = 255 - (i/c1.length*175);
            }else {
                c1[i] = 175 + ((c1.length-i)/c1.length*255);
            }

            c2[i] = rnd.nextInt(75);
            c3[i] = rnd.nextInt(75);
        }

        switch (c){
            case 1:
                for (int i = 0; i < c1.length; i++){
                arrayColor [i] = Color.argb(255, c1[i], c2[i], c3[i]);
                }
                break;
            case 2:
                for (int i = 0; i < c1.length; i++){
                arrayColor [i] = Color.argb(255, c2[i], c1[i], c3[i]);
                }
                break;
            case 3:
                for (int i = 0; i < c1.length; i++){
                arrayColor [i] = Color.argb(255, c2[i], c3[i], c1[i]);
                }
                break;
            default:
                for (int i = 0; i < c1.length; i++){
                arrayColor [i] = Color.argb(255, c1[i], c2[i], c3[i]);
                }
        }
    }

    public void setArrayDatos(float[] arrayDatos, int [] arrayColor){

        this.arrayDatos = arrayDatos;
        this.arrayColor = new int[arrayDatos.length];
//        this.arrayColor = arrayColor;
        invalidate();
    }
}//Fin class
