package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
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
    DatabaseReference specificClinic;
    CalendarView shiftCalendar;
    EditText edtChooseShiftStart;
    EditText edtChooseShiftEnd;
    Button btnAddShift;
    TextView tvSpecificClinicOperatingHours;
    TextView tvSpecificClinicName;

    Shift shift;

    Clinic clinic;

    String startHours;
    String endHours;
    String hours;

    String specificMonth;
    String specificDay;
    String specificYear;

    int minHours;
    int maxHours;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_days_screen);

        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        final String clinicName = getIntent().getExtras().getString("clinic_name");




        shiftCalendar = findViewById(R.id.cvShifts);
        edtChooseShiftStart = findViewById(R.id.edtChooseShiftStart);
        edtChooseShiftEnd = findViewById(R.id.edtChooseShiftEnd);
        btnAddShift = findViewById(R.id.btnAddShift);
        shiftCalendar.setMinDate(shiftCalendar.getDate());
        tvSpecificClinicOperatingHours = findViewById(R.id.tvSpecificClinicOperatingHours);
        tvSpecificClinicName = findViewById(R.id.tvSpecificClinicName);




        database = FirebaseDatabase.getInstance();
        shifts = database.getReference("Shifts");
        specificClinic = database.getReference("Clinics").child(clinicName);

        specificClinic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clinic = dataSnapshot.getValue(Clinic.class);
                tvSpecificClinicOperatingHours.setText(clinic.getClinicOperatingHours());
                tvSpecificClinicName.setText(clinic.getClinicName());
                minHours = clinic.clinicMinHours();
                maxHours = clinic.clinicMaxHours();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        shiftCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                specificMonth = String.valueOf(month);
                specificDay = String.valueOf(dayOfMonth);
                specificYear = String.valueOf(year);

            }
        });


        btnAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(specificMonth==null || specificDay==null){
                    Toast.makeText(getApplicationContext(),"Please select a Month and Day", Toast.LENGTH_LONG).show();
                    return;
                }



                startHours = edtChooseShiftStart.getText().toString();
                endHours = edtChooseShiftEnd.getText().toString();
                hours = startHours + " to " + endHours;





                shift = new Shift(specificMonth, specificDay, hours, clinicName );



                shifts.child(uidemployee).child(shift.getDate()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().setValue(shift);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });



    }

}
