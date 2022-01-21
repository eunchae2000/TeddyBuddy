package com.example.teddybuddy;

import com.google.gson.annotations.SerializedName;

public class PostResult {
    @SerializedName("userId")
    private String userId;

    @SerializedName("userPw")
    private String userPw;

    @SerializedName("childName")
    private String childName;

    @SerializedName("childAge")
    private int childAge;

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("parentName")
    private String parentName;

    @SerializedName("parentTel")
    private String parentTel;

    @SerializedName("childGender")
    private String childGender;

    @Override
    public String toString(){
        return "PostResult{" +
                "userId = " + userId +
                ", userPw = " + userPw +
                ", childName = " + childName +
                ", childAge = " + childAge +
                ", nickName = " + nickName +
                ", parentName = " + parentName +
                ", parentTel = " + parentTel +
                ", childGender = " + childGender +
                "}";
    }


}
