package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {

    private EditText emailAddressField;
    private EditText passwordField;
    private Button logInButton;
    private Button toSignUpButton;

    FirebaseDatabase database;
    DatabaseReference users;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        auth = FirebaseAuth.getInstance();

        emailAddressField = (EditText)findViewById(R.id.edtLogInEmailAddress);
        passwordField = (EditText)findViewById(R.id.edtLogInPassword);
        logInButton = (Button)findViewById(R.id.btnLogIn);
        toSignUpButton = (Button)findViewById(R.id.btnToSignUp);





        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = emailAddressField.getText().toString();
                String password = passwordField.getText().toString();

                if(emailAddress.equals("admin") && password.equals("5T5ptQ"))
                {
                    Intent s = new Intent(getApplicationContext(), AdministratorScreen.class);
                    startActivity(s);
                }
                else {//added to remove incorrect login credentials
                    auth.signInWithEmailAndPassword(emailAddress, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = auth.getInstance().getCurrentUser();
                                        final String uid = user.getUid();

                                        users.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.child("patient").child(uid).exists()) {
                                                    Intent s = new Intent(getApplicationContext(), PatientScreen.class);
                                                    s.putExtra("uid", uid);
                                                    startActivity(s);
                                                } else if (dataSnapshot.child("employee").child(uid).exists()) {
                                                    Intent s = new Intent(getApplicationContext(), EmployeeScreen.class);
                                                    s.putExtra("uid", uid);
                                                    startActivity(s);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                //not needed
                                            }
                                        });
                                    } else {
                                        Toast.makeText(MainActivity.this, "Incorrect login credentials", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
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

    @Override
    protected void onStart() {
        super.onStart();
        //username: admin
        //pwd: 5T5ptQ
        FirebaseUser currentUser = auth.getCurrentUser();

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("admin").exists())){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
