package com.kobihudson.looks;

/**
 * Created by kobihudson on 8/22/15.
 */
import android.util.Log;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TimeKeeper implements Runnable{

    private static final String TAG = "TimeKeeper";
    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    //Keeping time --------------------------------------------
    public void sendTimelyUpdates() {
        //Log.e(TAG, "here");
        scheduler.scheduleAtFixedRate(this, 1, 1, SECONDS);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        this.notifyListeners();
        //Log.e(TAG, "Notified everyone");
    }
    //Keeping time --------------------------------------------



    //listener stuff ------------------------------------------
    private List<TimeKeeperListener> listeners = new ArrayList<TimeKeeperListener>();
    public void addListener(TimeKeeperListener toAdd) {
        listeners.add(toAdd);
    }

    public void notifyListeners() {
        for (TimeKeeperListener TKL : listeners) {
            //Log.e(TAG, "notify");
            try {
                TKL.update();
            }catch(Exception e){
                Log.e(TAG, "ERROR: "+e);
            }
        }
    }
    //listener stuff ------------------------------------------




}
