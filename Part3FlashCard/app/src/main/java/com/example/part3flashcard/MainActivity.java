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

public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "aloha";

    // Correct username and password to login
    private static final String usernameCorrect = "user";
    private static final String passwordCorrect = "pass";


    private EditText etxtUsername;
    private EditText etxtPassword;
    private Button btnSignIn;
    private TextView txtLogin;
    private TextView txtUsernameTitle;
    private TextView txtPasswordTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtUsername = (EditText) findViewById(R.id.etxtUsername);
        etxtPassword = (EditText) findViewById(R.id.etxtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtUsernameTitle = (TextView) findViewById(R.id.txtUsernameTitle);
        txtPasswordTitle = (TextView) findViewById(R.id.txtPasswordTitle);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkUsername = etxtUsername.getText().toString();
                String checkPassword = etxtPassword.getText().toString();
                if(checkUsername.equals(usernameCorrect) && checkPassword.equals(passwordCorrect)) {
                    Log.i(MyFlag, "Login successful");
                    Intent GotoActivity2 = new Intent(getApplicationContext(), MathQuestions.class );
                    GotoActivity2.putExtra("username", usernameCorrect);

                    startActivity(GotoActivity2);
                }
                else{
                    Log.e(MyFlag, "Username or password incorrect");
                    Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_LONG).show();
                    // etxtUsername.setText("");
                    etxtPassword.setText("");
                }
            }
        });



    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putString("username", etxtUsername.getText().toString());
        outState.putString("password", etxtPassword.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)  {
            etxtUsername.setText(savedInstanceState.getString("username"));
            etxtPassword.setText(savedInstanceState.getString("password"));
        }
    }
}