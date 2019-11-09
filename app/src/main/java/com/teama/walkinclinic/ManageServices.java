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

public class ManageServices extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference services = database.getInstance().getReference().child("Services");


    private ListView lvServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        lvServices = findViewById(R.id.lvServices);



        services.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> servicesList = new ArrayList<String>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Service service = ds.getValue(Service.class);
                    servicesList.add(service.getName());

                }
                ArrayAdapter <String>servicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, servicesList);
                lvServices.setAdapter(servicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
