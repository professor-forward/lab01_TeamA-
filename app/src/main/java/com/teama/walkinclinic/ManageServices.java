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
    ArrayList<Service> servicesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        lvServices = findViewById(R.id.lvServices);
        servicesList = new ArrayList<Service>();



        services.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Service service = ds.getValue(Service.class);
                    servicesList.add(service);

                }
                ArrayAdapter <Service>servicesAdapter = new <Service>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, servicesList);
                lvServices.setAdapter(servicesAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)    {

            }
        });

        lvServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = servicesList.get(position);

                Intent serviceInfoPassToPopUp = new Intent(getApplicationContext(), ManageServicesPopUp.class);
                serviceInfoPassToPopUp.putExtra("service_name",service.getName());
                serviceInfoPassToPopUp.putExtra("service_pay",service.getPay());
                serviceInfoPassToPopUp.putExtra("service_role", service.getRole());

                startActivityForResult(serviceInfoPassToPopUp,1);
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(data.hasExtra("service_name")){
                String serviceName = data.getExtras().getString("service_name");
                Service markedService = servicesList.get(0);

                for(Service service: servicesList){
                    if(service.getName().equals(serviceName)){
                        markedService = service;
                    }
                }
                deleteService(markedService);
            }
        }

    }

    private void deleteService(final Service service){

        services.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    if(service.getName().equals(ds.getValue(Service.class).getName())){
                        ds.getRef().removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        servicesList.remove(service);
        ArrayAdapter<Service> servicesAdapter = new ArrayAdapter<Service>(getApplicationContext(),android.R.layout.simple_list_item_1,servicesList);
        lvServices.clearChoices();
        lvServices.setAdapter(servicesAdapter);
    }
}
