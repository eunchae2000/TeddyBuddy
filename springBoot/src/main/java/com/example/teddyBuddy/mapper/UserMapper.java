package com.example.teddyBuddy.mapper;

import com.example.teddyBuddy.dto.UserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select user_id from user where user_id=#{id}")
    UserDto findUserById(@Param("user_id") String id);

    @Insert("insert into user values(#{name},#{id},#{password})")
    @Options(useGeneratedKeys = true,keyProperty = "user_id",keyColumn = "user_id")
    void signup(UserDto user);

    @Select("select user_id from user where user_id=#{id} and user_password=#{password}")
    String signin(UserDto user);
}