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

    @SerializedName("text1")
    private String text1;

    @SerializedName("text2")
    private String text2;

    @SerializedName("text3")
    private String text3;

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
                ", text1 = " + text1 +
                ", text2 = " + text2 +
                ", text3 = " + text3 +
                "}";
    }


}
