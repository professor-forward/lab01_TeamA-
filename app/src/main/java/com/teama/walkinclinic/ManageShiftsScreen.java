package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView;

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

import java.util.ArrayList;

public class ManageShiftsScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference shifts;

    ListView lvShifts;
    ArrayList<Shift> shiftsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shifts_screen);



        final String uidemployee = getIntent().getExtras().getString("uidemployee");

        database = FirebaseDatabase.getInstance();
        shifts = database.getReference("Shifts");

        lvShifts = findViewById(R.id.lvShifts);
        shiftsList = new ArrayList<Shift>();

        shifts.child(uidemployee).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Shift shift = ds.getValue(Shift.class);
                    shiftsList.add(shift);
                }

                ArrayAdapter<Shift> shiftsAdapter = new ArrayAdapter<Shift>(getApplicationContext(),android.R.layout.simple_list_item_1, shiftsList);
                lvShifts.setAdapter(shiftsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
