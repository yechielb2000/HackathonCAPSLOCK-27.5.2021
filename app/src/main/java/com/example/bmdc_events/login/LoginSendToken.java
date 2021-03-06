package com.example.bmdc_events.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bmdc_events.bmdc_api.PostRequest;
import com.example.bmdc_events.R;

public class LoginSendToken extends AppCompatActivity {

    EditText token;
    Button sendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_send_token);

        token = findViewById(R.id.token_tv);
        sendToken = findViewById(R.id.send_token_button);

        sendToken.setOnClickListener(v -> {

            PostRequest postRequest = new PostRequest();
            assert token.getText() != null;
            postRequest.sendGet(token.getText().toString(), this);
        });

    }
}