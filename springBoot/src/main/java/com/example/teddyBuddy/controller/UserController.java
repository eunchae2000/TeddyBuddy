package com.example.teddyBuddy.controller;


import com.example.teddyBuddy.dto.ResultDto;
import com.example.teddyBuddy.dto.UserDto;
import com.example.teddyBuddy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResultDto signup(@RequestBody UserDto user) {
        return userService.signin(user);
    }

    // 로그인
    @PostMapping("/signin")
    public ResultDto signin(@RequestBody UserDto user) {
        return userService.signup(user);
    }
}
