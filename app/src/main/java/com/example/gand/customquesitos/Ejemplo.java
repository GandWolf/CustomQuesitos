package com.example.gand.customquesitos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Gand on 01/02/17.
 */

public class Ejemplo extends View {

    String texto;
    Paint brocha, b2;
    int altura, ancho, fondo, bajo;
    int diametro;

    public Ejemplo(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Ejemplo,
                0, 0);

        try {
            texto = a.getString(R.styleable.Ejemplo_texto);
            Log.e("ERROR", texto);
        } finally {
            a.recycle();
        }


        brocha= new Paint();
        brocha.setColor(getResources().getColor(R.color.colorPrimary));
//        brocha.setARGB(244,0,0,0);
        brocha.setTextSize(50);

        b2 = new Paint();
        b2.setColor(getResources().getColor(R.color.colorPrimaryDark));
        b2.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        altura = getPaddingTop();
        ancho = getPaddingLeft();
        fondo = Math.min(w, h) - getPaddingRight();
        bajo = Math.min(w,h) - getPaddingBottom();
        diametro = fondo-ancho;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(getResources().getColor(R.color.colorAccent));
//        canvas.drawText(texto,50,50, brocha );
        canvas.drawArc(altura,ancho,fondo,bajo,0,360,true,b2);
        b2.setColor(getResources().getColor(R.color.colorAccent));
        b2.setStyle(Paint.Style.STROKE);
        canvas.drawArc(altura + diametro/4, ancho+diametro/4, fondo -diametro/4, bajo - diametro/4
        ,0, 360, true, b2);

    }

    @Override
    public void invalidate() {
        super.invalidate();
    }
    void pintar(){
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        Toast.makeText(getContext(),"X= "+x+"Y="+y, Toast.LENGTH_SHORT).show();



        return super.onTouchEvent(event);
    }
}
