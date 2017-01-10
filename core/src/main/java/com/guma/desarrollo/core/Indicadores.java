package com.guma.desarrollo.core;

public class Indicadores {
    private int imagen;
    private String nombre;
    private String visitas;

    public Indicadores(int imagen, String nombre, String visitas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.visitas = visitas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVisitas() {
        return visitas;
    }

    public int getImagen() {
        return imagen;
    }
}
