package com.saroty.ter.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.saroty.ter.R;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.ScheduleManager;

/**
 * Created by Arthur on 08/05/2015.
 */
public class OptionsScheduleDialogFragment extends DialogFragment
{
    private static final String SCHEDULE_KEY = "schedule";

    private Schedule mSchedule;

    public static DialogFragment newInstance(Schedule schedule)
    {
        OptionsScheduleDialogFragment f = new OptionsScheduleDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(SCHEDULE_KEY, schedule);
        f.setArguments(args);
        return f;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mSchedule = (Schedule) getArguments().getSerializable(SCHEDULE_KEY);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(mSchedule.getName())
                .setItems(R.array.dialog_options_schedule_menu, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case 0: // Reload
                                break;
                            case 1: // Rename

                                break;
                            case 2: // toggle Disable
                                ScheduleManager.getInstance().disableSchedule(mSchedule.getUUID());
                                break;
                            case 3: // Delete
                                ScheduleManager.getInstance().deleteSchedule(mSchedule.getUUID());
                        }
                    }
                });

        return builder.create();
    }
}
