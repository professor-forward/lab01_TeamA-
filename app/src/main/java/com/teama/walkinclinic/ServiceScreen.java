package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    private Button btnEditService;
    private Button btnAddService;
    private Button btnDeleteService;

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
        btnEditService = (Button) findViewById(R.id.btnEditService);
        btnAddService = (Button) findViewById(R.id.btnAddService);
        btnDeleteService = (Button) findViewById(R.id.btnDeleteService);

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceID = edtServiceID.getText().toString();
                String serviceName = edtServiceName.getText().toString();
                String servicePay = edtServicePay.getText().toString();

                Service service = new Service(serviceID,serviceName,servicePay);

                services.child(serviceID).setValue(service);

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
    }


}
