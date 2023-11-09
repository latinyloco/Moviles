package com.example.myapplication.DB;

public class Partida {

    private String fecha;
    private String nombre;
    private int pts;

    public Partida(String fecha, String nombre, int pts){
        this.fecha = fecha;
        this.nombre = nombre;
        this.pts = pts;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPts() {
        return pts;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
