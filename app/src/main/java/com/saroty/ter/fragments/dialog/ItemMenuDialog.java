package com.saroty.ter.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.saroty.ter.R;

/**
 * Created by Romain on 21/05/2015.
 */
public class ItemMenuDialog extends AlertDialog.Builder {

    private static ItemMenuActionListener mTargetFragment;

    public static Dialog newInstance(Context context, ItemMenuActionListener listener){
        ItemMenuDialog menu = new ItemMenuDialog(context);
        Dialog dialog = menu.create();
        dialog.setCanceledOnTouchOutside(true);

        mTargetFragment = listener;

        return dialog;
    }

    public ItemMenuDialog(Context context) {
        super(context);

        setTitle("Options");


        CharSequence[] menu = context.getResources().getStringArray(R.array.dialog_item_option_penu);

        setItems(menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //delete
                        mTargetFragment.delete();
                        break;

                    case 1:
                        //add same hour
                        mTargetFragment.add();
                        break;
                }
            }
        });
    }

    public interface ItemMenuActionListener{
        public void add();

        public void delete();
    }


}
