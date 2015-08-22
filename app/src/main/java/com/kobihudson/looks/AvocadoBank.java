package com.kobihudson.looks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by kobihudson on 5/7/15.
 */
public class AvocadoBank implements TimeKeeperListener{

    private static final String TAG = "AvocadoBank";

    BigDecimal AvocadoCount;
    BigDecimal AvocadosPerSecond;
    TextView countToUpdate;
    TextView avocadosPerSecondToUpDate;
    Activity myActivity;

    public AvocadoBank(TextView countToUpdate, TextView avocadosPerSecondToUpDate, Activity myActivity){
        this.AvocadoCount = BigDecimal.ZERO;
        this.AvocadosPerSecond = BigDecimal.ZERO;
        this.countToUpdate = countToUpdate;
        this.avocadosPerSecondToUpDate = avocadosPerSecondToUpDate;
        this.myActivity = myActivity;
    }

    public String getAvocadoCount(){
        return AvocadoCount.toString();
    }

    public String getAvocadosPerSecond(){
        return AvocadosPerSecond.toString();
    }

    public void addOneToCount(){
        addValueToCount(BigDecimal.ONE);
    }

    public void addValueToCount(BigDecimal value){
        AvocadoCount = AvocadoCount.add(value);
        updateCountText();
    }

    public void updateCountText(){
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                countToUpdate.setText(AvocadoCount.setScale(0, RoundingMode.HALF_UP) + " Avocados");
            }
        });
    }

    public void updateAvocadosPerSecondText(){
        myActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                avocadosPerSecondToUpDate.setText(AvocadosPerSecond + " per second");
            }
        });
    }

    public void addValueToAvocadoPerSecond(BigDecimal value){
        AvocadosPerSecond = AvocadosPerSecond.add(value);
        updateAvocadosPerSecondText();
    }

    public void buyItem(BuyButton button){
        if(button.getCost().compareTo(AvocadoCount)<=0){
            addValueToCount(button.getCost().multiply(BigDecimal.valueOf(-1))); //subtract cost
            addValueToAvocadoPerSecond(button.getAvocadosPerSecond()); //add to the aps value
            BigDecimal newCost = new BigDecimal(button.getCost().multiply(BigDecimal.valueOf(1.1)).toBigInteger());
            Log.e(TAG, newCost.toString());
            button.setCost(newCost);
            button.setCostText(newCost.toString());
        }
    }

    public void update(){
        //Things to do when you get an update as a listener
        updateAvocadosPerSecondText();
        updateCountText();
        addValueToCount(AvocadosPerSecond);
    }


}
