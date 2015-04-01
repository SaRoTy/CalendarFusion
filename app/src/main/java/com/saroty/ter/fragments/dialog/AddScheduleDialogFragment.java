package com.saroty.ter.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.saroty.ter.R;
import com.saroty.ter.converters.factory.ConverterFactory;
import com.saroty.ter.schedule.Schedule;
import com.saroty.ter.schedule.util.ScheduleFileUtil;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Arthur on 31/03/2015.
 */
public class AddScheduleDialogFragment extends DialogFragment
{
    private ProgressBar mProgressBar;
    private LinearLayout mContentLayout;
    private Button mDownloadButton;
    private EditText mEditText;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add_schedule, null);

        mContentLayout = (LinearLayout) v.findViewById(R.id.content_layout);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        mDownloadButton = (Button) v.findViewById(R.id.button_download);
        mEditText = (EditText) v.findViewById(R.id.dialog_add_schedule_url);


        mDownloadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onUrlValidate(mEditText.getText().toString());
            }
        });

        builder.setView(v);
        return builder.create();
    }

    private void onUrlValidate(String url)
    {
        Log.v("a", url);
        mContentLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public interface AddScheduleDialogListener
    {
        void onFinishAddSchedule(Schedule schedule);
    }

    private class AdaptScheduleTask extends AsyncTask<URL, Void, Schedule>
    {
        protected Context mContext;
        private ConverterFactory mFactory = new ConverterFactory();
        private Exception mException;

        public AdaptScheduleTask()
        {
            this.mContext = mContext.getApplicationContext();
        }

        @Override
        protected Schedule doInBackground(URL... urls)
        {
            //TODO: Implement for multiple URLS
            try
            {
                return mFactory.makeConverter(urls[0]).convert();
            } catch (Exception e)
            {
                this.mException = e;
            }
            return null;
        }

        @Override
        public void onPostExecute(Schedule table)
        {
            try
            {
                ScheduleFileUtil.saveSchedule(table);
            } catch (IOException e)
            {
                mException = e;
            }
            if (mException != null)
                mException.printStackTrace();
        }
    }

}
