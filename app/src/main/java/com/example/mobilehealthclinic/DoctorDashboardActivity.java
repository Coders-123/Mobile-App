package com.example.mobilehealthclinic;

import androidx.appcompat.app.AppCompatActivity;


import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class DoctorDashboardActivity extends AppCompatActivity{

    CardView appointmentsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsdashboard);
    }
}
