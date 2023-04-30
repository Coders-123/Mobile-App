package com.example.mobilehealthclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobilehealthclinic.fireStoreApi.DoctorHelper;
import com.example.mobilehealthclinic.fireStoreApi.PatientHelper;
import com.example.mobilehealthclinic.fireStoreApi.UserHelper;

import static android.widget.AdapterView.*;
public class FirstSigninActivity extends AppCompatActivity {
    private static final String TAG = "FirstSigninActivity";
    private EditText fullName;
    private EditText address;
    private EditText cell;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_signin);
        btn = findViewById(R.id.confirmeBtn);
        fullName = findViewById(R.id.firstSignFullName);
        address = findViewById(R.id.firstSignAddress);
        cell = findViewById(R.id.firstSignCell);

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner specialityList = findViewById(R.id.speciality_spinner);
        ArrayAdapter<CharSequence> adapterSpecialityList = ArrayAdapter.createFromResource(this,
                R.array.speciality_spinner, android.R.layout.simple_spinner_item);
        adapterSpecialityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialityList.setAdapter(adapterSpecialityList);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                Log.e(TAG, "onItemSelected:" + selected);
                if (selected.equals("Doctor")) {
                    specialityList.setVisibility(View.VISIBLE);
                } else {
                    specialityList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                specialityList.setVisibility(View.GONE);
            }
        });
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullName.getText().toString();
                String addRess = address.getText().toString();
                String celL = cell.getText().toString();
                String speciality = specialityList.getSelectedItem().toString();

                String type = spinner.getSelectedItem().toString();

                if (type.equals("Patient")) {
                    PatientHelper.addPatient(fullname, addRess, celL);
                    UserHelper.addUser(fullname, addRess, celL, "Patient");
                    Intent intent = new Intent(FirstSigninActivity.this, PatientDashboardActivity.class);
                    startActivity(intent);
                } else {
                    DoctorHelper.addDoctor(fullname, addRess, celL, speciality);
                    UserHelper.addUser(fullname, addRess, celL, "Doctor");
                    Intent intent = new Intent(FirstSigninActivity.this, DoctorDashboardActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
