package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClinicInfoScreen extends AppCompatActivity {

    TimePickerDialog timePickerDialog;
    EditText edtClinicOpeningHours;
    EditText edtClinicClosingHours;
    Button btnGoToSetMyHours;
    Button btnSaveClinicInfoChanges;

    String amOrPm;
    int StartHourOfDay;
    int EndHourOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info_screen);

        btnGoToSetMyHours = findViewById(R.id.btnGoToSetMyHours);
        btnSaveClinicInfoChanges = findViewById(R.id.btnSaveClinicInfoChanges);
        edtClinicOpeningHours = findViewById(R.id.edtClinicOpeningHours);
        edtClinicClosingHours = findViewById(R.id.edtClinicClosingHours);

        edtClinicOpeningHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 timePickerDialog = new TimePickerDialog(ClinicInfoScreen.this, R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                     @Override
                     public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                         StartHourOfDay = hourOfDay;
                         if(hourOfDay>=12){
                             amOrPm = "PM";
                         }
                         else{
                             amOrPm = "AM";
                         }
                         edtClinicOpeningHours.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amOrPm);
                     }
                 },0,0,false);
                 timePickerDialog.show();
            }
        });

        edtClinicClosingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(ClinicInfoScreen.this, R.style.TimePickerTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        EndHourOfDay = hourOfDay;
                        if(hourOfDay>=12){
                            amOrPm = "PM";
                        }
                        else{
                            amOrPm = "AM";
                        }
                        edtClinicClosingHours.setText(String.format("%02d:%02d", hourOfDay, minute) + " " + amOrPm);
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });



        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        btnGoToSetMyHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), WorkDaysScreen.class);
                s.putExtra("uidemployee",uidemployee);
                startActivity(s);

            }
        });

        btnSaveClinicInfoChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Math.abs(EndHourOfDay - StartHourOfDay) <8){
                    Toast.makeText(getApplicationContext(), "The Clinic must be open at least 8 hours", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
