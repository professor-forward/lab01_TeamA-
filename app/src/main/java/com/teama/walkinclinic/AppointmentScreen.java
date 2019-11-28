package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

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
import java.util.ArrayList;


public class AppointmentScreen extends AppCompatActivity {

    String clinicName;
    String clinicAppointmentTotalHours;

    Spinner spCustomAppointmentHours;
    Spinner spAppointmentQuarter;
    TextView tvAppointment;
    TextView tvPickQuarter;
    TextView tvPickHour;
    Button btnSaveAppointmentTime;


    FirebaseDatabase database;
    DatabaseReference appointmentclinics;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_screen);

         database = FirebaseDatabase.getInstance();
         appointmentclinics = database.getReference("Clinics");

         final String[] regularHours = new String[]{"8AM","9AM","10AM","11AM","12PM","1PM","2PM","3PM","4PM","5PM"};
         final String[] specialtyHours = new String[]{"10AM","11AM","12PM","1PM","2PM","3PM","4PM"};
         final String[] emergencyHours = new String[]{"will die soon", "already dead", "not close to death"};
         spCustomAppointmentHours = findViewById(R.id.spCustomAppointmentHours);
         spAppointmentQuarter = findViewById(R.id.spAppointmentQuarter);
         tvAppointment = findViewById(R.id.tvAppointment);
         tvPickQuarter = findViewById(R.id.tvPickQuarter);
         tvPickHour = findViewById(R.id.tvPickHour);
         btnSaveAppointmentTime = findViewById(R.id.btnSaveAppointmentTime);

         clinicName = getIntent().getStringExtra(ClinicOptionsScreen.CLINICNAMEKEY);
         appointmentclinics.child(clinicName).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 clinicAppointmentTotalHours = (String)dataSnapshot.child("clinicOperatingHours").getValue();
                 tvAppointment.setText(clinicName + " Book Appointment");

                 if(clinicAppointmentTotalHours.equals("Regular Clinic Hours: 8AM - 8PM")){
                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item
                     , regularHours);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     spCustomAppointmentHours.setAdapter(adapter);
                 }
                 else if(clinicAppointmentTotalHours.equals("Specialty Clinic Hours: 10AM - 2PM")){
                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item
                             , specialtyHours);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     spCustomAppointmentHours.setAdapter(adapter);
                 }
                 else{
                     ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item
                             , emergencyHours);
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     spCustomAppointmentHours.setAdapter(adapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });



    }
}
