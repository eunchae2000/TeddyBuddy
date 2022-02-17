package com.example.teddybuddy;

public class Chat {
    private String nickname;
    private String msg;
    private String time;
    private int viewType;

    public Chat(String nickname, String msg, String time, int viewType){
        this.nickname = nickname;
        this.msg = msg;
        this.time = time;
        this.viewType = viewType;
    }

    public String getNickname(){
        return nickname;
    }

    public String getMsg(){
        return msg;
    }

    public String getTime(){
        return time;
    }

    public int getViewType(){
        return viewType;
    }
}
