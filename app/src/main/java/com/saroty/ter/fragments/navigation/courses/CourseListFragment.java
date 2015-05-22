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
import com.saroty.ter.fragments.dialog.AddScheduleDialogFragment;
import com.saroty.ter.fragments.dialog.ItemMenuDialog;
import com.saroty.ter.models.list.CourseRowModel;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import biweekly.property.Timezone;
import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 24/03/2015.
 */
public class CourseListFragment extends Fragment implements ItemMenuDialog.ItemMenuActionListener,
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
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                calendar.setTimeInMillis(mDay.getMilliseconds(TimeZone.getDefault()));

                mBundle.putSerializable("time", calendar);

                showItemOptionMenu();

                return false;
            }
        });

        return rootView;
    }

    private void loadDailyCourses()
    {
        CourseRowModel[] model;
        int i = 0;

        TreeMap<LocalTimeInterval, Course> listDay = ScheduleManager.getInstance().getDailyCourses(mDay);

        model = new CourseRowModel[listDay.size()];

        for (Map.Entry<LocalTimeInterval, Course> course : listDay.entrySet())
        {
            model[i] = new CourseRowModel(course.getKey(), course.getValue());
            i++;
        }

        mAdapter = new ListCourseOfDayRowAdapter(getActivity().getApplicationContext(), model);

        mList.setAdapter(mAdapter);

    }

    public void showItemOptionMenu(){
        Dialog dialogMenu = ItemMenuDialog.newInstance(getActivity(),this);
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
    public void validate() {
        mAddDialog.dismiss();
    }

    @Override
    public void cancel() {
        mAddDialog.dismiss();
    }
}
