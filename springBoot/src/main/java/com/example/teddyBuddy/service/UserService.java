package com.example.teddyBuddy.service;

import com.example.teddyBuddy.dto.ResultDto;
import com.example.teddyBuddy.dto.UserDto;
import com.example.teddyBuddy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public ResultDto signup(UserDto user){
        ResultDto result=new ResultDto();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            UserDto userById = userMapper.findUserById(user.getId());
            if(userById!=null){
                result.setMsg("이미있는 아이디");
            }else {
                userMapper.signup(user);
                result.setMsg("회원가입 성공");
                result.setSuccess(true);
                result.setDetail(user);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ResultDto signin(UserDto user){
        ResultDto result=new ResultDto();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            String userId = userMapper.signin(user);
            if(userId==null){
                result.setMsg("잘못된 아이디 또는 비밀번호");
            }else {
                result.setMsg("로그인 성공");
                result.setSuccess(true);
                user.setId(userId);
                UserDto userById = userMapper.findUserById(userId);
                result.setDetail(userById);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}