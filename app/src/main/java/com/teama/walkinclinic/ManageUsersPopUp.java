package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

public class ManageUsersPopUp extends AppCompatActivity {

    public TextView userTextField;
    private Button deleteUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users_pop_up);

        final String userEmail = getIntent().getExtras().getString("user_email");

        userTextField = findViewById(R.id.userTextField);
        userTextField.setText("User: " + userEmail);

        deleteUserButton = findViewById(R.id.deleteUserButton);

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // tell the manage users class to delete the user of said email address

                Intent toManageUsers = new Intent(getApplicationContext(),ManageUsers.class);
                toManageUsers.putExtra("user_email", userEmail);
                //toManageUsers.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setResult(RESULT_OK,toManageUsers);
                finish();



            }
        });


    }
}
