package com.saroty.ter.models.list;

import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseRowModel {

    private String mNom;
    private String inter;
    private String fin;

    public CourseRowModel(String nom, String inter){
        mNom = nom;
        this.inter = inter;
    }

    public String getNom(){return mNom;}

    public String getInter(){return inter;}
}
