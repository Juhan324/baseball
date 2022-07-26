package com.baseball.webgame.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baseball.webgame.model.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM public.user WHERE username=#{id}")
    User getUser(@Param("id") String id);

    @Insert("INSERT INTO public.user(username,userpassword,role,userid) VALUES(#{data.username}, #{data.password}, #{data.role}, #{data.id})")
    void insertUser(@Param("data") User data);
}
