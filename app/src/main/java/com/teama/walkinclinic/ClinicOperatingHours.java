package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;


public class ClinicOperatingHours extends AppCompatActivity {
    AlertDialog adgSaveChanges;
    Button btnSaveClinicInfoChanges;
    Button btnGoToSetMyHours;
    Spinner spOperatingHours;
    int startHourOfClinic;
    int closeHourOfClinic;
    String hoursOperating;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_operating_hours);

        btnSaveClinicInfoChanges = findViewById(R.id.btnSaveClinicInfoChanges);
        btnGoToSetMyHours = findViewById(R.id.btnGoToSetMyHours);

        spOperatingHours = findViewById(R.id.spOperatingHours);
        hoursOperating = spOperatingHours.getSelectedItem().toString();

        adgSaveChanges = new AlertDialog.Builder(ClinicOperatingHours.this).create();

        spOperatingHours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hoursOperating = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        btnSaveClinicInfoChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adgSaveChanges.setTitle("Warning");
                adgSaveChanges.setMessage("If you change the operating hours, it will erase all current employee shifts!");
                adgSaveChanges.setButton(adgSaveChanges.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(hoursOperating.equals("Normal Hours: 8AM - 8PM")){
                            startHourOfClinic = 8;
                            closeHourOfClinic = 20;
                            hoursOperating = "8AM - 8PM";

                        }
                        else if(hoursOperating.equals("Holiday Hours: 10AM - 2PM")){
                            startHourOfClinic = 10;
                            closeHourOfClinic = 14;
                            hoursOperating = "10AM - 2PM";
                        }
                        else{
                            startHourOfClinic = 24;
                            closeHourOfClinic = 24;
                            hoursOperating = "24 hours";
                        }
                        Toast.makeText(getApplicationContext(),"The hours of the clinic have been set to " + hoursOperating, Toast.LENGTH_LONG ).show();
                    }
                });
                adgSaveChanges.setButton(adgSaveChanges.BUTTON_POSITIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                adgSaveChanges.show();
                adgSaveChanges.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);
                adgSaveChanges.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

            }
        });

        btnGoToSetMyHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),WorkDaysScreen.class);
                s.putExtra("clinicStartHours",startHourOfClinic);
                s.putExtra("clinicCloseHours",closeHourOfClinic);
                s.putExtra("uidemployee",uidemployee);
                startActivity(s);
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
/*
Old NumberPicker
 btnSaveClinicInfoChanges = findViewById(R.id.btnSaveClinicInfoChanges);

        import android.widget.NumberPicker;
        NumberPicker npClinicOpeningHours;
        NumberPicker npClinicClosingHours;
        Spinner spinner2;
        Spinner spinner3;
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
 */