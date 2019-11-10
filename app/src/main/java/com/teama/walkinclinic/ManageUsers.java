package com.teama.walkinclinic;

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

public class ManageUsers extends AppCompatActivity {

    private DatabaseReference usersDatabase = FirebaseDatabase.getInstance().getReference("Users");
    private ArrayList<User> userList;

    private ListView usersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        usersListView = findViewById(R.id.usersListView);
        userList = new ArrayList<User>();

        usersDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.child("patient").getChildren()){
                    User patient = ds.getValue(Patient.class);
                    userList.add(patient);
                }
                for(DataSnapshot ds: dataSnapshot.child("employee").getChildren()){
                    User employee = ds.getValue(Employee.class);
                    userList.add(employee);
                }


                ArrayAdapter<User> usersAdapter = new <User>ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, userList);
                usersListView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        // this controls the onClick event when an item in the list view is clicked
        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = userList.get(i);
                Log.d("Name",user.toString());

                Intent infoPassToPopUp = new Intent(getApplicationContext(), ManageUsersPopUp.class);
                infoPassToPopUp.putExtra("user_email",user.getEmailAddress());

                /* I am starting the pop up this way so that it can send back information while removing itself from the activity stack. Otherwise,
                I end up with a massive stack of ManageUsers and ManageUsersPopUp activities */
                startActivityForResult(infoPassToPopUp,1);
            }
        });
    }

    protected void onStart()
    {
        super.onStart();

        Log.d("CHECK","CHECK FROM START");

        /*
        usersDatabase.child("patient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("Check","Check from onDataChange");

                userList.clear();

                // fill the userList with all the user accounts on Firebase
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    User user = (User) ds.getValue();
                    userList.add(user);

                    Log.d("User:", user.getEmailAddress());
                }

                // fill the listView with the users from Firebase
                //ArrayAdapter usersAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, userList);
                //usersListView.setAdapter(usersAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // probably an error message should come up here
            }
        });

         */

    }


    // this function recieves information with the pop up class regarding which user to delete
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("check", "check inside onActivityResult");

        if(resultCode == RESULT_OK) {
            if (data.hasExtra("user_email")) {
                String userEmail = data.getExtras().getString("user_email");
                User markedUser = userList.get(0);

                for (User user : userList) {
                    if (user.getEmailAddress().equals(userEmail)) {
                        markedUser = user;
                    }
                }

                deleteUser(markedUser);
            }
        }

    }

    // delete a specific user - FIREBASE Code is needed here
    private void deleteUser(final User user)
    {
        usersDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (user instanceof Patient) {
                    for (DataSnapshot ds : dataSnapshot.child("patient").getChildren()) {
                        if (ds.getValue(Patient.class).getEmailAddress().equals(user.getEmailAddress())) {
                            ds.getRef().removeValue();
                        }
                    }
                } else if (user instanceof Employee) {
                    for (DataSnapshot ds : dataSnapshot.child("employee").getChildren()) {
                        if (ds.getValue(Employee.class).getEmailAddress().equals(user.getEmailAddress())) {
                            ds.getRef().removeValue();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // firebase code goes here for deleting user
        Log.d("check", "User: " + user.getEmailAddress());

        userList.remove(user);
        ArrayAdapter usersAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, userList);
        usersListView.clearChoices();
        usersListView.setAdapter(usersAdapter);


        Toast.makeText(getApplicationContext(), "Deleted User: " + user.getEmailAddress(),Toast.LENGTH_LONG);
    }
}
