package com.example.part3flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity {

    private TextView txtWellDone;
    private View dividerResults;
    private Button btnRetry;
    private Button btnLogOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        txtWellDone = (TextView) findViewById(R.id.txtWellDone);
        dividerResults = (View) findViewById(R.id.dividerResults);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnRetry = (Button) findViewById(R.id.btnRetry);

        Bundle stuffFromQuestions = getIntent().getExtras();
        int numberCorrect = stuffFromQuestions.getInt("numberCorrect");

        Toast.makeText(Results.this, "Finished! You got " + numberCorrect + " out of 10.", Toast.LENGTH_LONG).show();

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoActivity2 = new Intent(getApplicationContext(), MathQuestions.class);
                GotoActivity2.putExtra("username", stuffFromQuestions.getString("username"));

                startActivity(GotoActivity2);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GotoActivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(GotoActivity1);

            }
        });


    }
}