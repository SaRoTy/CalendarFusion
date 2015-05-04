package com.saroty.ter.converters.ade.rss.element.enums;

/**
 * Created by Arthur on 04/05/2015.
 */
public enum AdeRssElementEnum
{
    ITEM("item");

    private String mTag;

    AdeRssElementEnum(String tag)
    {
        mTag = tag;
    }

    public String getTag()
    {
        return mTag;
    }
}
