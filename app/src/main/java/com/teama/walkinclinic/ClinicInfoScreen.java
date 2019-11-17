package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;


import android.widget.Toast;



public class ClinicInfoScreen extends AppCompatActivity {



    Button btnGoToSetClinicHours;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info_screen);

        btnGoToSetClinicHours = findViewById(R.id.btnGoToSetClinicHours);





        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        btnGoToSetClinicHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ClinicOperatingHours.class);
                s.putExtra("uidemployee",uidemployee);
                startActivity(s);

            }
        });

        







    }
}

