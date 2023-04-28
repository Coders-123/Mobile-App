package com.example.mobilehealthclinic.fireStoreApi;

import com.example.mobilehealthclinic.model.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference DoctorRef = db.collection("Doctor");

    public static void addDoctor(String name, String address, String cell, String speciality){
        Doctor doctor = new Doctor(name,address,cell, FirebaseAuth.getInstance().getCurrentUser().getEmail(),speciality);

        DoctorRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(doctor);

    }
}