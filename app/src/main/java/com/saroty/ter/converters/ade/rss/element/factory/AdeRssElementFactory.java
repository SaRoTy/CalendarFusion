package com.saroty.ter.converters.ade.rss.element.factory;

import android.util.Log;

import com.saroty.ter.converters.ade.rss.element.AdeRssElement;
import com.saroty.ter.converters.ade.rss.element.AdeRssItemElement;

/**
 * Created by Arthur on 04/05/2015.
 */
public class AdeRssElementFactory
{
    public AdeRssElement make(String tag)
    {
        switch (tag)
        {
            case "item":
                return new AdeRssItemElement();

            default:
                Log.d("AdeRssElementFactory", "Unknown element : " + tag);
                return new AdeRssElement();
        }
    }
}
