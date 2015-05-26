package com.saroty.ter.fragments.navigation.day;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.saroty.ter.R;
import com.saroty.ter.ScheduleApplication;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.adapters.CoursesViewPagerAdapter;
import com.saroty.ter.fragments.dialog.AddItemDialogFragment;
import com.saroty.ter.fragments.navigation.NavigationFragment;
import com.saroty.ter.schedule.Course;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleGroup;
import com.saroty.ter.schedule.ScheduleManager;
import com.saroty.ter.time.LocalTime;
import com.saroty.ter.time.LocalTimeInterval;

import java.util.Iterator;
import java.util.TimeZone;

import hirondelle.date4j.DateTime;

/**
 * Created by Romain on 31/03/2015.
 */
public class DaysNavigationFragment extends NavigationFragment implements AddItemDialogFragment.AddItemDialogListener
{

    private CoursesViewPagerAdapter myViewPagerAdapter;
    private ViewPager mViewPager;
    private DateTime mBaseDay;
    private AddItemDialogFragment mAddDialog;
    private int current;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.mBaseDay = DateTime.today(TimeZone.getDefault());
        Bundle b;

        if((b = getArguments()) != null) {
            current = mBaseDay.numDaysFrom(
                    DateTime.forDateOnly(b.getInt("year"), b.getInt("month"), b.getInt("dayOfMonth"))
            );
        }else{
            current = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_courses_view_pager, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();

        myViewPagerAdapter =
                new CoursesViewPagerAdapter(getChildFragmentManager(), this);

        mViewPager.setAdapter(myViewPagerAdapter);

        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(current, true);
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        current = mViewPager.getCurrentItem();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        if (menu.size() == 0)
            inflater.inflate(R.menu.menu_courses_day_list, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_add_item)
        {

            mAddDialog = AddItemDialogFragment.newInstance(this);
            mAddDialog.show(getFragmentManager(), "AddScheduleDialogFragment");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public DateTime getBaseDay()
    {
        return this.mBaseDay;
    }

    @Override
    public String getNavigationTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_navigation_days);
    }

    @Override
    public String getActionbarTitle()
    {
        return ScheduleApplication.getContext().getString(R.string.title_days);
    }

    //TODO possible d'ajouter sur un autre que celui pesonnal (pour plus tard) pas sur pour l'opti
    @Override
    public void validate(String title, String location, int duration, int hour,
                         int minute, DateTime date) {
        ScheduleManager manager = ScheduleManager.getInstance();
        Schedule perso = null;
        int i = 0;
        boolean trouve = false;

        if(!manager.containSchedule(manager.getDEFAULT_GROUP_NAME(),"Personal")) {
            perso = new Schedule();
            perso.setName("Personal");
            perso.setGroupName(manager.getDEFAULT_GROUP_NAME());
            manager.addSchedule(perso, true);
        }else{
            ScheduleGroup[] groups = manager.getGroups();

            while(i< groups.length && !trouve){
                for(Iterator<Schedule> ite = groups[i].getScheduleList().iterator();ite.hasNext() &&
                        !trouve;){
                    perso = ite.next();
                    if(perso.getName().equals("Personal"))
                        trouve = true;
                }
                i++;
            }
        }

        Course course = new Course(title);
        course.setRoom(location);

        LocalTime start = new LocalTime(hour,minute);
        LocalTime end = new LocalTime(hour + (duration/60), minute + duration%60);
        LocalTimeInterval inter = new LocalTimeInterval(start,end);

        perso.addCourse(course,date,inter);

        Toast.makeText(getActivity().getApplicationContext(),
                title + "ajouté",
                Toast.LENGTH_LONG).show();

        mAddDialog.dismiss();
    }

    @Override
    public void cancel() {
        mAddDialog.dismiss();
    }
}
