package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class ClinicOperatingHours extends AppCompatActivity {
    Button btnSaveClinicInfoChanges;
    int startHourOfClinic;
    int closeHourOfClinic;
    NumberPicker npClinicOpeningHours;
    NumberPicker npClinicClosingHours;
    Spinner spinner2;
    Spinner spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_operating_hours);

        btnSaveClinicInfoChanges = findViewById(R.id.btnSaveClinicInfoChanges);


        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        npClinicOpeningHours = findViewById(R.id.npClinicOpeningHours);
        npClinicClosingHours = findViewById(R.id.npClinicClosingHours);

        //Populate opening/closing with 1-12
        npClinicOpeningHours.setMinValue(1);
        npClinicOpeningHours.setMaxValue(12);
        npClinicClosingHours.setMinValue(1);
        npClinicClosingHours.setMaxValue(12);
        //wrap when reach max
        npClinicOpeningHours.setWrapSelectorWheel(true);
        npClinicClosingHours.setWrapSelectorWheel(true);

        npClinicOpeningHours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    startHourOfClinic = newVal;
            }
        });
        npClinicClosingHours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    closeHourOfClinic = newVal;
            }
        });

        btnSaveClinicInfoChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int timeIntervalEnd = startHourOfClinic;
                int timeIntervalStart = closeHourOfClinic;
                if(spinner2.getSelectedItem().toString().equals("PM")){timeIntervalStart+=10;}
                if(spinner3.getSelectedItem().toString().equals("PM")){timeIntervalEnd+=10;}
                if(Math.abs(timeIntervalEnd-timeIntervalStart) <8){
                    Toast.makeText(getApplicationContext(), "The Clinic must be open at least 8 hours", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "The Clinic hours have been updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

/*
Old TimePickerDialog

TimePickerDialog timePickerDialog;
    import android.widget.TimePicker;
    import android.app.TimePickerDialog;
    String amOrPm;

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


*/
