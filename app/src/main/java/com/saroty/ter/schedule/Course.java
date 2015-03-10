package com.saroty.ter.schedule;


import javax.xml.datatype.Duration;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Course
{
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

    public void setTitle(String title)
    {
        this.title = title;
    }
}
