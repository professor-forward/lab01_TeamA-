package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignUpScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    private EditText firstNameField;
    private EditText lastNameField;
    private EditText emailAddressField;
    private EditText passwordField;
    private Button btnSignUp;
    private Button toLogInButton;
    private Spinner spinner;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        firstNameField = (EditText) findViewById(R.id.FirstNameField);
        lastNameField = (EditText) findViewById(R.id.LastNameField);
        emailAddressField = (EditText) findViewById(R.id.EnterEmailAddress);
        passwordField = (EditText) findViewById(R.id.PasswordField);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        toLogInButton = (Button) findViewById(R.id.btnToLogIn);
        spinner = (Spinner) findViewById(R.id.spinner);




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String emailAddress = emailAddressField.getText().toString();
                String password = passwordField.getText().toString();
                String type = "patient";

                final User user = new User(type, firstName, lastName, emailAddress, password);

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getEmailAddress()).exists())
                            Toast.makeText(SignUpScreen.this, "The Email Address already exists", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getEmailAddress()).setValue(user);
                            Toast.makeText(SignUpScreen.this, "You have succesfully registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //extra
                    }
                });


            }
        });
    }
}