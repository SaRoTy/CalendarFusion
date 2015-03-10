package com.saroty.ter.schedule;


import java.io.Serializable;

import javax.xml.datatype.Duration;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Course implements Serializable
{
    private static final long serialVersionUID = 5334276957940324970L;
    private String title;
    private String category;

    public Course()
    {
    }

    public Course(String title, Duration duration)
    {
        this.title = title;
    }

    public boolean hasCategory()
    {
        return !category.equals("");
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "[Course] (" + category + ") " + title;
    }
}
