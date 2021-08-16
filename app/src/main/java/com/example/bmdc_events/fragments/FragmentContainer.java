package com.example.bmdc_events.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.bmdc_events.GetListsDialogFragment;
import com.example.bmdc_events.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentContainer extends AppCompatActivity {

    private TextView current_fragment;
    private ImageButton final_list_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        current_fragment = findViewById(R.id.current_fragment);
        current_fragment.setText("Events");

        final_list_button = findViewById(R.id.final_list_button);
        final_list_button.setVisibility(View.GONE);

        final_list_button.setOnClickListener(v -> new GetListsDialogFragment());

        BottomNavigationView bottomNav = findViewById(R.id.bar_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalenderFragment()).commit();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint({"NonConstantResourceId", "SetTextI18n"}) @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = new CalenderFragment();
            switch (item.getItemId()) {

                case R.id.calender_fragment_button:
                    current_fragment.setText("Calender");
                    final_list_button.setVisibility(View.GONE);
                    selectedFragment = new CalenderFragment();
                    break;

                case R.id.events_fragment_button:
                    current_fragment.setText("Events");
                    final_list_button.setVisibility(View.VISIBLE);  //TODO only to admin
                    selectedFragment = new EventsFragment();
                    break;

                case R.id.profile_fragment_button:
                    current_fragment.setText("Profile");
                    final_list_button.setVisibility(View.GONE);
                    selectedFragment = new ProfileFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    public void onBackPressed() {
    }
}