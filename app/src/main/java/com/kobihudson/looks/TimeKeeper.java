package com.kobihudson.looks;

/**
 * Created by kobihudson on 8/22/15.
 * This is a nice method that you can add TimeKeeperListeners to. If you add a listener to it the
 * listener must implement a UpdateListener. This means it has an update method that knows what
 * to do when the time keeper goes to update eveyrbody.
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
    private List<UpdateListener> listeners = new ArrayList<UpdateListener>();
    public void addListener(UpdateListener toAdd) {
        listeners.add(toAdd);
    }

    public void notifyListeners() {
        for (UpdateListener TKL : listeners) {
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
