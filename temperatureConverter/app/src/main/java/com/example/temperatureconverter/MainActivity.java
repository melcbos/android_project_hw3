package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    TextView celsiusView, fahrenheitView, skiOutput, swimOutput;
    SeekBar celsiusSeekbar;
    SeekBar fahreinheitSeekbar;
    double celval,fahval;
    String tag = "lfa";


    //celval and fahval are rounded values to two decimal places
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        celsiusView = (TextView) findViewById(R.id.celVal);
        fahrenheitView = (TextView) findViewById(R.id.fahVal);
        celsiusSeekbar = (SeekBar) findViewById(R.id.celsiusSeekBar);
        fahreinheitSeekbar = (SeekBar) findViewById(R.id.fahrenheitSeekBar);
        skiOutput = (TextView) findViewById(R.id.message);
        swimOutput= (TextView) findViewById(R.id.message3);
        swimOutput.setVisibility(View.INVISIBLE);
        skiOutput.setVisibility(View.INVISIBLE);

        fahreinheitSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //to get two decimal places scale down the seekBar which has a max of 6600
                //celsius scale max is actually 66 degrees
                double numFarenheit = (double) (i *0.01);
                fahval = Math.round(numFarenheit*100.0)/100.0;
                String s1 =""+fahval;

                double numCelsius = (fahval-32)*(0.5555);
                celval = Math.round(numCelsius*100.0)/100.0;
//                Log.d(tag, "fahval= "+ fahval);
//                Log.d(tag, "numCelsius"+ numCelsius);
//                Log.d(tag, "onProgressChanged: "+ celval);

                String s2 = ""+celval;
                fahrenheitView.setText(s1);
                celsiusView.setText(s2);

                int numCelsiusInt = (int) (celval*100);
                celsiusSeekbar.setProgress(numCelsiusInt);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Double celsiusCheck = Double.parseDouble(celsiusView.getText().toString());
                Log.d(tag, "celsius: "+ celsiusCheck);
                if (celsiusCheck<12 ){
                    swimOutput.setVisibility(View.INVISIBLE);
                    skiOutput.setVisibility(View.VISIBLE);

                }
                else{
                    skiOutput.setVisibility(View.INVISIBLE);
                    swimOutput.setVisibility(View.VISIBLE);
                }


            }
        });


        celsiusSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //to get two decimal places scale down the seekBar which has a max of 15000
                //farenheit scale max is actually 150 degrees
                double numCelsius = (double) (i *0.01);
                celval = Math.round(numCelsius*100.0)/100.0;
                String s1 =""+celval;

                double numFarenheit = celval* 1.8 + 32;
                fahval = Math.round(numFarenheit*100.0)/100.0;
                String s2 = ""+fahval;
                celsiusView.setText(s1);
                fahrenheitView.setText(s2);
                int numFarenheitInt = (int) (fahval*100);

                //we converted fahval to the 0 -15000 (max for farenheit) scale by multiplying it by 100

                fahreinheitSeekbar.setProgress(numFarenheitInt);


//                if(celsiusView.getText().toString().equals("66.0")) celsiusView.setText("66.00");
//                if(celsiusView.getText().toString().equals("0.0")) celsiusView.setText("0.00");


            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Double celsiusCheck = Double.parseDouble(celsiusView.getText().toString());
                Log.d(tag, "celsius: "+ celsiusCheck);
                if (celsiusCheck<12 ){
                    swimOutput.setVisibility(View.INVISIBLE);
                    skiOutput.setVisibility(View.VISIBLE);

                }
                else{
                    skiOutput.setVisibility(View.INVISIBLE);
                    swimOutput.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}