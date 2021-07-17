package com.assignment.ceylonlinuxassignment.async_task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.ceylonlinuxassignment.Network.JsonReaderGet;
import com.assignment.ceylonlinuxassignment.Utils.Utility;

import java.util.ArrayList;

public class getAllData extends android.os.AsyncTask<String[], String[], String[]> {

    @SuppressLint("StaticFieldLeak")
    private final Activity mContext;

    public getAllData(Activity context){
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
    }

    @SuppressLint("WrongThread")
    @Override
    protected String[] doInBackground(String[]... strings) {
        String[] s = null;
        try {
            if(!Utility.isNetworkNotAvailable(mContext)) {
             s =   JsonReaderGet.getAPI(mContext);
            }
        } catch (Exception e) {
            Log.e("getAllProducts", e.toString());
        }
        return s;
    }


    @Override
    protected void onPostExecute(String[] response) {

        super.onPostExecute(response);
    }
}