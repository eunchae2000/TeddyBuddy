package com.example.teddybuddy;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @POST("user/{signin}")
    Call<PostResult> getSignin(@Body LoginInfomation loginInfomation);
    @POST("user/{signup}")
    Call<PostResult> getSignup(
            @Body String userId,
            @Body String userPw,
            @Body String childName,
            @Body int childAge,
            @Body String nickName,
            @Body String parentName,
            @Body String parentTel,
            @Body String childGender,
            @Body String text1,
            @Body String text2,
            @Body String text3

    );

}
