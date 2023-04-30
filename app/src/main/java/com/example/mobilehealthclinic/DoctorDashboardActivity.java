package com.example.mobilehealthclinic;

import androidx.appcompat.app.AppCompatActivity;


import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
public class DoctorDashboardActivity extends AppCompatActivity{

    CardView homeCard = findViewById(R.id.homeCard);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsdashboard);

        homeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick", "Clicked Home Card");
                Intent intent = new Intent(DoctorDashboardActivity.this, DoctorsHome.class);
                startActivity(intent);
            }
        });
    }
}
