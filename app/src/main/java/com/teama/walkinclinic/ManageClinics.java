package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManageClinics extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference clinics;
    ArrayList<Clinic> clinicsList;
    ListView lvClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_clinics);

        database = FirebaseDatabase.getInstance();
        clinics = database.getReference("Clinics");

        lvClinics = findViewById(R.id.lvClinics);
        clinicsList = new ArrayList<Clinic>();

        clinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Clinic clinic = ds.getValue(Clinic.class);
                    clinicsList.add(clinic);

                }
                ArrayAdapter <Clinic>clinicsAdapter = new ArrayAdapter<Clinic>(getApplicationContext(), android.R.layout.simple_list_item_1, clinicsList);
                lvClinics.setAdapter(clinicsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvClinics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Clinic clinic = clinicsList.get(i);

                Intent s = new Intent(getApplicationContext(), ClinicServiceScreen.class);
                s.putExtra("clinic_name",clinic.getClinicName());

                /* I am starting the pop up this way so that it can send back information while removing itself from the activity stack. Otherwise,
                I end up with a massive stack of ManageUsers and ManageUsersPopUp activities */
                //startActivityForResult(s,1);
                startActivity(s);
            }
        });

    }
}
