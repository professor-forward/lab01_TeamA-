package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClinicOptionsScreen extends AppCompatActivity {

    private Button appointmentBtn;
    private Button reviewBtn;
    String clinicName;
    public static String CLINICNAMEKEY = "ClinicName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_options_screen);

        appointmentBtn = findViewById(R.id.btnToAppointmentScreen);
        reviewBtn = findViewById(R.id.btnToReviewScreen);
        final String uidpatient = getIntent().getStringExtra("uidpatient");

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinicName = getIntent().getStringExtra(SelectClinicScreen.CLINIC_NAME_EXTRA_KEY);
                Intent s = new Intent(getApplicationContext(), AppointmentScreen.class);
                s.putExtra(CLINICNAMEKEY, clinicName);
                s.putExtra("uidpatient",uidpatient);
                startActivity(s);
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clinicName = getIntent().getStringExtra(SelectClinicScreen.CLINIC_NAME_EXTRA_KEY);
                Intent s = new Intent(getApplicationContext(), ReviewScreen.class);
                s.putExtra(CLINICNAMEKEY, clinicName);
                startActivity(s);
            }
        });
    }
}
