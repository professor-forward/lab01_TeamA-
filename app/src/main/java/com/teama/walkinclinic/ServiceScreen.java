package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Firebase packages
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference services;


    private EditText edtServiceName;
    private EditText edtServicePay;
    private EditText edtServiceRole;

    private Button btnEditService;
    private Button btnAddService;
    private Button btnViewServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_screen);

        database = FirebaseDatabase.getInstance();
        services = database.getReference("Services");

        edtServiceName = (EditText) findViewById(R.id.edtServiceName);
        edtServicePay = (EditText) findViewById(R.id.edtServicePay);
        edtServiceRole = (EditText) findViewById(R.id.edtServiceRole);

        btnEditService = (Button) findViewById(R.id.btnEditService);
        btnAddService = (Button) findViewById(R.id.btnAddService);


        btnViewServices = (Button) findViewById(R.id.btnViewServices);

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceName = edtServiceName.getText().toString();
                final String servicePay = edtServicePay.getText().toString();
                final String serviceRole = edtServiceRole.getText().toString();

                if(serviceName.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a Service name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(servicePay.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a Service pay", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(serviceRole.trim().matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a Service role", Toast.LENGTH_SHORT).show();
                    return;
                }

                services.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.child(serviceName).exists()) {
                            Service service = new Service(serviceName, servicePay, serviceRole);
                            services.child(serviceName).setValue(service);
                            Toast.makeText(getApplicationContext(),"The Service with name " + serviceName +", role " + serviceRole + " and pay " + servicePay + " has been sucessfully added", Toast.LENGTH_LONG ).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"A service with name, " + serviceName + ", already exists. To edit this service, click 'Edit Service' ", Toast.LENGTH_LONG).show();
                        }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });

        btnEditService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceName = edtServiceName.getText().toString();
                final String servicePay = edtServicePay.getText().toString();
                final String serviceRole = edtServiceRole.getText().toString();
                if(serviceName.trim().matches("")){
                    Toast.makeText(getApplicationContext(),"You did not enter a Service name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(servicePay.matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a Service pay", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(serviceRole.trim().matches("")){
                    Toast.makeText(getApplicationContext(), "You did not enter a Service role", Toast.LENGTH_SHORT).show();
                    return;
                }
                services.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(serviceName).exists()){
                           dataSnapshot.child(serviceName).child("pay").getRef().setValue(servicePay);
                           dataSnapshot.child(serviceName).child("role").getRef().setValue(serviceRole);
                            Toast.makeText(getApplicationContext(),"The Service pay and role of " + serviceName + " has been updated to " + servicePay + " and " + serviceRole, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "A service with name, " + serviceName +", does not exist. To add this service, click 'Add Service' ", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnViewServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(),ManageServices.class);
                startActivity(s);
            }
        });
    }


}
