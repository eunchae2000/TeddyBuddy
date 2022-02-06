package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {

    String RegisterUrl = "http://192.168.96.22:8081/";

    @FormUrlEncoded
    @POST("user/{signup}")
    Call<RegisterInformation> getSignup(
            @Field("userId") String userId,
            @Field("userPw") String userPw,
            @Field("childName") String childName,
            @Field("childAge") int childAge,
            @Field("nickName") String nickName,
            @Field("parentName") String parentName,
            @Field("parentTel") String parentTel,
            @Field("childGender") String childGender,
            @Field("text1") String text1,
            @Field("text2") String text2,
            @Field("text3") String text3
    );
}
