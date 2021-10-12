package com.example.stoplight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView lightLabel;
    Button lightButton;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLabel = (TextView) findViewById(R.id.lightLabel);
        lightButton = (Button) findViewById(R.id.lightButton);

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                counter++;
                if(counter == 1) {
                    lightLabel.setBackgroundColor(Color.YELLOW);
                }
                else if(counter == 2){
                    lightLabel.setBackgroundColor(Color.GREEN);
                }
                else{
                    counter = 0;
                    lightLabel.setBackgroundColor(Color.RED);
                }
            }
        });
    }


}