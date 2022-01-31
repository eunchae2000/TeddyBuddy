package com.example.teddyBuddy.mapper;

import com.example.teddyBuddy.dto.UserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select user_id from public.\"users\" where user_id=#{id}")
    @Results({
            @Result(property="id", column="user_id")
    })
    UserDto.IdCheck findUserById(String id);

    @Insert("insert into public.\"users\"(user_name, user_id, user_password, user_nickname, user_age, user_gender, companion_name, companion_num, interests_1st, interests_2nd, interests_3rd) values(#{name}, #{id}, #{password}, #{nickname}, #{age},  #{gender},  #{companionName}, #{companionNum},  #{interests1st}, #{interests2nd}, #{interests3rd})")
    //자동생성 키가 아니여서 필요없음, autoincrement나 seq를 사용할 때 쓰면 자동생성된 키 값을 반환해준다.
    //@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "user_id")
    void signup(UserDto.Signup user);

    @Select("select user_id from public.\"users\" where user_id=#{id} and user_password=#{password}")
    String signin(UserDto.Signin user);
}
