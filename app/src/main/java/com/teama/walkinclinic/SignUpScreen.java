package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class SignUpScreen extends AppCompatActivity {

    private TextView firstNameField;
    private TextView lastNameField;
    private TextView emailAddressField;
    private TextView passwordField;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        firstNameField = (TextView)findViewById(R.id.FirstNameField);
        lastNameField = (TextView)findViewById(R.id.LastNameField);
        emailAddressField = (TextView)findViewById(R.id.EmailAddressField);
        passwordField = (TextView)findViewById(R.id.PasswordField);
        signUpButton = (Button)findViewById(R.id.SignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    private void SignUp()
    {
        /*
        String firstName = firstNameField.getText().toString();
        String lastName = firstNameField.getText().toString();
        String emailAddress = emailAddressField.getText().toString();
        String password = passwordField.getText().toString();
        */

        // check if sign up info is correct
        // create a new patient and add them to fire base

        // go back to login screen
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
