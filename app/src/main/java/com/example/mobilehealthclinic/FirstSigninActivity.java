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
        btn = (Button) findViewById(R.id.confirmeBtn);
        fullName = (EditText) findViewById(R.id.firstSignFullName);
        address = (EditText) findViewById(R.id.firstSignAddress);
        cell = (EditText) findViewById(R.id.firstSignCell);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        final Spinner specialityList = (Spinner) findViewById(R.id.speciality_spinner);
        ArrayAdapter<CharSequence> adapterSpecialityList = ArrayAdapter.createFromResource(this,
                R.array.speciality_spinner, android.R.layout.simple_spinner_item);
        adapterSpecialityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialityList.setAdapter(adapterSpecialityList);
        String newAccountType = spinner.getSelectedItem().toString();

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

                String fullname, addRess, celL, type, speciality;
                fullname = fullName.getText().toString();
                addRess = address.getText().toString();
                celL = cell.getText().toString();
                type = spinner.getSelectedItem().toString();
                speciality = specialityList.getSelectedItem().toString();
                UserHelper.addUser(fullname, addRess, celL, type);
                if (type.equals("Patient")) {
                    PatientHelper.addPatient(fullname, "addres", celL);
                    System.out.println("Add patient " + fullname + " to patient collection");

                } else {
                    DoctorHelper.addDoctor(fullname, "address",celL, speciality);
                }
                Intent k = new Intent(FirstSigninActivity.this, MainActivity.class);
                startActivity(k);
            }


        });
    }

}