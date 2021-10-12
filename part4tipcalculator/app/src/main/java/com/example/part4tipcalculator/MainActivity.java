package com.example.part4tipcalculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tipPercent,tipView, totalView, txtTip, txtTotal;
    SeekBar barTipAmount;
    EditText editBill;

    double tipVal, totalVal = 0;
    int tipPerc = 0;
    private double totalAsNumber = 0;
    String billString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tipPercent = (TextView)findViewById(R.id.tipPercent);
        tipView = (TextView)findViewById(R.id.tipValue);
        totalView = (TextView)findViewById(R.id.totalValue);
        txtTip = (TextView)findViewById(R.id.txtTip);
        txtTotal = (TextView)findViewById(R.id.txtTotal);
        barTipAmount = (SeekBar) findViewById(R.id.barTipAmount);
        editBill = (EditText) findViewById(R.id.editBill);
        tipPercent.setText("0%");
        txtTip.setText("Tip");
        txtTotal.setText("Total");
        tipView.setText("$0.00");
        totalView.setText("$0.00");


        barTipAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //When the seekbar is touched this function is called.
                //Since the seekBar goes from (0 - 100), we scale it down by 1/4
                int tipPercentage = (int) (i * 0.25);
                tipPerc = Math.round(tipPercentage);
                tipPercent.setText(tipPerc + "%");

                updateVals();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        editBill.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    billString = editBill.getText().toString();
                    if(billString.equals("")) {billString = "0";}

                    //check if the first character is "$" to make sure app doesn't crash
                    if (billString.charAt(0) == '$'){
                        billString = billString.substring(1);
                    }

                    //divide by 100 to get value in pennies
                    totalAsNumber = Float.parseFloat(billString);
                    totalAsNumber/= 100.0;
                    editBill.setText("$"+totalAsNumber);
                    updateVals();
                }
                return false;
            }
        });
    };

    public void updateVals(){
        //this function will update values on screen
        tipVal = Math.round(totalAsNumber * tipPerc) / 100.0;
        totalVal = Math.round((totalAsNumber + tipVal) * 100.0) / 100.0;

        //update the values on screen
        tipView.setText("$" + tipVal);
        totalView.setText("$" + totalVal);
    }
}