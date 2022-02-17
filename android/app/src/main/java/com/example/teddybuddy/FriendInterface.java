package com.example.teddybuddy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FriendInterface {

    @GET("user/friend")
    Call<FriendInformation> getFriends(
            @Query("user_id") String id,
            @Query("interests_1st") String interests1st,
            @Query("interests_2nd") String interests2nd,
            @Query("interests_3rd") String interests3rd
    );

    @GET("user/friendInfo")
    Call<FriendInformation> getFriendInfo(
            @Query("user_id") String id
    );
}
