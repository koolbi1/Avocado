package com.kobihudson.looks;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * Created by kobihudson on 5/7/15.
 */
public class BuyButton extends Button  {

    BigDecimal cost;
    BigDecimal avocadosPerSecond;
    AvocadoBank buttonsBank;
    BigDecimal count;
    TextView costTextLabel;



    private static final String TAG = "BuyButton";

    public BuyButton(Context context) {
        super(context);
    }

    public BuyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BuyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BigDecimal getAvocadosPerSecond() {
        return avocadosPerSecond;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getCount() {
        return count;
    }

    public AvocadoBank getButtonsBank() {
        return buttonsBank;
    }

    public void setAvocadosPerSecond(BigDecimal avocadosPerSecond) {
        this.avocadosPerSecond = avocadosPerSecond;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCount(BigDecimal count){
        this.count = count;
    }

    public void setButtonsBank(AvocadoBank buttonsBank) {
        this.buttonsBank = buttonsBank;
    }

    public void setUpOnClickListener(){
        BuyButton.this.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonsBank.buyItem(BuyButton.this);
            }
        });
    }

    public void setCostText(String costText){
      this.costTextLabel.setText(costText);
    }

    public void setUpButton(BigDecimal cost, BigDecimal APS, AvocadoBank avoBank, TextView costTextLabel){
        this.setCost(cost);
        this.setAvocadosPerSecond(APS);
        this.setButtonsBank(avoBank);
        this.setUpOnClickListener();
        this.setCount(BigDecimal.ZERO);
        this.costTextLabel = costTextLabel;
    }




}
