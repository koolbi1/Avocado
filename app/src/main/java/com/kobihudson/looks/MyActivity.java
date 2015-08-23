package com.kobihudson.looks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MyActivity extends Activity {

    static AvocadoBank avoBank;
    static TextView avocadoCountText;
    static TextView avocadosPerSecondText;
    static Activity me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        avocadoCountText = (TextView)findViewById(R.id.AvocadoCountText);
        avocadosPerSecondText = (TextView)findViewById(R.id.AvocadosPerSecondText);

        me = this;

        avoBank = new AvocadoBank();
        TimeKeeper keeper = new TimeKeeper();
        keeper.addListener(avoBank);
        keeper.sendTimelyUpdates();//Starts a sending updates every second.

        //---------------------------------------------Buttons--------------------------------------
        final BuyButton AvocadoSeedButton = (BuyButton)findViewById(R.id.AvocadoSeedButton);
        AvocadoSeedButton.setUpButton(BigDecimal.valueOf(10), BigDecimal.valueOf(0.2), avoBank,
                (TextView)findViewById(R.id.costOfSeedsText));

        final BuyButton AvocadoTreeButton = (BuyButton)findViewById(R.id.AvocadoTreeButton);
        AvocadoTreeButton.setUpButton(BigDecimal.valueOf(100), BigDecimal.valueOf(1.2), avoBank,
                (TextView)findViewById(R.id.costOfTreesText));

        final BuyButton AvocadoFarmButton = (BuyButton)findViewById(R.id.AvocadoFarmButton);
        AvocadoFarmButton.setUpButton(BigDecimal.valueOf(1000), BigDecimal.valueOf(8.6), avoBank,
                (TextView)findViewById(R.id.costOfFarmsText));

        final BuyButton AvocadoFactoryButton = (BuyButton)findViewById(R.id.AvocadoFactoryButton);
        AvocadoFactoryButton.setUpButton(BigDecimal.valueOf(10000), BigDecimal.valueOf(60), avoBank,
                (TextView)findViewById(R.id.costOfFactoryText));

        final BuyButton AvocadoBreederButton = (BuyButton)findViewById(R.id.AvocadoBreederButton);
        AvocadoBreederButton.setUpButton(BigDecimal.valueOf(100000), BigDecimal.valueOf(400), avoBank,
                (TextView)findViewById(R.id.costOfFactoryText));

        final BuyButton AvocadoClonerButton = (BuyButton)findViewById(R.id.AvocadoClonerButton);
        AvocadoClonerButton.setUpButton(BigDecimal.valueOf(1000000), BigDecimal.valueOf(3000), avoBank,
                (TextView)findViewById(R.id.costOfFactoryText));

        final ImageButton MainAvoButton = (ImageButton) findViewById(R.id.AvocadoButton);
        MainAvoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                avoBank.addValueToCount(BigDecimal.ONE);
            }
        });
        //---------------------------------------------Buttons--------------------------------------

    }

    public static void updateMainLabels() {
        me.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                avocadoCountText.setText(avoBank.getAvocadoCount() + " Avocados");
                avocadosPerSecondText.setText(avoBank.getAvocadosPerSecond() + " per second");
            }
        });
    }

    public static void saveGame(){
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
