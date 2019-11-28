package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectServiceScreen extends AppCompatActivity {

    public static String SERVICE_NAME_EXTRA_KEY = "ServiceName";

    private DatabaseReference availableServicesRef;
    private ListView serviceListView;
    private ArrayList<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service_screen);

        availableServicesRef = FirebaseDatabase.getInstance().getReference("Services");

        serviceListView = findViewById(R.id.listOfServices);
        serviceList = new ArrayList<Service>();

        final String uidpatient = getIntent().getStringExtra("uidpatient");

        serviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String serviceName = serviceList.get(i).getName();

                // go to the select clinic screen and pass the selected service as an extra with the intent object
                Intent toSelectClinics = new Intent(getApplicationContext(),SelectClinicScreen.class);
                toSelectClinics.putExtra(SERVICE_NAME_EXTRA_KEY,serviceName);
                toSelectClinics.putExtra("uidpatient",uidpatient);
                startActivity(toSelectClinics);
            }
        });

    }

    protected void onStart()
    {
        super.onStart();

        availableServicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /* clear the list because this activity remains in the stack after loading selectClinicScreen. Thus, this activity's
                   contents are saved as well. */

                serviceList.clear();

                // get the list of available services defined by the administrator from FireBase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Service service = ds.getValue(Service.class);
                    serviceList.add(service);
                }

                // set the serviceList to the serviceListView
                ArrayAdapter<String> serviceAdapter = new <String>ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,serviceList);
                serviceListView.setAdapter(serviceAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
