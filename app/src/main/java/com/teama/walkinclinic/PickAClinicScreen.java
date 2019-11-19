package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class PickAClinicScreen extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference activeClinics;
    ArrayList<Clinic> activeClinicsList;
    ListView lvActiveClinics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_aclinic_screen);

        final String uidemployee = getIntent().getExtras().getString("uidemployee");

        database = FirebaseDatabase.getInstance();
        activeClinics = database.getReference("Clinics");

        lvActiveClinics = findViewById(R.id.lvActiveClinics);
        activeClinicsList = new ArrayList<Clinic>();

        activeClinics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Clinic clinic = ds.getValue(Clinic.class);
                    activeClinicsList.add(clinic);
                }

                ArrayAdapter<Clinic> activeClinicsAdapter = new ArrayAdapter<Clinic>(getApplicationContext(), android.R.layout.simple_list_item_1, activeClinicsList);
                lvActiveClinics.setAdapter(activeClinicsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvActiveClinics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Clinic clinic = activeClinicsList.get(i);

                Intent s = new Intent(getApplicationContext(), WorkDaysScreen.class);
                s.putExtra("uidemployee",uidemployee);
                s.putExtra("clinic_name", clinic.getClinicName());
                startActivity(s);
            }
        });
    }
}