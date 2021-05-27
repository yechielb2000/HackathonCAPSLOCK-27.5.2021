package com.example.bmdc_events;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import static com.example.bmdc_events.MainActivity.MY_PREFS_NAME;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class PostRequest {

    private MyRetrofit client;
    private final Retrofit retrofit;

    public PostRequest() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://capslock-core.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        client = retrofit.create(MyRetrofit.class);
    }

    public void createPost(String value, Context context){

        MyRetrofit.MyBody body = new MyRetrofit.MyBody();
        body.setApp("Bandio");
        body.setEmail(value);
        Call<MyRetrofit.MyRes> myRes = client.postUser(body);

        myRes.enqueue(new Callback<MyRetrofit.MyRes>() {
            @Override
            public void onResponse(Call<MyRetrofit.MyRes> call, Response<MyRetrofit.MyRes> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(context, "Email was not sent successfully, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                MyRetrofit.MyRes postResponse =  response.body();

                Toast.makeText(context, postResponse.getStatus() +  "\n" + postResponse.getMessage(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), LoginSendToken.class));

            }

            @Override
            public void onFailure(Call<MyRetrofit.MyRes> call, Throwable t) {

                Toast.makeText(context, "please contact us ", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void sendGet(String value, Context context){


        Call<MyRetrofit.MyGetRes> res = client.verify(value);

        res.enqueue(new Callback<MyRetrofit.MyGetRes>() {
            @Override
            public void onResponse(Call<MyRetrofit.MyGetRes> call, Response<MyRetrofit.MyGetRes> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(context, "Token was not sent successfully, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                MyRetrofit.MyGetRes postResponse =  response.body();
                SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("Id", postResponse.getMessage().getUserId());
                editor.apply();
                Toast.makeText(context, postResponse.getMessage().userId, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context.getApplicationContext(), FragmentContainer.class));
            }

            @Override
            public void onFailure(Call<MyRetrofit.MyGetRes> call, Throwable t) {

                Toast.makeText(context, "please contact us ", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
