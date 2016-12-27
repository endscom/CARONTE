package com.guma.desarrollo.core;

import java.util.UUID;

/**
 * Created by maryan.espinoza on 27/12/2016.
 */
public class Cliente {
    private String mId;
    private String mName;
    private String mTitle;
    private String mCompany;
    private int mImage;

    public Cliente(String name, String title, String company, int image) {
        mId = UUID.randomUUID().toString();
        mName = name;
        mTitle = title;
        mCompany = company;
        mImage = image;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "ID='" + mId + '\'' +
                ", Compañía='" + mCompany + '\'' +
                ", Nombre='" + mName + '\'' +
                ", Cargo='" + mTitle + '\'' +
                '}';
    }
}