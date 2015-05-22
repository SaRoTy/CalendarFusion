package com.saroty.ter.web.config.element.factory;

import android.util.Log;

import com.saroty.ter.web.config.element.ConfigElement;
import com.saroty.ter.web.config.element.ConfigGroupElement;

/**
 * Created by Arthur on 21/05/2015.
 */
public class ConfigElementFactory
{
    public ConfigElement make(String tag)
    {
        switch (tag)
        {
            case "group":
                return new ConfigGroupElement();
            default:
                Log.d("CellcatElementFactory", "Unknown element : " + tag);
                return new ConfigElement();
        }
    }
}
