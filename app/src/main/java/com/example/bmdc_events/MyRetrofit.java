package com.example.bmdc_events;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyRetrofit {

    @POST("/auth/token")
    Call<MyRes> postUser(@Body MyBody body);

//    @GET("/auth/verification/{token}")
//    Call<VerRes> verify(@Path("token") String token);

    class MyBody {
        private String email;
        private String app;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }
    }

    class MyRes {
        private String status;
        private String message;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}