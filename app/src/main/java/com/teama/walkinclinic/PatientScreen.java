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




public class PatientScreen extends AppCompatActivity {

    private TextView tvWelcomePatient;

    FirebaseDatabase database;
    DatabaseReference userPatient;

    private Button SelectServiceBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        tvWelcomePatient = (TextView)findViewById(R.id.tvWelcomePatient);
        database = FirebaseDatabase.getInstance();
        userPatient = database.getReference("Users/patient");

        SelectServiceBtn = findViewById(R.id.btnToServiceScreen);

        final String uidpatient = getIntent().getExtras().getString("uid");

        SelectServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), SelectServiceScreen.class);
                s.putExtra("uidpatient", uidpatient);
                startActivity(s);
            }
        });
        userPatient.child(uidpatient).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              tvWelcomePatient.setText("Welcome "+((String)(dataSnapshot.child("firstName").getValue())) + "! You are logged in as a Patient" );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //extra
            }
        });

    }
}
