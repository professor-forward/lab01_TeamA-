package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.EditText;


import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CheckBox;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ClinicInfoScreen extends AppCompatActivity {

    Clinic clinic;

    Button btnSaveClinicInfo;
    Button btnViewAllClinic;
    Button btnUpdateClinicInfo;
    AlertDialog adgUpdateChanges;
    Spinner spOperatingHours;
    int startHourOfClinic;
    int closeHourOfClinic;

    EditText edtClinicName;
    EditText edtClinicAddress;
    EditText edtClinicPhoneNumber;

    CheckBox cbDebitCard;
    CheckBox cbCreditCard;
    CheckBox cbBitcoin;

    FirebaseDatabase database;
    DatabaseReference clinics;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_info_screen);

        final String uidemployee = getIntent().getExtras().getString("uidemployee");

        database = FirebaseDatabase.getInstance();
        clinics = database.getReference("Clinics");

        btnSaveClinicInfo = findViewById(R.id.btnSaveClinicInfo);
        btnViewAllClinic = findViewById(R.id.btnViewAllClinic);
        btnUpdateClinicInfo = findViewById(R.id.btnUpdateClinicInfo);

        adgUpdateChanges = new AlertDialog.Builder(ClinicInfoScreen.this).create();


        edtClinicName = findViewById((R.id.edtClinicName));

        edtClinicAddress = findViewById(R.id.edtClinicAddress);

        edtClinicPhoneNumber = findViewById(R.id.edtClinicPhoneNumber);


        cbDebitCard = findViewById(R.id.cbDebitCard1);

        cbCreditCard = findViewById(R.id.cbCreditCard1);

        cbBitcoin = findViewById(R.id.cbBitcoin1);

        spOperatingHours = findViewById(R.id.spOperatingHours2);



        btnSaveClinicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String clinicName = edtClinicName.getText().toString();
                if(clinicName.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a Clinic name", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String clinicAddress = edtClinicAddress.getText().toString();
                if(clinicAddress.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a valid Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String clinicPhoneNumber = edtClinicPhoneNumber.getText().toString();
                if(clinicPhoneNumber.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String hoursOperating = spOperatingHours.getSelectedItem().toString();
                boolean debitCard = cbDebitCard.isChecked();
                boolean creditCard = cbCreditCard.isChecked();
                boolean bitcoin = cbBitcoin.isChecked();
                if(bitcoin==false&&debitCard==false&&creditCard==false){
                    Toast.makeText(getApplicationContext(),"You did not set a payment type", Toast.LENGTH_SHORT).show();
                    return;
                }


                clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber, hoursOperating,debitCard,creditCard,bitcoin );
                clinics.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.child(clinicName).getRef().setValue(clinic);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btnViewAllClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ManageClinics.class);
                startActivity(s);
            }
        });

        btnUpdateClinicInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String clinicName = edtClinicName.getText().toString();
                if(clinicName.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a Clinic name", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String clinicAddress = edtClinicAddress.getText().toString();
                final String clinicPhoneNumber = edtClinicPhoneNumber.getText().toString();
                final String hoursOperating = spOperatingHours.getSelectedItem().toString();


                final boolean debitCard = cbDebitCard.isChecked();
                final boolean creditCard = cbCreditCard.isChecked();
                final boolean bitcoin = cbBitcoin.isChecked();

                if(bitcoin==false&&debitCard==false&&creditCard==false){
                    Toast.makeText(getApplicationContext(),"You need to set a payment type", Toast.LENGTH_SHORT).show();
                    return;
                }


                clinics.child(edtClinicName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(),"That Clinic has not been created yet", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(dataSnapshot.child("clinicOperatingHours").getValue()!=hoursOperating){
                            //Delete the time
                            FirebaseDatabase.getInstance().getReference("Shifts").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                                        for(DataSnapshot ds2: ds.getChildren()){
                                            if(ds2.getValue(Shift.class).getClinicName().equals(edtClinicName.getText().toString())){
                                                ds2.getRef().removeValue();
                                            }
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        if(clinicAddress.equals("")){
                        dataSnapshot.child("clinicAddress").getRef().setValue(clinicAddress);}
                        dataSnapshot.child("clinicOperatingHours").getRef().setValue(hoursOperating);
                        if(clinicPhoneNumber.equals("")){
                        dataSnapshot.child("phoneNumber").getRef().setValue(clinicPhoneNumber);}
                        dataSnapshot.child("clinicBitcoin").getRef().setValue(bitcoin);
                        dataSnapshot.child("clinicCredit").getRef().setValue(creditCard);
                        dataSnapshot.child("clinicDebit").getRef().setValue(debitCard);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });









    }
}

