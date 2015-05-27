package com.saroty.ter.fragments.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.saroty.ter.R;
import com.saroty.ter.fragments.navigation.home.HomeNavigationFragment;

import java.util.Calendar;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 18/05/2015.
 */
public class CalendarDialogFragment extends DialogFragment {

    private CalendarView mCalendar;
    private Bundle mBundle;
    private Button mOkBuntton;
    private Button mCancelButton;

    public static CalendarDialogFragment newInstance(CalendarDialogListener listener){
        CalendarDialogFragment f = new CalendarDialogFragment();
        f.setTargetFragment((Fragment) listener, 1337);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_calendar, container);

        mCalendar = (CalendarView) view.findViewById(R.id.calendar);

        mOkBuntton = (Button) view.findViewById(R.id.ok);
        mCancelButton = (Button) view.findViewById(R.id.cancel);

        mOkBuntton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButtonClicked(v);
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelButtonClicked(v);
            }
        });

        getDialog().setTitle("Calendar");

        return view;
    }

    private void okButtonClicked(View v)
    {
        DateTime date = DateTime.now(TimeZone.getDefault());
        DateTime comp = DateTime.forInstant(mCalendar.getDate(),TimeZone.getDefault());

        if(  comp.getMilliseconds(TimeZone.getDefault()) <=
                date.minusDays(1).getMilliseconds(TimeZone.getDefault())  ){
            Toast.makeText(getActivity().getApplicationContext(),
                    "Error can't choose a day before today",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mBundle = new Bundle();

        mBundle.putInt("year", comp.getYear());
        mBundle.putInt("month", comp.getMonth());
        mBundle.putInt("dayOfMonth",comp.getDay());

        ((HomeNavigationFragment)getTargetFragment()).onButtonOkClick();

    }

    public Bundle getBundle(){
        return mBundle;
    }

    private void cancelButtonClicked(View v){
        getDialog().dismiss();
    }

    public interface CalendarDialogListener{
        public void onButtonOkClick();
    }
}
