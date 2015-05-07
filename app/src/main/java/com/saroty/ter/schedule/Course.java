package com.saroty.ter.schedule;


import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Arthur on 09/03/2015.
 */
public class Course implements Serializable
{
    private static final long serialVersionUID = 5334276957940324970L;
    private String title;
    private String category;
    private String description;
    private String room;
    private int color = Color.rgb(255, 0, 0);

    public Course(String title)
    {
        this.title = title;
    }

    public boolean hasCategory()
    {
        return !category.equals("");
    }

    public boolean hasDescription()
    {
        return !description.equals("");
    }

    public boolean hasRoom()
    {
        return !room.equals("");
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

    public String getRoom()
    {
        return room;
    }

    public void setRoom(String room)
    {
        this.room = room;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
