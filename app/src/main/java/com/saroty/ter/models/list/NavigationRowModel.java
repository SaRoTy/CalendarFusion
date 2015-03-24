package com.saroty.ter.models.list;

import android.app.Fragment;

/**
 * Created by Arthur on 21/03/2015.
 */
public class NavigationRowModel<F extends Fragment>
{
    private String mTitle;

    public NavigationRowModel(String title)
    {
        mTitle = title;
    }

    public String getTitle()
    {
        return mTitle;
    }
}
