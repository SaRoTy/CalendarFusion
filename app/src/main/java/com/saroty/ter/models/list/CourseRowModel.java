package com.saroty.ter.models.list;

import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseRowModel {

    private String mNom;
    private LocalTimeInterval mInter;

    public CourseRowModel(String nom, LocalTimeInterval inter){
        this.mNom = nom;
        this.mInter = inter;
    }

    public String getNom(){return this.mNom;}

    public LocalTimeInterval getInter(){return this.mInter;}
}
