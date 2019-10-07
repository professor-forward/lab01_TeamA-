package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    }
}
