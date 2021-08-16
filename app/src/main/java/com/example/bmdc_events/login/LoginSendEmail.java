package com.example.bmdc_events.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bmdc_events.bmdc_api.PostRequest;
import com.example.bmdc_events.R;

public class LoginSendEmail extends AppCompatActivity {

    EditText email;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_send_email);

        email = findViewById(R.id.email_tv);
        sendEmail = findViewById(R.id.send_email_button);

        sendEmail.setOnClickListener(v -> {

            if (email.getText().length() > 0) {
                PostRequest postRequest = new PostRequest();
                postRequest.createPost(email.getText().toString(), this);
            }else{
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}