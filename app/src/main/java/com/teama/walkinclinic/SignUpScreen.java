package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SignUpScreen extends AppCompatActivity {

    private TextView firstNameField;
    private TextView lastNameField;
    private TextView emailAddressField;
    private TextView passwordField;
    private Button signUpButton;

    private ArrayList<User> userList;

    private DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        firstNameField = (TextView)findViewById(R.id.FirstNameField);
        lastNameField = (TextView)findViewById(R.id.LastNameField);
        emailAddressField = (TextView)findViewById(R.id.EmailAddressField);
        passwordField = (TextView)findViewById(R.id.PasswordField);
        signUpButton = (Button)findViewById(R.id.SignUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String emailAddress = emailAddressField.getText().toString();
                String password = passwordField.getText().toString();

                Log.d("firstName",firstName);
                Log.d("lastName",lastName);
                Log.d("email",emailAddress);
                Log.d("password",password);


                SignUp(firstName,lastName,emailAddress,password);
            }
        });
    }

    protected void onStart()
    {
        super.onStart();

        userList = new ArrayList<User>();

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot userSnapShot : dataSnapshot.getChildren())
                {
                    User user = userSnapShot.getValue(User.class);
                    userList.add(user);
                    Log.d("FIREBASE READ", "data has been retrieved");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SignUp(String firstName, String lastName, String emailAddress, String password)
    {
        // create a new patient and add them to fire base
        String id = databaseUsers.push().getKey();
        User patient = new Patient(id, firstName, lastName, emailAddress, password);
        databaseUsers.child(id).setValue(patient);

        Log.d("FIREBASE WRITE","data was written");

        // go back to login screen
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
