package com.kobihudson.looks;

import java.math.BigDecimal;

/**
 * Created by kobihudson on 5/7/15.
 * This is the bank. It is super simple and pretty much passive. It implements UpdateListener
 * because it listens to the time keeper. When the TimeKeeper sends out its updates
 */
public class AvocadoBank implements UpdateListener {

    private static final String TAG = "AvocadoBank";

    BigDecimal AvocadoCount;
    BigDecimal AvocadosPerSecond;

    public AvocadoBank(){
        //This is only called for first time gameplay
        this.AvocadoCount = BigDecimal.ZERO;
        this.AvocadosPerSecond = BigDecimal.ZERO;
    }

    public AvocadoBank(BigDecimal AvocadoCount, BigDecimal AvocadosPerSecond){
        //If the person has played before this
        this.AvocadoCount = AvocadoCount;
        this.AvocadosPerSecond = AvocadosPerSecond;
    }

    public String getAvocadoCount(){
        //return the current amount of avocados.
        return AvocadoCount.toBigInteger().toString();
    }

    public String getAvocadosPerSecond(){
        //returns the APS value
        return AvocadosPerSecond.toString();
    }

    public void addValueToCount(BigDecimal value){
        AvocadoCount = AvocadoCount.add(value);
        updateCountLabelAndSave();
    }

    public void addValueToAvocadoPerSecond(BigDecimal value){
        AvocadosPerSecond = AvocadosPerSecond.add(value);
        updateCountLabelAndSave();
    }

    /**
     * I want to make purchasing more generic so that in the future it is easier to add different
     * priced items. I know that I am writing all this code but I also want the buy item to be in
     * the bank so that is can be sure to trust it and not have many different purchase methods all
     * over the place. It is up to the item to update it's on cost, that is why this is a bool.
     **/
    public boolean buyItem(BigDecimal itemCost, BigDecimal APSIncrease){
        if(itemCost.compareTo(AvocadoCount)<=0){
            addValueToCount(itemCost.multiply(BigDecimal.valueOf(-1))); //subtract cost from count
            addValueToAvocadoPerSecond(APSIncrease); //add to the aps value
            return true;
        }
        //It costs too much. Tell them no!
        return false;
    }

    public void update(){
        //Things to do when you get an update as a listener
        //All that the bank needs to do right now is update your current count.
        addValueToCount(AvocadosPerSecond);
    }

    //--------------------------------------Count Label---------------------------------------------
    public void updateCountLabelAndSave() {
        MyActivity.updateMainLabels();
    }
    //--------------------------------------Count Label---------------------------------------------

}
