package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {
    @POST("user/signin")
    Call<LoginInformation> getSignin(
            @Body LoginInformation loginInformation  //서버에서 json으로 요청받으므로 @Field가 아닌 @Body사용
    );
}
