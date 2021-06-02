package com.example.bmdc_events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "bmdc_events";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String id = prefs.getString("Id", null);//"No name defined" is the default value.

//        if (id == null)
//            startActivity(new Intent(this, LoginSendEmail.class));
//        else
            startActivity(new Intent(this, FragmentContainer.class));
    }

}