package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class SelectServiceScreen extends AppCompatActivity {

    private ListView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service_screen);

        serviceList = findViewById(R.id.listOfServices);

        serviceList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), SelectClinicScreen.class);
                startActivity(s);
            }
        });
    }
}
