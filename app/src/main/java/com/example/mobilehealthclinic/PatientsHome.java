package com.example.mobilehealthclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class PatientsHome extends AppCompatActivity{

    private ImageView PatientImage;
    Button logout;
    GoogleSignInClient gClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientshome);

        ImageSlider imageSlider= findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.stevebiko, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.isolempilo1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.vaccination, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.bloodtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.femalehealth, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hivtest, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        logout = findViewById(R.id.logout);
        PatientImage = findViewById(R.id.PatientImage);

        PatientImage.setImageResource(R.drawable.patienthome);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(PatientsHome.this, LoginActivity.class));
                    }
                });
            }
        });
    }

}
