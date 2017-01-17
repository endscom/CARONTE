package com.guma.desarrollo.core;
import java.util.UUID;

/**
 * Created by luis.perez on 17/01/2017.
 */

public class NumArticuloPorCliente {
    private String mID;
    private String mCODCLIETE;
    private String mNOMBRE;
    private String mARTICULOS;
    private int mImage;

    public NumArticuloPorCliente(String codcliente, String nombre, String articulos, int Image){
        mID = UUID.randomUUID().toString();
        mCODCLIETE = codcliente;
        mNOMBRE = nombre;
        mARTICULOS = articulos;
        mImage = Image;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmCODCLIETE() {
        return mCODCLIETE;
    }

    public void setmCODCLIETE(String mCODCLIETE) {
        this.mCODCLIETE = mCODCLIETE;
    }

    public String getmARTICULOS() {
        return mARTICULOS;
    }

    public void setmARTICULOS(String mARTICULOS) {
        this.mARTICULOS = mARTICULOS;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmNOMBRE() {
        return mNOMBRE;
    }

    public void setmNOMBRE(String mNOMBRE) {
        this.mNOMBRE = mNOMBRE;
    }
}
