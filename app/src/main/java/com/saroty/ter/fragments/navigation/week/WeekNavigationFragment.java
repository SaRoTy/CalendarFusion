package com.saroty.ter.fragments.navigation.week;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.dialog.AddItemDialogFragment;
import com.saroty.ter.fragments.dialog.ItemMenuDialog;
import com.saroty.ter.fragments.navigation.NavigationFragment;
import com.saroty.ter.fragments.navigation.courses.DetailCourseFragment;
import com.saroty.ter.utils.ScheduleToEventsUtils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 09/05/2015.
 */
public class WeekNavigationFragment extends NavigationFragment implements WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener, AddItemDialogFragment.AddItemDialogListener,
        ItemMenuDialog.ItemMenuActionListener
{
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_WEEK_VIEW;
    private final int LENGTH_SHORT = 1;
    private int mDayNameLength = LENGTH_SHORT;
    private  AddItemDialogFragment mAddDialog;
    private final int LENGTH_LONG = 2;
    private WeekView mWeekView;
    private Bundle mBundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_week_navigation, container, false);
        mBundle = new Bundle();
        mWeekView = (WeekView) rootView.findViewById(R.id.weekView);

        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        mWeekView.goToHour(6);

        DateTime date = DateTime.now(TimeZone.getDefault());
        date = date.minusDays(date.getWeekDay()-2);

        Log.v("debug romain",date.toString());
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(date.getMilliseconds(TimeZone.getDefault()));

        mWeekView.goToDate(calendar);

        mWeekView.setEmptyViewClickListener(new WeekView.EmptyViewClickListener() {
            @Override
            public void onEmptyViewClicked(Calendar time) {
                showAddItemDialog(time);
            }
        });

        //TODO : peut etre passer �a en anglais � voir
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter()
        {
            @Override
            public String interpretDate(Calendar date)
            {
                SimpleDateFormat sdf;
                sdf = mDayNameLength == LENGTH_SHORT ? new SimpleDateFormat("EEE") : new SimpleDateFormat("EEEE");
                try
                {
                    String dayName = sdf.format(date.getTime()).toUpperCase();
                    return String.format("%s %d/%02d", dayName, date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH));
                } catch (Exception e)
                {
                    e.printStackTrace();
                    return "";
                }
            }

            @Override
            public String interpretTime(int hour)
            {
                return String.format("%02d h", hour);
            }
        });


        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public String getNavigationTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_week);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_week);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect)
    {
        DetailCourseFragment fragment = ((ScheduleToEventsUtils) event).goToDetail();
        ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect)
    {
        mBundle.clear();
        mBundle.putSerializable("time", event.getStartTime());

        Dialog dialogMenu = ItemMenuDialog.newInstance(getActivity(),this);
        dialogMenu.show();

    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth)
    {
        List<WeekViewEvent> events;

        events = ScheduleToEventsUtils.getEvents(newMonth, newYear);

        return events;
    }

    private void showAddItemDialog(Calendar time){
        mAddDialog = new AddItemDialogFragment().newInstance(this);
        mBundle.clear();
        mBundle.putSerializable("time", time);
        mAddDialog.setArguments(mBundle);
        mAddDialog.show(getFragmentManager(), "Additem");
    }

    @Override
    public void validate(String title, String location, int duration, int hour,
                         int minute, DateTime date) {
        mBundle.clear();
        mAddDialog.dismiss();
    }

    @Override
    public void cancel() {
        mBundle.clear();
        mAddDialog.dismiss();
    }

    @Override
    public void add() {
        mAddDialog = new AddItemDialogFragment().newInstance(this);
        mAddDialog.setArguments(mBundle);
        mAddDialog.show(getFragmentManager(), "Additem");
    }

    @Override
    public void delete() {

    }
}
