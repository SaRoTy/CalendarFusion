package com.saroty.ter.fragments.navigation.courses;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.ListCourseOfDayRowAdapter;
import com.saroty.ter.fragments.dialog.AddItemDialogFragment;
import com.saroty.ter.fragments.dialog.ItemMenuDialog;
import com.saroty.ter.fragments.dialog.ItemMenuDialog.ItemMenuActionListener;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseListFragment extends Fragment implements ItemMenuActionListener,
        AddItemDialogFragment.AddItemDialogListener
{

    private ListView mList;
    private ListCourseOfDayRowAdapter mAdapter;
    private DateTime mDay;
    private Bundle mBundle;
    private AddItemDialogFragment mAddDialog;

    public CourseListFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_list_courses_of_day, container, false);
        mBundle = new Bundle();
        Bundle b = getArguments();

        if(b != null)
            this.mDay = new DateTime(b.getString("day"));


        this.mList = (ListView) rootView.findViewById(R.id.list_courses_of_day);
        loadDailyCourses();

        this.mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailCourseFragment fragment = DetailCourseFragment.newInstance(mAdapter.getModel()[position].getInterval(),
                        mAdapter.getModel()[position].getCourse(),mDay);
                ((MainActivity) getActivity()).setCurrentFragment(fragment, true);
            }
        });

        this.mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                calendar.setTimeInMillis(mDay.getMilliseconds(TimeZone.getDefault()));
                calendar.set(Calendar.HOUR_OF_DAY,
                        mAdapter.getModel()[position].getInterval().getStart().getHour());
                calendar.set(Calendar.MINUTE,
                        mAdapter.getModel()[position].getInterval().getStart().getMinute());

                mBundle.putSerializable("time", calendar);

                showItemOptionMenu();

                return true;
            }
        });

        return rootView;
    }

    private void loadDailyCourses()
    {
        List<CourseRowModel> model = new ArrayList<>();

        TreeMap<LocalTimeInterval, List<Course>> listDay = ScheduleManager.getInstance().getDailyCourses(mDay);

        for (Map.Entry<LocalTimeInterval, List<Course>> course : listDay.entrySet())
        {
            for (Course c : course.getValue())
            {
                model.add(new CourseRowModel(course.getKey(), c));
            }
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(), model.toArray(new CourseRowModel[model.size()]));

        mList.setAdapter(mAdapter);

    }

    public void showItemOptionMenu(){
        Dialog dialogMenu = ItemMenuDialog.newInstance(getActivity(), this);
        dialogMenu.show();
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

    @Override
    public void validate(String title, String location, int duration, int hour,
                         int minute, DateTime date) {
        mAddDialog.dismiss();
    }

    @Override
    public void cancel() {
        mAddDialog.dismiss();
    }
}
