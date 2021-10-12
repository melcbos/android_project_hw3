package com.example.trackcalories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Item1, Item2, Item3, Item4,Item5;
    Button submit;
    TextView result;

    String tag = "lfa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Item1 = (EditText) findViewById(R.id.servingsItem1);
        Item2= (EditText) findViewById(R.id.servingsItem2);
        Item3 = (EditText) findViewById(R.id.servingsItem3);
        Item4 = (EditText) findViewById(R.id.servingsItem4);
        Item5 = (EditText) findViewById(R.id.servingsitem5);
        submit = (Button) findViewById(R.id.calc);
        result = (TextView) findViewById(R.id.totalCalories);

        submit.setOnClickListener(new View.OnClickListener() {
            Integer sum;
            Integer numItem1, numItem2, numItem3, numItem4, numItem5;

            @Override
            public void onClick(View view) {
                numItem1 = Integer.parseInt(Item1.getText().toString());
                numItem2 = Integer.parseInt(Item2.getText().toString());
                numItem3 = Integer.parseInt(Item3.getText().toString());
                numItem4 = Integer.parseInt(Item4.getText().toString());
                numItem5 = Integer.parseInt(Item5.getText().toString());

                sum = numItem1*(12)+ numItem2*(365) + numItem3*(354) + numItem4*(320) + numItem5*(150);
                //Log.d(tag, "onClick: " + sum);
                result.setText(sum.toString());


            }
        });

    }
}