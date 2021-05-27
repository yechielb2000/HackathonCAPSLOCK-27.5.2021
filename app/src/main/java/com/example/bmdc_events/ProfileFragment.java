package com.example.bmdc_events;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    Button Join_To_Telegram;
    TextView name, enter_name, email, enter_email;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);

        Join_To_Telegram = view.findViewById(R.id.Join_To_Telegram);
        name = view.findViewById(R.id.name);
        enter_name = view.findViewById(R.id.enter_name);
        email = view.findViewById(R.id.email);
        enter_email = view.findViewById(R.id.enter_email);

        Join_To_Telegram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=BMDCEentsbot"));
            startActivity(intent);
//            Intent i = new Intent(getActivity(), LoginSendEmail.class);
//            startActivity(i);
//            (getActivity()).overridePendingTransition(0, 0);
        });

        return view;

    }
}