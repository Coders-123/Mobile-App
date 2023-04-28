package com.example.mobilehealthclinic.fireStoreApi;


import com.example.mobilehealthclinic.model.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference PatientRef = db.collection("Patient");

    public static void addPatient(String name, String address, String cell){
        Patient patient = new Patient(name,address,cell, FirebaseAuth.getInstance().getCurrentUser().getEmail());
        System.out.println("Create object patient");
        PatientRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(patient);
    }
}
