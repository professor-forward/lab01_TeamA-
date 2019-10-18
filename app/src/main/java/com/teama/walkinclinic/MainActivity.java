package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView emailAddressField;
    private TextView passwordField;
    private Button logInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddressField = (TextView)findViewById(R.id.EmailAddressField);
        passwordField = (TextView)findViewById(R.id.PasswordField);
        logInButton = (Button)findViewById(R.id.LogInButton);
        signUpButton = (Button)findViewById(R.id.SignUpButton);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = emailAddressField.getText().toString();
                String password = passwordField.getText().toString();
                LogIn(emailAddress,password);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadSignUpScreen();
            }
        });
    }

    private void LoadSignUpScreen()
    {
        Intent newIntent = new Intent(getApplicationContext(),SignUpScreen.class);
        startActivity(newIntent);
    }

    private void LogIn(String emailAddress, String password)
    {
        // fetch information from fire base and verify with input data
    }
}
