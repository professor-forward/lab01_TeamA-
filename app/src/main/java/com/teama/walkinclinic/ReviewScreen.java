package com.teama.walkinclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference clinics;
    Clinic fireBaseClinic;

    RatingBar review;
    TextView clientComment;
    ArrayList<Review> reviewsList;

    private String clinicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_screen);

        final RatingBar starBar = (RatingBar) findViewById(R.id.ratingBar);
        Button submitButton = (Button) findViewById(R.id.submitBtn);
        final TextView ratingText  = (TextView) findViewById(R.id.ratingTextView);

        database = FirebaseDatabase.getInstance();
        clinics = database.getReference("Clinics");

        review = (RatingBar)findViewById(R.id.ratingBar);
        clientComment = findViewById(R.id.commentBox);
        reviewsList = new ArrayList<Review>();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingText.setText("You Selected " + starBar.getRating());

                clinics.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        clinicName = getIntent().getStringExtra(ClinicOptionsScreen.CLINICNAMEKEY);
                        for (DataSnapshot D1: dataSnapshot.getChildren())
                        {
                            fireBaseClinic = D1.getValue(Clinic.class);
                            if(fireBaseClinic.getClinicName().equals(clinicName))
                            {
                                DatabaseReference reviewRef = D1.child("Reviews").getRef();
                                String uid = reviewRef.push().getKey();

                                Review newReview = new Review(review.getRating(), clientComment.getText().toString(), uid);

                                reviewRef.child(uid).setValue(newReview);
                            }
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
