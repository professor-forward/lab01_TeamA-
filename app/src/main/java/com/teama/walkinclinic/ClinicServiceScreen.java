package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ClinicServiceScreen extends AppCompatActivity {


    private DatabaseReference activeServicesRef;
    private DatabaseReference availableServicesRef;


    private ListView activeServicesListView;
    private ListView availableServicesListView;
    private Button saveButton;

    private ArrayList<Service> activeServices;
    private ArrayList<Service> availableServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_service_screen);

        final String clinicName = getIntent().getExtras().getString("clinic_name");

        activeServicesRef = FirebaseDatabase.getInstance().getReference("Clinics").child(clinicName).child("ActiveServices");
        availableServicesRef = FirebaseDatabase.getInstance().getReference("Services");

        activeServicesListView = findViewById(R.id.activeServicesListView);
        availableServicesListView = findViewById(R.id.availableServicesListView);
        saveButton = findViewById(R.id.saveButton);

        activeServices = new ArrayList<Service>();
        availableServices = new ArrayList<Service>();


        // test code starts here ------------------------------------
        /*
        for(int i = 0; i < 10; i++)
        {
            activeServices.add(new Service("ActiveService " + i, "$i","Role " + i));
            availableServices.add(new Service("AvailableService " + i, "$i", "Role " + i));
        }

        ArrayAdapter<String> activeServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, activeServices);
        activeServicesListView.setAdapter(activeServicesAdapter);

        ArrayAdapter<String> availableServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, availableServices);
        availableServicesListView.setAdapter(availableServicesAdapter);

        // test code ends here --------------------------------------
        */

        // controls button clicks on the active services list view
        activeServicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = activeServices.get(i);
                removeService(service);
            }
        });

        // controls button clicks on the available services list view
        availableServicesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("check","clicking available services");
                Service service = availableServices.get(i);
                addService(service);
            }
        });

        // updates FireBase with the employee's chosen clinic services
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // add active services to firebase
                activeServicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        // remove current active clinic services
                        activeServicesRef.getRef().removeValue();

                        /* rebuild ActiveServices node
                        activeServicesRef = FirebaseDatabase.getInstance().getReference("ActiveServices");
                        */
                        for(Service s : activeServices)
                        {
                            activeServicesRef.child(s.getName()).setValue(s);
                        }

                        Toast.makeText(getApplicationContext(),"Saved Services", Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // this one gets the currently active services of the clinic
        activeServicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                activeServices.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Service activeService = ds.getValue(Service.class);
                    activeServices.add(activeService);
                }

                ArrayAdapter<String> activeServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, activeServices);
                activeServicesListView.setAdapter(activeServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        // this one gets all possible services that the clinic could provide
        availableServicesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                availableServices.clear();

                // get available services
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Service availableService = ds.getValue(Service.class);

                    // if active services has this service then it is no longer available, so don't add it to the available services list
                    boolean inActiveServices = false;

                    for(Service s : activeServices)
                    {
                        // check if the available service is already in the activeServices list
                        if(availableService.equals(s))
                        {
                            inActiveServices = true;
                        }
                    }

                    //if not in activeServices then add to availableServices
                    if(inActiveServices == false)
                    {
                        availableServices.add(availableService);
                    }
                }

                ArrayAdapter<String> availableServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, availableServices);
                availableServicesListView.setAdapter(availableServicesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // this function deletes services from the available services list view and places them in the active services list view
    private void addService(Service service)
    {
        if(service == null)
        {
            return;
        }

        Log.d("CHECK","Service : " + service);

        boolean isFound = false;
        Service markedService = availableServices.get(0);

        for(Service s : availableServices)
        {
            Log.d("CHECK","Check service in availableServices: " + s);

            if (service.equals(s))
            {
                Log.d("check", "matching service found");
                isFound = true;
                markedService = s;
                break;
            }
        }

        if(isFound)
        {
            availableServices.remove(markedService);
            activeServices.add(markedService);
        }
        else
        {
            return;
        }

        // update listViews
        ArrayAdapter<String> activeServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, activeServices);
        activeServicesListView.setAdapter(activeServicesAdapter);
        ArrayAdapter<String> availableServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, availableServices);
        availableServicesListView.setAdapter(availableServicesAdapter);
    }

    // this function deletes services from the active services list view and places them in the available list view
    private void removeService(Service service)
    {
        if(service == null)
        {
            return;
        }

        boolean isFound = false;
        Service markedService = activeServices.get(0);

        for(Service s : activeServices)
        {
            if (service.equals(s))
            {
                isFound = true;
                markedService = s;
                break;
            }
        }

        if(isFound)
        {
            activeServices.remove(markedService);
            availableServices.add(markedService);
        }
        else
        {
            return;
        }

        // update listViews
        ArrayAdapter<String> activeServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, activeServices);
        activeServicesListView.setAdapter(activeServicesAdapter);
        ArrayAdapter<String> availableServicesAdapter = new <String>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, availableServices);
        availableServicesListView.setAdapter(availableServicesAdapter);

    }
}
