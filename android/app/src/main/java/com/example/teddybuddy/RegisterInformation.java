package com.example.teddybuddy;

import com.google.gson.annotations.SerializedName;

public class RegisterInformation<T> {
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String age;
    private String gender;
    private String companionName;
    private String companionNum;
    private String interests1st;
    private String interests2nd;
    private String interests3rd;

    public RegisterInformation(String id, String password, String name, String nickname, String age, String gender, String companionName, String companionNum, String interests1st, String interests2nd, String interests3rd){
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.companionName = companionName;
        this.companionNum = companionNum;
        this.interests1st = interests1st;
        this.interests2nd = interests2nd;
        this.interests3rd = interests3rd;
    }

    @SerializedName("msg")
    private String msg;
    @SerializedName("success")
    private boolean success;
    @SerializedName("detail")
    private T detail;

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getDetail() {
        return detail;
    }
}
