package com.example.bmdc_events;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class InitializeCloudFireStore {

    FirebaseFirestore firebaseFirestore;

    public InitializeCloudFireStore() {

         firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void addEvent(String subject, String text, String date, String deadlineDate){

        ArrayList<String> usersIdArray = new ArrayList<>();

        //create a new event
        Map<String, Object> event = new HashMap<>();
        event.put("subject", subject);
        event.put("text", text);
        event.put("date", date);
        event.put("deadline date", deadlineDate);
        event.put("usersIdArray", usersIdArray);

        // Add a new document with a generated ID
        firebaseFirestore.collection("events")
                .add(event)
                .addOnSuccessListener(documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Error adding document", e));
    }
}
