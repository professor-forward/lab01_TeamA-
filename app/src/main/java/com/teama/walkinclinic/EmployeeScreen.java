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


public class EmployeeScreen extends AppCompatActivity {

    private TextView tvWelcomeEmployee;

    FirebaseDatabase database;
    DatabaseReference userEmployee;

    private Button infoButton;
    private Button btnToShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);


        tvWelcomeEmployee = (TextView)findViewById(R.id.tvWelcomeEmployee);
        database = FirebaseDatabase.getInstance();
        userEmployee = database.getReference("Users/employee");

        infoButton = (Button) findViewById(R.id.btnToInfo);
        btnToShifts = findViewById(R.id.btnToShifts);

        final String uidemployee = getIntent().getExtras().getString("uid");

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ClinicInfoScreen.class);
                s.putExtra("uidemployee", uidemployee);
                startActivity(s);
            }
        });

        btnToShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ShiftDirectingScreen.class );
                s.putExtra("uidemployee", uidemployee);
                startActivity(s);
            }
        });

        userEmployee.child(uidemployee).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvWelcomeEmployee.setText("Welcome "+((String)(dataSnapshot.child("firstName").getValue())) + "! You are logged in as a Employee" );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //extra
            }
        });



    }
}
