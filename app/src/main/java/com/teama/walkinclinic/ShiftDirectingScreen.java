package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ShiftDirectingScreen extends AppCompatActivity {

    Button btnToAddShift;
    Button btnToViewAllMyShifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_directing_screen);

        final String uidemployee = getIntent().getExtras().getString("uidemployee");
        btnToAddShift = findViewById(R.id.btnToAddShift);
        btnToViewAllMyShifts = findViewById(R.id.btnToViewAllMyShifts);


        btnToAddShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent s = new Intent(getApplicationContext(), PickAClinicScreen.class);
                s.putExtra("uidemployee",uidemployee);
                startActivity(s);

            }
        });

        btnToViewAllMyShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s = new Intent(getApplicationContext(), ManageShiftsScreen.class);
                s.putExtra("uidemployee", uidemployee);
                startActivity(s);
            }
        });
    }
}
