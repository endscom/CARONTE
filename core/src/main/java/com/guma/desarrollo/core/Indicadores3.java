package com.guma.desarrollo.core;

/**
 * Created by luis.perez on 20/01/2017.
 */

public class Indicadores3 {
    private int Imagen;
    private String Nombre;
    private String Promedio;
    private String Meta;
    private String Actual;
    private String Pendiente;

    public Indicadores3(int imagen, String nombre, String promedio, String meta,String actual, String pendiente)
    {
        this.Imagen = imagen;

        this.Promedio = promedio;
        this.Nombre = nombre;
        this.Meta = meta;
        this.Actual = actual;
        this.Pendiente = pendiente;
    }

    public int getImagen() {
        return Imagen;
    }

    public String getPromedio() {
        return Promedio;
    }
    public String getNombre(){
        return Nombre;
    }

    public String getMeta() {
        return Meta;
    }

    public String getActual() {
        return Actual;
    }

    public String getPendiente() {
        return Pendiente;
    }
}
