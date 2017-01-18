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

    private Paint piePaint;
    private RectF rectF;
    private float[] data;

    int top, left, endBottom, endRight;

    public DiagramaQueso(Context context, AttributeSet attrs){
        super(context,attrs);

        piePaint = new Paint();
        piePaint.setAntiAlias(true);
        piePaint.setDither(true);
        piePaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Determine horizontal and vertical padding
        int paddingX = getPaddingLeft() + getPaddingRight();
        int paddingY = getPaddingBottom() + getPaddingTop();

        int minW, minH, w, h;
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                // Try for a height based on our minimum including vertical padding
                minH = getSuggestedMinimumHeight() + paddingY;
                h = MeasureSpec.getSize(heightMeasureSpec);

                // Set the width according to the height as our control should be
                // square, again compensating for padding
                minW = MeasureSpec.getSize(h) - paddingY + paddingX;
                w = resolveSize(minW, widthMeasureSpec);
                break;
            default:
                // Try for a width based on our minimum including horizontal padding
                minW = getSuggestedMinimumWidth() + paddingX;
                w = resolveSize(minW, widthMeasureSpec);

                // Set the height according to the width as our control should be
                // square, again compensating for padding
                minH = MeasureSpec.getSize(w) - paddingX + paddingY;
                h = resolveSize(minH, heightMeasureSpec);
                break;
        }

        setMeasuredDimension(w, h);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
        left = getPaddingLeft();
        top = getPaddingTop();
        int diameter = Math.min(
                w - left - getPaddingRight(),
                h - top - getPaddingBottom());
        endRight = left + diameter;
        endBottom= top + diameter;
    }

    private float[] pieSegment(){

        float[] segValues = new float[this.data.length];
        float Total = getTotal();

        for (int i = 0; i < this.data.length; i++){

            segValues[i] = (this.data[i]/Total) * 360;
        }

        return segValues;
    }


    private float getTotal(){

        float total = 0;

        for (float val : this.data){
            total +=val;
        }

        return total;
    }

    @Override
    protected void onDraw(Canvas canvas){

        if (data != null){


            rectF = new RectF(left, top, endRight, endBottom);

            float[] segment = pieSegment();

            float segStartPoint = 0;

            for (int i = 0; i < segment.length; i++){

                Random rnd = new Random();
                int color = Color.argb(255, (int)segment[i], rnd.nextInt(256), rnd.nextInt(256));

                piePaint.setColor(color);
                canvas.drawArc(rectF, segStartPoint, segment[i], true, piePaint);
                segStartPoint += segment[i];
            }
        }
    }

    public void setData(float[] data){

        this.data = data;
        invalidate();
    }
}//Fin class
