package com.saroty.ter.models.list;

/**
 * Created by Romain on 27/05/2015.
 */
public class ManagerCourseRowModel {

    private String mTitle;
    private int mColor;

    public ManagerCourseRowModel(String title,int color){
        mTitle = title;
        mColor = color;
    }

    public String getTitle(){return mTitle;}

    public int getColor(){return mColor;}

    @Override
    public boolean equals(Object o){
        if(!(o instanceof ManagerCourseRowModel))
            return false;

        return((ManagerCourseRowModel)o).getTitle().equals(mTitle);
    }

    @Override
    public int hashCode(){
        return 13+mTitle.hashCode();
    }
}
