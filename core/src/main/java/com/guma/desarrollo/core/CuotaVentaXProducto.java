package com.guma.desarrollo.core;
import java.util.UUID;

public class CuotaVentaXProducto {
    private String mId;
    private String mCodVendedor;
    private String mCodProducto;
    private String mNombreProducto;
    private String mMeta;
    private int mImage;

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public CuotaVentaXProducto(String codvendedor, String codproducto, String nombreproducto, String meta, int image){
        mId = UUID.randomUUID().toString();
        mCodVendedor=codvendedor;
        mCodProducto=codproducto;
        mNombreProducto=nombreproducto;
        mMeta=meta;
        mImage=image;

    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getCodVendedor() {
        return mCodVendedor;
    }

    public void setCodVendedor(String mCodVendedor) {
        this.mCodVendedor = mCodVendedor;
    }

    public String getCodProducto() {
        return mCodProducto;
    }

    public void setCodProducto(String mCodProducto) {
        this.mCodProducto = mCodProducto;
    }

    public String getNombreProducto() {
        return mNombreProducto;
    }

    public void setNombreProducto(String mNombreProducto) {
        this.mNombreProducto = mNombreProducto;
    }

    public String getMeta() {
        return mMeta;
    }

    public void setMeta(String mMeta) {
        this.mMeta = mMeta;
    }

    @Override
    public String toString(){
        return "Lead{" +
                "ID='" + mId + '\'' +
                ", CodVendedor='" + mCodVendedor+ '\'' +
                ", CodProducto='" + mCodProducto + '\'' +
                ", NombreProducto='" + mNombreProducto + '\'' +
                ", Meta='" + mMeta + '\'' +
                '}';
    }
}
