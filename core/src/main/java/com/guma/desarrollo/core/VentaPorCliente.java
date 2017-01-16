package com.guma.desarrollo.core;

import java.util.UUID;

/**
 * Created by luis.perez on 13/01/2017.
 */

public class VentaPorCliente {
    private String mID;
    private String mCODIGO;
    private String mNOMBRE;
    private String mVTAxCLIENTE;
    private int mImage;

    public VentaPorCliente(String codigo, String nombre, String vtaxcliente, int Image)
    {
        mID = UUID.randomUUID().toString();
        mCODIGO = codigo;
        mNOMBRE = nombre;
        mVTAxCLIENTE = vtaxcliente;
        mImage = Image;
    }

    public String getID() {
        return mID;
    }

    public void setID(String mID) {
        this.mID = mID;
    }

    public String getCODIGO() {
        return mCODIGO;
    }

    public void setCODIGO(String mCODIGO) {
        this.mCODIGO = mCODIGO;
    }

    public String getNOMBRE() {
        return mNOMBRE;
    }

    public void setNOMBRE(String mNOMBRE) {
        this.mNOMBRE = mNOMBRE;
    }

    public String getVTAxCLIENTE() {
        return mVTAxCLIENTE;
    }

    public void setVTAxCLIENTE(String mVTAxCLIENTE) {
        this.mVTAxCLIENTE = mVTAxCLIENTE;
    }
    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString(){
        return "Lead{" +
                "ID='" + mID + '\'' +
                ", CODIGO='" + mCODIGO + '\'' +
                ", NOMBRE='" + mNOMBRE + '\'' +
                ", VTAxCLIENTE='" + '\'';
    }
}
