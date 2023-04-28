package com.example.mobilehealthclinic.fireStoreApi;


import android.widget.Toast;

import com.example.mobilehealthclinic.FirstSigninActivity;
import com.example.mobilehealthclinic.MainActivity;
import com.example.mobilehealthclinic.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserHelper {
    static FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference UsersRef = db.collection("User");

    public static void addUser(String name, String address, String cell,String type){
        User user = new User(name,address,cell, FirebaseAuth.getInstance().getCurrentUser().getEmail(),type);
        UsersRef.document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).set(user);

    }
}
