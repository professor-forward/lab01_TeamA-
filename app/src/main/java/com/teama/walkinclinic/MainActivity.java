package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView emailAddressField;
    private TextView passwordField;
    private Button logInButton;
    private Button toSignUpButton;

    FirebaseDatabase database;
    DatabaseReference accounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddressField = (TextView)findViewById(R.id.EmailAddressField);
        passwordField = (TextView)findViewById(R.id.PasswordField);
        logInButton = (Button)findViewById(R.id.LogInButton);
        toSignUpButton = (Button)findViewById(R.id.btnToSignUp);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = emailAddressField.getText().toString();
                String password = passwordField.getText().toString();
                LogIn(emailAddress,password);
            }
        });

        toSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), SignUpScreen.class);
                startActivity(s);
            }
        });
    }

    private void LoadSignUpScreen()
    {
        Intent newIntent = new Intent(getApplicationContext(),SignUpScreen.class);
        startActivity(newIntent);
    }

    private void LogIn(final String emailAddressField, final String passwordField)
    {
        accounts.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.child(emailAddressField).exists()){
                    if(!emailAddressField.isEmpty()){
                        User login = dataSnapshot.child(emailAddressField).getValue(User.class);
                        if(login.getPassword().equals(passwordField)){
                            Toast.makeText(MainActivity.this,"Succesful Login", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                        }
                        }
                    else{
                        Toast.makeText(MainActivity.this, "Email is not registered", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
            //check online
            }

        });
    }
}
