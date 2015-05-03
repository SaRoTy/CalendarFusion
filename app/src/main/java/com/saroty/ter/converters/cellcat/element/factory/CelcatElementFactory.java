package com.saroty.ter.converters.cellcat.element.factory;

import android.util.Log;

import com.saroty.ter.converters.cellcat.element.CelcatElement;
import com.saroty.ter.converters.cellcat.element.CelcatEventElement;
import com.saroty.ter.converters.cellcat.element.CelcatOptionElement;
import com.saroty.ter.converters.cellcat.element.CelcatSpanElement;

import java.util.List;

/**
 * Created by Arthur on 30/04/2015.
 */
public class CelcatElementFactory
{
    public CelcatElement make(String tag, List<CelcatSpanElement> spanList)
    {
        switch (tag)
        {
            case "option":
                return new CelcatOptionElement();
            case "span":
                return new CelcatSpanElement();
            case "event":
                return new CelcatEventElement(spanList);
            default:
                Log.d("CellcatElementFactory", "Unknown element : " + tag);
                return new CelcatElement();
        }
    }
}
