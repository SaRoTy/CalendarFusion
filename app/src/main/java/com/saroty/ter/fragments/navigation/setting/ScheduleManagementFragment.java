package com.saroty.ter.fragments.navigation.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.saroty.ter.R;
import com.saroty.ter.activities.MainActivity;
import com.saroty.ter.fragments.navigation.filter.CreateFilterFragment;
import com.saroty.ter.fragments.navigation.filter.ManageFilterFragment;

/**
 * Created by Romain on 26/05/2015.
 */
public class ScheduleManagementFragment extends Fragment {

    private FragmentTabHost mTabHost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        mTabHost = (FragmentTabHost)inflater.inflate(R.layout.fragment_schedule_management, container, false);
        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("create").setIndicator(getActivity().getString(R.string.title_create_tab)),
                CreateFilterFragment.class, getArguments());

        mTabHost.addTab(mTabHost.newTabSpec("manage").setIndicator(getActivity().getString(R.string.title_manage_tab)),
                ManageFilterFragment.class, getArguments());


        setHasOptionsMenu(true);

        return mTabHost;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_schedule_manager);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
