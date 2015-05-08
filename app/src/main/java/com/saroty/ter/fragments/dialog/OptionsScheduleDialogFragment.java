package com.saroty.ter.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.saroty.ter.R;

/**
 * Created by Arthur on 08/05/2015.
 */
public class OptionsScheduleDialogFragment extends DialogFragment
{
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_options_schedule_title)
                .setItems(R.array.dialog_options_schedule_menu, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case 0:
                                ;
                            case 1:
                                ;
                            case 2:
                                ;
                        }
                    }
                });

        return builder.create();
    }
}
