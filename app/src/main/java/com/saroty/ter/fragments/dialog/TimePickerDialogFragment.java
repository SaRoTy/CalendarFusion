package com.saroty.ter.fragments.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Romain on 20/05/2015.
 */
public class TimePickerDialogFragment extends DialogFragment
{

    public static TimePickerDialogFragment newInstance(TimePickerDialog.OnTimeSetListener listener)
    {
        TimePickerDialogFragment f = new TimePickerDialogFragment();
        f.setTargetFragment((Fragment)listener,1337);

        return f;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it

        return new TimePickerDialog(getActivity().getApplicationContext(),
                (TimePickerDialog.OnTimeSetListener)getTargetFragment(),
                hour,minute,DateFormat.is24HourFormat(getActivity()));

    }

}
