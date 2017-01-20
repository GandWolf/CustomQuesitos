package com.example.gand.customquesitos;

/**
 * Created by Gand on 20/01/17.
 */

public class Queso {
    private String nombre;
    private Float cantidad;
    private int color;

    public Queso(String nombre, Float cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {       return nombre;    }
    public Float getCantidad() {       return cantidad;    }
    public int getColor() {       return color;    }

    public void setColor(int color) {        this.color = color;    }
}
