package com.kobihudson.looks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

/**
 * Created by kobihudson on 5/7/15.
 * This is the class for the buttons on the screen. I made this so that there was some extra freedom
 * but I don't know how I feel about it. It could use quite a bit of rework. It also does not have
 * many standards right now.
 */
public class BuyButton extends Button  {

    BigDecimal cost;
    BigDecimal avocadosPerSecond;
    AvocadoBank buttonsBank;
    BigDecimal count;
    TextView costTextLabel;

    private static final String TAG = "BuyButton";

    public BuyButton(Context context) {
        //Just here to extend button
        super(context);
    }

    public BuyButton(Context context, AttributeSet attrs) {
        //Just here to extend button
        super(context, attrs);
    }

    public BuyButton(Context context, AttributeSet attrs, int defStyle) {
        //Just here to extend button
        super(context, attrs, defStyle);
    }

    //---------------------------------------set-up-------------------------------------------------
    public void setUpButton(BigDecimal cost, BigDecimal APS,AvocadoBank avoBank,
                            TextView costTextLabel){
        //This is bascally the constructor because it gets constructed in the xml.
        //I don't know if I want to set these this way but oh well.
        this.setCost(cost);
        this.setAvocadosPerSecond(APS);
        this.setButtonsBank(avoBank);
        this.setUpOnClickListener();
        this.setCount(BigDecimal.ZERO);
        this.setCostTextLabel(costTextLabel);
    }

    public void setUpOnClickListener(){
        BuyButton.this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(buttonsBank.buyItem(BuyButton.this.cost, BuyButton.this.avocadosPerSecond)) {
                    BigDecimal newCost = new BigDecimal(BuyButton.this.getCost().
                            multiply(BigDecimal.valueOf(1.1)).toBigInteger());
                    BuyButton.this.setCost(newCost);
                    MyActivity.saveKeyAndVal(BuyButton.this.getId()+"", newCost.toString());
                    //Update label text as well as APS label
                    BuyButton.this.setCostText(newCost.toString());
                }
            }
        });
    }

    //----------------------------------------getters-----------------------------------------------
    public BigDecimal getAvocadosPerSecond() {
        //Just a return
        return avocadosPerSecond;
    }

    public BigDecimal getCost() {
        //Just a return
        return cost;
    }

    public BigDecimal getCount() {
        //Just a return
        return count;
    }

    public AvocadoBank getButtonsBank() {
        //Just a return
        return buttonsBank;
    }

    //----------------------------------------setters-----------------------------------------------
    public void setAvocadosPerSecond(BigDecimal avocadosPerSecond) {
        this.avocadosPerSecond = avocadosPerSecond;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCostTextLabel(TextView costLabel){
        this.costTextLabel = costLabel;
    }

    public void setCount(BigDecimal count){
        this.count = count;
    }

    public void setButtonsBank(AvocadoBank buttonsBank) {
        this.buttonsBank = buttonsBank;
    }

    public void setCostText(String costText){
        //This is to set the cost label
        this.costTextLabel.setText(costText);
    }

    //BigDecimal newCost = new BigDecimal(button.getCost().multiply(BigDecimal.valueOf(1.1)).toBigInteger());

}
