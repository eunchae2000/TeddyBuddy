package com.example.teddyBuddy.service;

import com.example.teddyBuddy.dto.ResultDto;
import com.example.teddyBuddy.dto.UserDto;
import com.example.teddyBuddy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    // 회원가입
    public ResultDto signup(UserDto.Signup user){
        ResultDto result=new ResultDto();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            UserDto.IdCheck userById = userMapper.findUserById(user.getId());
            if(userById!=null){
                result.setMsg("이미있는 아이디");
                result.setDetail(userById);
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

    //로그인
    public ResultDto signin(UserDto.Signin user){
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
                UserDto.IdCheck userById = userMapper.findUserById(userId);
                result.setDetail(userById);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    //친구 매칭
    public ResultDto friend(UserDto.Friend user){
        ResultDto result=new ResultDto();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<UserDto.Friends> friends = userMapper.friendInterests123(user);
            List<String> list = new ArrayList<>();
            if(friends.size()!=0){
                friends.forEach(s -> list.add(s.getId()));
                int rnd = (int)(Math.random()*list.size());
                String friendId = list.get(rnd);
                userMapper.insertChat123(user);
                userMapper.insertUserAndChat(user.getId(), user.getChatId());
                userMapper.insertUserAndChat(friendId, user.getChatId());
                userMapper.updateFriendCnt(user.getId());
                userMapper.updateFriendCnt(friendId);
                result.setMsg("친구 매칭 성공");
                result.setSuccess(true);
                result.setDetail(friendId);
            }else{
                friends = userMapper.friendInterests12(user);
                if(friends.size()!=0){
                    friends.forEach(s -> list.add(s.getId()));
                    int rnd = (int)(Math.random()*list.size());
                    String friendId = list.get(rnd);
                    userMapper.insertChat12(user);
                    userMapper.insertUserAndChat(user.getId(), user.getChatId());
                    userMapper.insertUserAndChat(friendId, user.getChatId());
                    userMapper.updateFriendCnt(user.getId());
                    userMapper.updateFriendCnt(friendId);
                    result.setMsg("친구 매칭 성공");
                    result.setSuccess(true);
                    result.setDetail(friendId);
                }else{
                    friends = userMapper.friendInterests1(user);
                    if(friends.size()!=0){
                        friends.forEach(s -> list.add(s.getId()));
                        int rnd = (int)(Math.random()*list.size());
                        String friendId = list.get(rnd);
                        userMapper.insertChat1(user);
                        userMapper.insertUserAndChat(user.getId(), user.getChatId());
                        userMapper.insertUserAndChat(friendId, user.getChatId());
                        userMapper.updateFriendCnt(user.getId());
                        userMapper.updateFriendCnt(friendId);
                        result.setMsg("친구 매칭 성공");
                        result.setSuccess(true);
                        result.setDetail(friendId);
                    }else{
                        result.setMsg("매칭가능한 친구가 없습니다");
                    }
                }
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}