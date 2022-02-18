package com.example.teddyBuddy.mapper;

import com.example.teddyBuddy.dto.UserDto;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    //아이디 값 존재 확인
    @Select("select user_id, user_nickname, interests_1st, interests_2nd, interests_3rd from public.\"users\" where user_id=#{id}")
    @Results({
            @Result(property="id", column="user_id"),
            @Result(property="nickname", column="user_nickname"),
            @Result(property="interests1st", column="interests_1st"),
            @Result(property="interests2nd", column="interests_2nd"),
            @Result(property="interests3rd", column="interests_3rd"),
    })
    UserDto.IdCheck findUserById(String id);

    //회원가입
    @Insert("insert into public.\"users\"(user_name, user_id, user_password, user_nickname, user_age, user_gender, companion_name, companion_num, interests_1st, interests_2nd, interests_3rd) values(#{name}, #{id}, #{password}, #{nickname}, #{age},  #{gender},  #{companionName}, #{companionNum},  #{interests1st}, #{interests2nd}, #{interests3rd})")
    void signup(UserDto.Signup user);

    //로그인
    @Select("select user_id from public.\"users\" where user_id=#{id} and user_password=#{password}")
    String signin(UserDto.Signin user);

    //친구 매칭 관심사1순위,2순위,3순위 일치
    @Select("select user_id from public.\"users\" where interests_1st=#{interests1st} and interests_2nd=#{interests2nd} and interests_3rd=#{interests3rd} " +
            "and friend_cnt<3 " +
            "and user_id not in (#{id}) " +
            "and user_id not in (" +
            "SELECT DISTINCT user_id FROM public.\"users_chats\" WHERE chat_id IN (" +
            "SELECT chat_id FROM public.\"users_chats\" where user_id=#{id}))")
    @Results({
            @Result(property="id", column="user_id")
    })
    List<UserDto.Friends> friendInterests123(UserDto.Friend user);

    //친구 매칭 관심사1순위,2순위 일치
    @Select("select user_id from public.\"users\" where interests_1st=#{interests1st} and interests_2nd=#{interests2nd} " +
            "and friend_cnt<3" +
            "and user_id not in (#{id}) " +
            "and user_id not in (" +
            "SELECT DISTINCT user_id FROM public.\"users_chats\" WHERE chat_id IN (" +
            "SELECT chat_id FROM public.\"users_chats\" where user_id=#{id}))")
    @Results({
            @Result(property="id", column="user_id")
    })
    List<UserDto.Friends> friendInterests12(UserDto.Friend user);

    //친구 매칭 관심사1순위만 일치
    @Select("select user_id from public.\"users\" where interests_1st=#{interests1st} " +
            "and friend_cnt<3" +
            "and user_id not in (#{id}) " +
            "and user_id not in (" +
            "SELECT DISTINCT user_id FROM public.\"users_chats\" WHERE chat_id IN (" +
            "SELECT chat_id FROM public.\"users_chats\" where user_id=#{id}))")
    @Results({
            @Result(property="id", column="user_id")
    })
    List<UserDto.Friends> friendInterests1(UserDto.Friend user);

    //채팅방123 추가
    @Insert("insert into public.\"chats\"(chat_interests_1st, chat_interests_2nd, chat_interests_3rd) values(#{interests1st}, #{interests2nd}, #{interests3rd})")
    @SelectKey(keyProperty = "chatId",keyColumn = "chat_id",resultType = int.class,before = false,statement = "select currval('chats_chat_id_seq')")
    int insertChat123(UserDto.Friend user);

    //채팅방12 추가
    @Insert("insert into public.\"chats\"(chat_interests_1st, chat_interests_2nd) values(#{interests1st}, #{interests2nd})")
    @SelectKey(keyProperty = "chatId",keyColumn = "chat_id",resultType = int.class,before = false,statement = "select currval('chats_chat_id_seq')")
    int insertChat12(UserDto.Friend user);

    //채팅방1 추가
    @Insert("insert into public.\"chats\"(chat_interests_1st) values(#{interests1st})")
    @SelectKey(keyProperty = "chatId",keyColumn = "chat_id",resultType = int.class,before = false,statement = "select currval('chats_chat_id_seq')")
    int insertChat1(UserDto.Friend user);

    //채팅 추가
    @Insert("insert into public.\"users_chats\"(user_id, chat_id) values(#{user_id}, #{chat_id})")
    void insertUserAndChat(String user_id, int chat_id);

    //친구아이디 조회
    @Select("select distinct user_id from public.\"users_chats\" where user_id not in (#{id}) and chat_id in (" +
            "select chat_id from public.\"users_chats\" where user_id=#{id})")
    @Results({
            @Result(property="id", column="user_id")
    })
    List<UserDto.Friends> findFriendById(UserDto.Friends user);

    //친구 수 1증가
    @Update("UPDATE public.\"users\" SET friend_cnt = friend_cnt+1 WHERE user_id=#{id}")
    void updateFriendCnt(String id);

    //친구정보 조회
    @Select("SELECT user_id, user_nickname, user_age FROM public.\"users\" WHERE user_id=#{id}")
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "nickname", column = "user_nickname"),
            @Result(property = "age", column = "user_age")
    })
    UserDto.FriendInfo friendInfo(String id);

    //채팅방 ID 조회
    @Select("SELECT a.chat_id FROM public.\"users_chats\" a, public.\"users_chats\" b " +
            "WHERE a.user_id=#{id} and b.user_id=#{friendId} and a.chat_id=b.chat_id")
    String chatId(String id, String friendId);
}
