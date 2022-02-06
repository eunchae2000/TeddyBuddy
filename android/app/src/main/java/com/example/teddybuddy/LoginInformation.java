package com.example.teddybuddy;

import com.google.gson.annotations.SerializedName;

//요청받고 보낼 json형태에 맞춰서 변수명, 타입 설정 <스프링부트Dto변수랑 맞추기>
public class LoginInformation<T> {
    //requestBody에 사용할 변수라서 생성자로 하나의 객체에 묶어서 같이 전송
    private String id;
    private String password;

    public LoginInformation(String id, String password) {
        this.id = id;
        this.password = password;
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
