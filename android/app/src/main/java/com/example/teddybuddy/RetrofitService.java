package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("user/{signin}")
    Call<PostResult> getSignin(@Path("signin")String signin);

    @POST("user/{signin}")
    Call<PostResult> getSignup(@Path("signup")String signup);

}
