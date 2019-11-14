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

import java.text.DateFormatSymbols;

public class WorkDaysScreen extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference shifts;
    CalendarView shiftCalendar;
    EditText edtChooseShiftStart;
    EditText edtChooseShiftEnd;
    Button btnAddShift;

    Shift shift;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_days_screen);

        final String uidemployee = getIntent().getExtras().getString("uidemployee");

        database = FirebaseDatabase.getInstance();
        shifts = database.getReference("Shifts");

        shiftCalendar = findViewById(R.id.cvShifts);
        edtChooseShiftStart = findViewById(R.id.edtChooseShiftStart);
        edtChooseShiftEnd = findViewById(R.id.edtChooseShiftEnd);
        btnAddShift = findViewById(R.id.btnAddShift);

        shiftCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String startHours = edtChooseShiftStart.getText().toString();
                String endHours = edtChooseShiftEnd.getText().toString();
                String hours = startHours + " to " + endHours;
                shift = new Shift(String.valueOf(month), String.valueOf(dayOfMonth),hours);

            }
        });


        btnAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shifts.child(uidemployee).child(shift.getDate()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().setValue(shift.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



    }

}
