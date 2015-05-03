package com.saroty.ter.converters.cellcat.element.enums;

/**
 * Created by Arthur on 01/05/2015.
 */
public enum CelcatElementEnum
{
    OPTION("option"),
    EVENT("event"),
    ITEM("item"),
    SPAN("span");

    private String mTag;

    CelcatElementEnum(String tag)
    {
        mTag = tag;
    }

    public String getTag()
    {
        return mTag;
    }
}
