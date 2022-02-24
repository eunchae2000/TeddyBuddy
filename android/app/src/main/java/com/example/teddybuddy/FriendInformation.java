package com.example.teddybuddy;

import com.google.gson.annotations.SerializedName;

public class FriendInformation<T> {

    //requestBody에 사용할 변수라서 생성자로 하나의 객체에 묶어서 같이 전송
    private String id;
    private Integer chatId;
    private String interests1st;
    private String interests2nd;
    private String interests3rd;
    private String friend_id;
    private String friend_nickname;
    private String friend_age;
    private String friend_chatId;

    public FriendInformation(String id, Integer chatId, String interests1st, String interests2nd, String interests3rd, String friend_id, String friend_nickname, String friend_age, String friend_chatId) {
        this.id = id;
        this.chatId = chatId;
        this.interests1st = interests1st;
        this.interests2nd = interests2nd;
        this.interests3rd = interests3rd;
        this.friend_id = friend_id;
        this.friend_nickname = friend_nickname;
        this.friend_age = friend_age;
        this.friend_chatId = friend_chatId;

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
