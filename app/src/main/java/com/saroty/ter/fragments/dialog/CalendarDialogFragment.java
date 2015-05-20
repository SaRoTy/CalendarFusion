package com.saroty.ter.fragments.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.saroty.ter.R;
import com.saroty.ter.fragments.navigation.home.HomeNavigationFragment;

import java.util.Calendar;

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
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(mCalendar.getDate());

        mBundle = new Bundle();

        mBundle.putInt("year", cal.get(Calendar.YEAR));
        mBundle.putInt("month", cal.get(Calendar.MONTH) + 1);
        mBundle.putInt("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

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
