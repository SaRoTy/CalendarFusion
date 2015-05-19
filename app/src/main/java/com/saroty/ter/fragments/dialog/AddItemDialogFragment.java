package com.saroty.ter.fragments.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

/**
 * Created by Romain on 19/05/2015.
 */
public class AddItemDialogFragment extends DialogFragment{

    public static AddItemDialogFragment newInstance(AddItemDialogListener listener){
        AddItemDialogFragment f = new AddItemDialogFragment();

        f.setTargetFragment((Fragment)listener,1337);


        return f;
    }

    public interface AddItemDialogListener{
        public void validate();
    }
}
