package com.example.bmdc_events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FragmentContainer extends AppCompatActivity {

    private TextView current_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        current_fragment = findViewById(R.id.current_fragment);
        current_fragment.setText("Events");

        BottomNavigationView bottomNav = findViewById(R.id.bar_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalenderFragment()).commit();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment selectedFragment = new CalenderFragment();
            switch (item.getItemId()) {

                case R.id.calender_fragment_button:
                    current_fragment.setText("Calender");
                    selectedFragment = new CalenderFragment();
                    break;

                case R.id.events_fragment_button:
                    current_fragment.setText("Events");
                    selectedFragment = new EventsFragment();
                    break;

                case R.id.profile_fragment_button:
                    current_fragment.setText("Profile");
                    selectedFragment = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}