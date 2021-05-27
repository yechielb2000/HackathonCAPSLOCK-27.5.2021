package com.example.bmdc_events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    final String MY_PREFS_NAME = "MyPrefsFile";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putString("name", null);
//        editor.apply();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("Id", "null");//"No name defined" is the default value.

//        if (name.equals("null"))
//            startActivity(new Intent(this, LoginSendEmail.class));

        startActivity(new Intent(this, FragmentContainer.class));
    }

}