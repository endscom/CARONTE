package com.guma.desarrollo.core;

import java.util.UUID;

/**
 * Created by luis.perez on 16/01/2017.
 */

public class VentaPorArticulo {
    private String mID;
    private String mARTICULO;
    private String mDESCRIPCION;
    private String mVTAxARTICULO;
    private int mImage;

    public VentaPorArticulo(String articulo, String descripcion, String vtaxarticulo, int Image){
        mID = UUID.randomUUID().toString();
        mARTICULO = articulo;
        mDESCRIPCION = descripcion;
        mVTAxARTICULO = vtaxarticulo;
        mImage = Image;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmARTICULO() {
        return mARTICULO;
    }

    public void setmARTICULO(String mARTICULO) {
        this.mARTICULO = mARTICULO;
    }

    public String getmDESCRIPCION() {
        return mDESCRIPCION;
    }

    public void setmDESCRIPCION(String mDESCRIPCION) {
        this.mDESCRIPCION = mDESCRIPCION;
    }

    public String getmVTAxARTICULO() {
        return mVTAxARTICULO;
    }

    public void setmVTAxARTICULO(String mVTAxARTICULO) {
        this.mVTAxARTICULO = mVTAxARTICULO;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString(){
        return "VentaPorArticulo{" +
                "ID='" + mID + '\'' +
                ", ARTICULO='" + mARTICULO + '\'' +
                ", DESCRIPCION='" + mDESCRIPCION + '\'' +
                ", VTAxCLIENTE='" + '\'';
    }

}
