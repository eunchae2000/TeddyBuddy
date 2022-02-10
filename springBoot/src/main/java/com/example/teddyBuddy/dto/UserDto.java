package com.example.teddyBuddy.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @Getter
    public  static class Signup {
        private String name;
        private String id;
        private String password;
        private String nickname;
        private String age;
        private String gender;
        private String companionName;
        private String companionNum;
        private String interests1st;
        private String interests2nd;
        private String interests3rd;
    }

    @Getter
    public static class IdCheck {
        private String id;
        private String nickname;
    }

    @Getter
    public static class Signin {
        private String id;
        private String password;
    }

    @Getter
    @Setter
    public static class Friend {
        private String Id;
        private int chatId;
        private String interests1st;
        private String interests2nd;
        private String interests3rd;
    }

    @Getter
    public static class Friends {
        private String id;
    }
}
