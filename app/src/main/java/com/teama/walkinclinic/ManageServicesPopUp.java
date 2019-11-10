package com.teama.walkinclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;
import android.widget.TextView;

import android.os.Bundle;

public class ManageServicesPopUp extends AppCompatActivity {

    public TextView tvServiceInfo;
    public TextView tvServiceInfo2;
    public TextView tvServiceInfo3;
    private Button btnDeleteService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services_pop_up);

        final String servicesName = getIntent().getExtras().getString("service_name");
        final String servicesPay = getIntent().getExtras().getString("service_pay");
        final String servicesRole = getIntent().getExtras().getString("service_role");

        tvServiceInfo = findViewById(R.id.tvServiceInfo);
        tvServiceInfo2 = findViewById(R.id.tvServiceInfo2);
        tvServiceInfo3 = findViewById(R.id.tvServiceInfo3);
        btnDeleteService = findViewById(R.id.btnDeleteService);

        tvServiceInfo.setText("Service: " + servicesName);
        tvServiceInfo2.setText("Pay: " + servicesPay);
        tvServiceInfo3.setText("Role: " + servicesRole);

        btnDeleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toManageServices = new Intent(getApplicationContext(), ManageServices.class);
                toManageServices.putExtra("service_name", servicesName);
                setResult(RESULT_OK,toManageServices);
                finish();
            }
        });


    }
}
