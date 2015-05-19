package com.saroty.ter.fragments.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.navigation.DaysNavigationFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Romain on 18/05/2015.
 */
//TODO: pas de changement au niveau du titre après le choix de date
public class CalendarDialog extends DialogFragment {

    private CalendarView mCalendar;
    private Bundle b;
    private Button mOk;
    private Button mCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_dialog, container);

        mCalendar = (CalendarView) view.findViewById(R.id.calendar);

        mOk = (Button) view.findViewById(R.id.ok);
        mCancel = (Button) view.findViewById(R.id.cancel);

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok(v);
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });

        getDialog().setTitle("Calendar");

        return view;
    }

    public void ok(View v){
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(mCalendar.getDate());

        b = new Bundle();

        b.putInt("year", cal.get(Calendar.YEAR));
        b.putInt("month", cal.get(Calendar.MONTH)+1);
        b.putInt("dayOfMonth", cal.get(Calendar.DAY_OF_MONTH));

        Fragment f = new DaysNavigationFragment();
        f.setArguments(b);

        ((MainActivity) getActivity()).setCurrentFragment(f, false);
        getDialog().dismiss();
    }

    public void cancel(View v){
        getDialog().dismiss();
    }
}
