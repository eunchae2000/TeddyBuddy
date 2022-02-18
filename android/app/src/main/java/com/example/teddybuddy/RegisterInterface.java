package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {

    @POST("user/signup")
    Call<RegisterInformation> getSignup( @Body RegisterInformation registerInformation);
}
