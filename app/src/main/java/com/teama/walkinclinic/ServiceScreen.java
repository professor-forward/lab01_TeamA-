package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Firebase packages
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ServiceScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference services;

    private EditText edtServiceID;
    private EditText edtServiceName;
    private EditText edtServicePay;

    private Button btnFindService;
    private Button btnAddService;
    private Button btnDeleteService;
    private Button btnViewServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_screen);

        database = FirebaseDatabase.getInstance();
        services = database.getReference("Services");

        edtServiceID = (EditText) findViewById(R.id.edtServiceID);
        edtServiceName = (EditText) findViewById(R.id.edtServiceName);
        edtServicePay = (EditText) findViewById(R.id.edtServicePay);

        btnFindService = (Button) findViewById(R.id.btnFindService);
        btnAddService = (Button) findViewById(R.id.btnAddService);
        btnDeleteService = (Button) findViewById(R.id.btnDeleteService);

        btnViewServices = (Button) findViewById(R.id.btnViewServices);

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceID = edtServiceID.getText().toString();
                final String serviceName = edtServiceName.getText().toString();
                final String servicePay = edtServicePay.getText().toString();

                services.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            if(!(serviceID.equals(ds.getValue(Service.class).getID()) || serviceName.equals(ds.getValue(Service.class).getName()) )){
                                Service service = new Service(serviceID,serviceName,servicePay);
                                services.child(serviceID).setValue(service);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });
        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceID = edtServiceID.getText().toString();
                services.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(serviceID).exists()){
                            dataSnapshot.child(serviceID).getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });

        btnFindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceID = edtServiceID.getText().toString();
                services.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(serviceID).exists()){
                           Service service = dataSnapshot.child(serviceID).getValue(Service.class);
                            edtServiceName.setText(service.getName());
                            edtServicePay.setText(service.getPay());
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