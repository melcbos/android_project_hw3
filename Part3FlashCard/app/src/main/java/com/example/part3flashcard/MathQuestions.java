package com.example.part3flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MathQuestions extends AppCompatActivity {

    private TextView txtOp1;
    private TextView txtOp2;
    private TextView txtOperator;
    private TextView txtQuestionNumber;
    private TextView txtQuestionLabel;
    private EditText etxtAnswer;
    private Button btnNext;

    private int numberCorrect;
    private float correctAnswer;
    private int currentQuestion;

    private static final String MyFlag = "aloha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_questions);

        Bundle stuffFromLogin = getIntent().getExtras();
        String user = stuffFromLogin.getString("username");
        Toast.makeText(MathQuestions.this, "Welcome " + user + "!", Toast.LENGTH_LONG).show();



        txtOp1 = (TextView) findViewById(R.id.txtOp1);
        txtOp2 = (TextView) findViewById(R.id.txtOp2);
        txtOperator = (TextView) findViewById(R.id.txtOperator);
        txtQuestionNumber = (TextView) findViewById(R.id.txtQuestionNumber);
        txtQuestionLabel= (TextView) findViewById(R.id.txtQuestionLabel);
        etxtAnswer = (EditText) findViewById(R.id.etxtAnswer);
        btnNext = (Button) findViewById(R.id.btnNext);

        currentQuestion = 1;

        generateQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAnswer = etxtAnswer.getText().toString();
                if (!userAnswer.equals("")) {
                    checkAnswer();
                    currentQuestion += 1;
                    if (currentQuestion == 11) {
                        Log.i(MyFlag, "Quiz done. Correct Answer is " + numberCorrect + " out of 10.");
                        Intent GotoActivity3 = new Intent(getApplicationContext(), Results.class);
                        GotoActivity3.putExtra("username", user);
                        GotoActivity3.putExtra("numberCorrect", numberCorrect);

                        startActivity(GotoActivity3);
                    } else {
                        txtQuestionNumber.setText(currentQuestion + "");
                        etxtAnswer.setText("");
                        generateQuestion();
                    }
                }
            }
        });
    }

    public void generateQuestion() {
        int randomOp1 = (int) (10 + (Math.random()*135));
        int randomOp2 = (int) ((Math.random()*13));
        double randomOperator = (Math.random());

        // makes sure divide by zero can't happen

        while (randomOperator >= 0.5 && randomOp2 == 0) {
            randomOp2 = (int) ((Math.random()*13));
        }

        if(randomOperator < 0.5) {
            txtOperator.setText("X");
            correctAnswer =  randomOp1 * randomOp2;
        }
        else {
            txtOperator.setText("/");
            correctAnswer = ((float) randomOp1) / randomOp2;
            int correctAnswerInt = (int) correctAnswer;
            while (correctAnswerInt != correctAnswer) {
                randomOp1 = (int) (10 + (Math.random()*135));
                correctAnswer = ((float) randomOp1) / randomOp2;
                correctAnswerInt = (int) correctAnswer;

            }
        }
        txtOp1.setText(randomOp1 + "");
        txtOp2.setText(randomOp2 + "");
    }

    public void checkAnswer() {
        float userAnswer = Float.parseFloat(etxtAnswer.getText().toString());
        if(userAnswer == correctAnswer) {
            numberCorrect+=1;
            Log.i(MyFlag, "Correct");
        }
        Log.i(MyFlag, "Your answer: " + userAnswer);
        Log.i(MyFlag, "Correct answer: " + correctAnswer);
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString("txtOp1", txtOp1.getText().toString());
        outState.putString("txtOp2", txtOp2.getText().toString());
        outState.putString("txtOperator", txtOperator.getText().toString());
        outState.putString("txtQuestionNumber", txtQuestionNumber.getText().toString());
        outState.putString("txtQuestionLabel", txtQuestionLabel.getText().toString());
        outState.putString("etxtAnswer", etxtAnswer.getText().toString());

        outState.putInt("numberCorrect", numberCorrect);
        outState.putInt("currentQuestion", currentQuestion);

        outState.putFloat("correctAnswer", correctAnswer);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)  {
            txtOp1.setText(savedInstanceState.getString("txtOp1"));
            txtOp2.setText(savedInstanceState.getString("txtOp2"));
            txtOperator.setText(savedInstanceState.getString("txtOperator"));
            txtQuestionNumber.setText(savedInstanceState.getString("txtQuestionNumber"));
            txtQuestionLabel.setText(savedInstanceState.getString("txtQuestionLabel"));
            etxtAnswer.setText(savedInstanceState.getString("etxtAnswer"));

            numberCorrect = savedInstanceState.getInt("numberCorrect");
            currentQuestion = savedInstanceState.getInt("currentQuestion");

            correctAnswer = savedInstanceState.getFloat("correctAnswer");

        }
    }


}