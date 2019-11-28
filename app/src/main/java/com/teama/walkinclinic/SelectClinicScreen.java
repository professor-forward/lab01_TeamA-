package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectClinicScreen extends AppCompatActivity {

    public static String CLINIC_NAME_EXTRA_KEY = "ClinicName";

    private DatabaseReference clinicsRef;
    private ListView clinicListView;
    private ArrayList<Clinic> clinicList;
    private Clinic firebaseClinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_clinic_screen);

        clinicsRef = FirebaseDatabase.getInstance().getReference("Clinics");
        clinicListView = findViewById(R.id.listOfClinics);
        clinicList = new ArrayList<Clinic>();

        final String uidpatient = getIntent().getStringExtra("uidpatient");

        clinicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // go to clinic options screen with the name of the patient's chosen clinic as an extra
                String clinicName = clinicList.get(i).getClinicName();

                Intent toClinicOptions = new Intent(getApplicationContext(),ClinicOptionsScreen.class);
                toClinicOptions.putExtra(CLINIC_NAME_EXTRA_KEY,clinicName);
                toClinicOptions.putExtra("uidpatient",uidpatient);
                startActivity(toClinicOptions);
            }
        });

    }

    protected void onStart()
    {
        super.onStart();

        final String patientServiceName = getIntent().getStringExtra(SelectServiceScreen.SERVICE_NAME_EXTRA_KEY);
        Log.d("CHECK EXTRA",patientServiceName);

        clinicsRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // clear the clinicList before adding anything
                clinicList.clear();

                // iterate through each clinic
                for(DataSnapshot ds1 : dataSnapshot.getChildren())
                {
                    // get the clinic
                    firebaseClinic = ds1.getValue(Clinic.class);
                    Log.d("CHECK","FROM CLINICS LOOP");
                    Log.d("CHECK","Current Clinic: " + firebaseClinic.getClinicName());

                    DataSnapshot activeServicesDS = ds1.child("ActiveServices");

                    /* iterate through ActiveServices and check if the patient's chosen service is in this clinic. If so, add this clinic to the
                       clinic list. */

                    for(DataSnapshot ds2 : activeServicesDS.getChildren())
                    {
                        Log.d("CHECK","FROM SERVICES LOOP");
                        Service service = ds2.getValue(Service.class);

                        if(service.getName().equals(patientServiceName))
                        {
                            clinicList.add(firebaseClinic);
                            Log.d("CHECK","Service Name: " + service.getName());
                            break;
                        }
                    }

                }

                // set the clinic list view
                ArrayAdapter<String> clinicAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, clinicList);
                clinicListView.setAdapter(clinicAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
