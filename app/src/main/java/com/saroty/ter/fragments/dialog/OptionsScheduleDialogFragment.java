package com.saroty.ter.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

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
                                ScheduleManager.getInstance().updateSchedule(mSchedule.getUUID());
                                break;
                            case 1: // Rename
                                showRenameDialog();
                                break;
                            case 2: // toggle Disable
                                if (mSchedule.isEnabled())
                                    ScheduleManager.getInstance().disableSchedule(mSchedule.getUUID());
                                else
                                    ScheduleManager.getInstance().enableSchedule(mSchedule.getUUID());
                                break;
                            case 3: // Delete
                                ScheduleManager.getInstance().deleteSchedule(mSchedule.getUUID());
                        }
                    }
                });

        AlertDialog dialog = builder.create();

        /*int titleDividerId = getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = dialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(getResources().getColor(R.color.blue));*/

        return dialog;
    }

    private void showRenameDialog()
    {
        final EditText input = new EditText(getActivity());
        input.setSingleLine();


        new AlertDialog.Builder(getActivity())
                .setTitle("Rename " + mSchedule.getName())
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        ScheduleManager.getInstance().renameSchedule(mSchedule.getUUID(), input.getText().toString());
                    }
                }).setNegativeButton("Cancel", null).show();
    }
}
