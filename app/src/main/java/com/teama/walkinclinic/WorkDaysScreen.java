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

public class WorkDaysScreen extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference shifts;
    CalendarView shiftCalendar;
    EditText edtChooseShiftStart;
    EditText edtChooseShiftEnd;
    Button btnAddShift;

    final String uidemployee = getIntent().getExtras().getString("uidemployee");
    Employee LANCER = new Employee("Cu", "Chulain", "gaebolg@gmail.com");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_days_screen);

        database = FirebaseDatabase.getInstance();
        shifts = database.getReference("Users").child("employee");

        shiftCalendar = findViewById(R.id.cvShifts);
        edtChooseShiftStart = findViewById(R.id.edtChooseShiftStart);
        edtChooseShiftEnd = findViewById(R.id.edtChooseShiftEnd);
        btnAddShift = findViewById(R.id.btnAddShift);


        btnAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shiftCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        String hours = Integer.toString((Integer.parseInt(edtChooseShiftStart.getText().toString()) - Integer.parseInt(edtChooseShiftEnd.getText().toString())));
                        final Shift shift = new Shift(Integer.toString(month), Integer.toString(dayOfMonth), hours);

                        shifts.child(uidemployee).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                dataSnapshot.getValue(Employee.class).addShift(shift);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        });



    }

}
