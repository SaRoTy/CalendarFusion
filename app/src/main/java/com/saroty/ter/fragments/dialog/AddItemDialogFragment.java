package com.saroty.ter.fragments.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.saroty.ter.R;
import com.saroty.ter.fragments.navigation.day.DaysNavigationFragment;

import java.util.Calendar;

/**
 * Created by Romain on 19/05/2015.
 */
public class AddItemDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{

    private EditText hour;
    private EditText date;
    private Calendar myCalendar;

    public static AddItemDialogFragment newInstance(AddItemDialogListener listener){
        AddItemDialogFragment f = new AddItemDialogFragment();

        f.setTargetFragment((Fragment)listener,1337);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_add_item, container);
        Bundle b;
        getDialog().setTitle("Add item");

        myCalendar = Calendar.getInstance();

        hour = (EditText)view.findViewById(R.id.item_hour);

        date = (EditText)view.findViewById(R.id.item_date);

        Button okButton = (Button)view.findViewById(R.id.ok_button);
        Button cancelButton = (Button)view.findViewById(R.id.cancel_button);

        date.setFocusable(false);

        hour.setFocusable(false);


        if((b = getArguments()) != null){
            Calendar time = (Calendar)b.get("time");
            hour.setText(String.format("%02d:%02d", time.get(Calendar.HOUR_OF_DAY),
                    time.get(Calendar.MINUTE)));

            date.setText(String.format("%d/%02d/%02d", time.get(Calendar.YEAR),
                    time.get(Calendar.MONTH),time.get(Calendar.DAY_OF_MONTH)));
        }

        hour.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (v == hour && event.getAction() == MotionEvent.ACTION_UP)
                    showTimeDialog();
                return false;


            }
        });

        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == date && event.getAction() == MotionEvent.ACTION_UP)
                    showDateDialog();
                return false;
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddItemDialogListener)getTargetFragment()).validate();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddItemDialogListener)getTargetFragment()).cancel();
            }
        });

        date.setText(myCalendar.get(Calendar.YEAR) + "/"
                + String.format("%02d/%02d",
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)));

        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour.setText(String.format("%02d:%02d", hourOfDay, minute));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //TODO stocker la date dans un date time
        date.setText(year + "/" + String.format("%02d/%02d", monthOfYear, dayOfMonth));
    }

    private void showTimeDialog(){

        new TimePickerDialog(getActivity(),this, myCalendar
                .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity())).show();
    }

    private void showDateDialog(){

        new DatePickerDialog(getActivity(),this,
                myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public interface AddItemDialogListener{
        public void validate();

        public void cancel();
    }

}
