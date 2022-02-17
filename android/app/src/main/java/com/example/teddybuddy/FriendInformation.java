package com.example.teddybuddy;

import com.google.gson.annotations.SerializedName;

public class FriendInformation<T> {

    //requestBody에 사용할 변수라서 생성자로 하나의 객체에 묶어서 같이 전송
    private String id;
    private String interests1st;
    private String interests2nd;
    private String interests3rd;

    public FriendInformation(String id, String interests1st, String interests2nd, String interests3rd) {
        this.id = id;
        this.interests1st = interests1st;
        this.interests2nd = interests2nd;
        this.interests3rd = interests3rd;
    }

    //response받은 데이터는 어노테이션으로 매칭시켜서 받고 getter생성, setter는 꼭 필요할 때만 생성
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
