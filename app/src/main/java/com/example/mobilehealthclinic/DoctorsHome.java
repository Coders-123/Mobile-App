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

public class DoctorsHome extends AppCompatActivity {
    TextView userName;

    private ImageView doctorImage;
    Button logout;
    GoogleSignInClient gClient;
    GoogleSignInOptions gOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorshome);

        ImageSlider imageSlider= findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        doctorImage.setImageResource(R.drawable.doctorshome);

        slideModels.add(new SlideModel(R.drawable.stevebiko, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.isolempilo1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.vaccination, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.bloodtest, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.femalehealth, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.hivtest, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        logout = findViewById(R.id.logout);
        userName = findViewById(R.id.userName);
        doctorImage = findViewById(R.id.DoctorImage);

        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);


        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null){
            String gName = gAccount.getDisplayName();
            userName.setText(gName);
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userType = user.getMetadata().getCreationTimestamp() > user.getMetadata().getLastSignInTimestamp() ? "Patient" : "Doctor";
            if (userType.equals("Patient")) {
                startActivity(new Intent(DoctorsHome.this, PatientDashboardActivity.class));
                finish();
            } else if (userType.equals("Doctor")) {
                startActivity(new Intent(DoctorsHome.this, DoctorDashboardActivity.class));
                finish();
            }
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(DoctorsHome.this, LoginActivity.class));
                    }
                });
            }
        });
    }
}

