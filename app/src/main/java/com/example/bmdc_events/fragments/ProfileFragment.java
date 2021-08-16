package com.example.bmdc_events.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bmdc_events.MainActivity.MY_PREFS_NAME;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bmdc_events.login.LoginSendEmail;
import com.example.bmdc_events.R;

public class ProfileFragment extends Fragment {

    Button Join_To_Telegram, sign_out;
    TextView name, enter_name, email, enter_email, UserId, Id;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);

        Join_To_Telegram = view.findViewById(R.id.Join_To_Telegram);
        sign_out = view.findViewById(R.id.sign_out);
        name = view.findViewById(R.id.name);
        enter_name = view.findViewById(R.id.enter_name);
        email = view.findViewById(R.id.email);
        enter_email = view.findViewById(R.id.enter_email);
        UserId = view.findViewById(R.id.UserId);
        Id = view.findViewById(R.id.Id);

        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String id = prefs.getString("Id", null);//"No name defined" is the default value.

        Id.setText(id);

        Join_To_Telegram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=BMDCEentsbot"));
            startActivity(intent);
        });

        sign_out.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Id", null);
            editor.apply();

            Intent i = new Intent(getActivity(), LoginSendEmail.class);
            startActivity(i);
            (getActivity()).overridePendingTransition(0, 0);
        });

        return view;
    }
}