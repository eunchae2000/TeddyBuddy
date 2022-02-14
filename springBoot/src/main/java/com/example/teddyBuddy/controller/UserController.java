package com.example.teddyBuddy.controller;


import com.example.teddyBuddy.dto.ResultDto;
import com.example.teddyBuddy.dto.UserDto;
import com.example.teddyBuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResultDto signup(@RequestBody UserDto.Signup user) {
        return userService.signup(user);
    }

    // 로그인
    @PostMapping("/signin")
    public ResultDto signin(@RequestBody UserDto.Signin user) {
        return userService.signin(user);
    }

    //친구 매칭
    @GetMapping("/friend")
    public ResultDto friend(
            @RequestParam("user_id") String id,
            @RequestParam("interests_1st") String interests1st,
            @RequestParam("interests_2nd") String interests2nd,
            @RequestParam("interests_3rd") String interests3rd) {
        UserDto.Friend user = new UserDto.Friend();
        user.setId(id);
        user.setInterests1st(interests1st);
        user.setInterests2nd(interests2nd);
        user.setInterests3rd(interests3rd);
        return userService.friend(user);
    }
}
