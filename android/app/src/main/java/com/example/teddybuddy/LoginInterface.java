package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {
    String LoginUrl = "http://192.168.96.22:8081/";

    @FormUrlEncoded
    @POST("user/{signin}")
    Call<LoginInfomation> getSignin(
            @Field("userId") String user_id,
            @Field("userPw") String user_pw
    );
}
