package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;
    FirebaseAuth auth;


    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmailAddress;
    private EditText edtPassword;
    private Button btnSignUp;
    private Button toLogInButton;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        auth = FirebaseAuth.getInstance();

        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtEmailAddress = (EditText) findViewById(R.id.edtEmailAddress);
        edtPassword = (EditText) findViewById(R.id.edtPassword);


        spinner = (Spinner) findViewById(R.id.spinner);


        toLogInButton = (Button) findViewById(R.id.btnToLogIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = edtFirstName.getText().toString();
                final String lastName = edtLastName.getText().toString();
                final String emailAddress = edtEmailAddress.getText().toString();
                final String password = edtPassword.getText().toString();
                final String type = spinner.getSelectedItem().toString();




                auth.createUserWithEmailAndPassword(emailAddress,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user;

                                    if(type.equals("patient")){
                                        user = new Patient(firstName, lastName, emailAddress);}
                                        else{
                                        user = new Employee(firstName, lastName, emailAddress);}
                                        users.child(type).child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(SignUpScreen.this,"You have sucessfully registered",Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                }else{
                                    Toast.makeText(SignUpScreen.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        toLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(s);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser() != null){
            //handle logged in user
        }
    }
}