package com.example.bmdc_events;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRequest {

    private MyRetrofit client;
    private final Retrofit retrofit;

    public PostRequest() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://capslock-core.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public void createPost(String value, Context context){

        client = retrofit.create(MyRetrofit.class);
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

                Toast.makeText(context, "Status : " + postResponse.getStatus() +  "\nMessage : " + postResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MyRetrofit.MyRes> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
