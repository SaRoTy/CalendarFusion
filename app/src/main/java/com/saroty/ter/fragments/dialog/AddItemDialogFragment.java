package com.saroty.ter.fragments.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.saroty.ter.R;

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
        getDialog().setTitle("Add item");

        myCalendar = Calendar.getInstance();

        hour = (EditText)view.findViewById(R.id.item_hour);

        date = (EditText)view.findViewById(R.id.item_date);

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

        date.setText(myCalendar.get(Calendar.YEAR) + "/"
                +String.format("%02d/%02d",
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)));




        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(date.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.hideSoftInputFromWindow(hour.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour.setText(hourOfDay + ":" + String.format("%02d", minute));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        //TODO stocker la date dans un date time
        date.setText(year+"/"+String.format("%02d/%02d",monthOfYear,dayOfMonth));
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
    }
}
