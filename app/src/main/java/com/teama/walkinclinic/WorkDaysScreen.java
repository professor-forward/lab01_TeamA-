package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    Spinner spinnerFirstAmOrPm;
    Spinner spinnerSecondAmOrPm;

    Shift shift;

    Clinic clinic;



    String hours;

    String specificMonth;
    String specificDay;
    String specificYear;








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
        spinnerFirstAmOrPm = findViewById(R.id.spinnerFirstAmOrPm);
        spinnerSecondAmOrPm = findViewById(R.id.spinnerSecondAmOrPm);




        database = FirebaseDatabase.getInstance();
        shifts = database.getReference("Shifts");
        specificClinic = database.getReference("Clinics").child(clinicName);

        specificClinic.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clinic = dataSnapshot.getValue(Clinic.class);
                hours = clinic.getClinicOperatingHours();
                tvSpecificClinicOperatingHours.setText(clinic.getClinicOperatingHours());
                tvSpecificClinicName.setText(clinic.getClinicName());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        shiftCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                specificMonth = String.valueOf(month+1);
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

                if(edtChooseShiftStart.getText().toString().equals("") || edtChooseShiftEnd.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please select operating hours", Toast.LENGTH_LONG).show();
                    return;
                }





                if(hours.equals("Regular Clinic Hours: 8AM - 8PM")){
                    int startHours = Integer.valueOf(edtChooseShiftStart.getText().toString());
                    int endHours = Integer.valueOf(edtChooseShiftEnd.getText().toString());
                    final String startAmOrPm = spinnerFirstAmOrPm.getSelectedItem().toString();
                    final String endAmOrPm = spinnerSecondAmOrPm.getSelectedItem().toString();
                    if(startAmOrPm.equals("pm") && startHours<12){startHours=startHours+12;}
                    if(endAmOrPm.equals("pm") && endHours<12){endHours=endHours+12;}
                    if(endHours-startHours<0 || startHours<8 || endHours>20){
                        Toast.makeText(getApplicationContext(),"Regular clinic hours is not open in this hours", Toast.LENGTH_LONG).show();
                        return;
                    }
                    hours = "Working Regular Clinic Hours: " + edtChooseShiftStart.getText().toString() + startAmOrPm +" to " + edtChooseShiftEnd.getText().toString() + endAmOrPm;
                }

                else if (hours.equals("Specialty Clinic Hours: 10AM - 2PM")){
                    int startHours = Integer.valueOf(edtChooseShiftStart.getText().toString());
                    int endHours = Integer.valueOf(edtChooseShiftEnd.getText().toString());
                    final String startAmOrPm = spinnerFirstAmOrPm.getSelectedItem().toString();
                    final String endAmOrPm = spinnerSecondAmOrPm.getSelectedItem().toString();
                    if(startAmOrPm.equals("pm") && startHours<12){startHours=startHours+12;}
                    if(endAmOrPm.equals("pm") && endHours<12){endHours=endHours+12;}
                    if(endHours-startHours<0 || startHours<10 || endHours>14){
                        Toast.makeText(getApplicationContext(),"Speciality clinic hours is not open in this hours", Toast.LENGTH_LONG).show();
                        return;
                    }
                    hours = "Working Speciality Clinic Hours: " + edtChooseShiftStart.getText().toString() + startAmOrPm +" to " + edtChooseShiftEnd.getText().toString() + endAmOrPm;
                }

                else if(hours.equals("Emergency Clinic Hours: Open 24 hours")){
                    int startHours = Integer.valueOf(edtChooseShiftStart.getText().toString());
                    int endHours = Integer.valueOf(edtChooseShiftEnd.getText().toString());
                    final String startAmOrPm = spinnerFirstAmOrPm.getSelectedItem().toString();
                    final String endAmOrPm = spinnerSecondAmOrPm.getSelectedItem().toString();
                    if(startHours>12 || endHours>12 || startHours<0 || endHours<0){
                        Toast.makeText(getApplicationContext(),"Incorrect time for emergency clinic", Toast.LENGTH_LONG).show();
                        return;
                    }
                    hours = "Working Emergency Clinic Hours: " + edtChooseShiftStart.getText().toString() + startAmOrPm +" to " + edtChooseShiftEnd.getText().toString() + endAmOrPm;
                }




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
