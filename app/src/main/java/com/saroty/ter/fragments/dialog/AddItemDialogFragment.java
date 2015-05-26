package com.saroty.ter.fragments.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.saroty.ter.R;

import java.util.Calendar;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 19/05/2015.
 */
public class AddItemDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener{
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mDurationEditText;
    private EditText mHourEditText;
    private EditText mDateEditText;
    private int mHour;
    private int mMinute;
    private int mYear;
    private int mMonth;
    private int mDay;
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

        mTitleEditText = (EditText)view.findViewById(R.id.item_name);

        mLocationEditText = (EditText)view.findViewById(R.id.item_location);

        mDurationEditText = (EditText)view.findViewById(R.id.item_duration);

        mHourEditText = (EditText)view.findViewById(R.id.item_hour);

        mDateEditText = (EditText)view.findViewById(R.id.item_date);

        Button okButton = (Button)view.findViewById(R.id.ok_button);
        Button cancelButton = (Button)view.findViewById(R.id.cancel_button);

        mDateEditText.setFocusable(false);

        mHourEditText.setFocusable(false);


        if((b = getArguments()) != null){
            Calendar time;
            DateTime datetime;
            if(b.containsKey("time")){
                time = (Calendar)b.get("time");
                mMinute = time.get(Calendar.MINUTE);
                mHour = time.get(Calendar.HOUR_OF_DAY);

                mYear = time.get(Calendar.YEAR);
                mMonth = time.get(Calendar.MONTH);
                mDay = time.get(Calendar.DAY_OF_MONTH);
            }

            if(b.containsKey("datetime")) {
                datetime = (DateTime) b.get("datetime");

                mYear = datetime.getYear();
                mMonth = datetime.getMonth();
                mDay = datetime.getDay();
            }
        }else{
            mYear = myCalendar.get(Calendar.YEAR);
            mMonth = myCalendar.get(Calendar.MONTH);
            mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        }

        mHourEditText.setText(String.format("%02d:%02d", mHour,mMinute));
        mDateEditText.setText(String.format("%d/%02d/%02d",mYear,mMonth,mDay ));

        mHourEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (v == mHourEditText && event.getAction() == MotionEvent.ACTION_UP)
                    showTimeDialog();
                return false;


            }
        });

        mDateEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == mDateEditText && event.getAction() == MotionEvent.ACTION_UP)
                    showDateDialog();
                return false;
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*int duration = Integer.parseInt(mDurationEditText.getText().toString());
                Log.v("debug romain",mTitleEditText.getText().toString()+"");
                Log.v("debug romain", duration+"");*/

                okClickHandler();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddItemDialogListener)getTargetFragment()).cancel();
            }
        });

        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        mHourEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDateEditText.setText(String.format("%d/%02d/%02d", year, monthOfYear, dayOfMonth));
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
    }

    public void okClickHandler(){
        if(mTitleEditText.getText().toString().equals("")){
            mTitleEditText.requestFocus();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Title empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(mDurationEditText.getText().toString().equals("")){
            mDurationEditText.requestFocus();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Duration empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(mLocationEditText.getText().toString().equals("")){
            mLocationEditText.requestFocus();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Location empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(mHourEditText.getText().toString().equals("")){
            mHourEditText.requestFocus();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Hour empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(mDateEditText.getText().toString().equals("")){
            mDateEditText.requestFocus();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Date empty",
                    Toast.LENGTH_LONG).show();
            return;
        }

        ((AddItemDialogListener)getTargetFragment()).
                validate(mTitleEditText.getText().toString(),
                        mLocationEditText.getText().toString(),
                        Integer.parseInt(mDurationEditText.getText().toString()),
                        mHour,
                        mMinute,
                        DateTime.forDateOnly(mYear, mMonth, mDay));
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
        public void validate(String title, String location, int duration, int hour,
                             int minute, DateTime date);

        public void cancel();
    }

}
