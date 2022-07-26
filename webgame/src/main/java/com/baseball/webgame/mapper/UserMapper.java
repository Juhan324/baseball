package com.baseball.webgame.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.baseball.webgame.model.UserVO;

@Mapper
@Component
public interface UserMapper {
    @Select("SELECT * FROM public.user WHERE userid=#{id}")
    UserVO getUser(@Param("id") String id);

    @Insert("INSERT INTO public.user(userid, username, password, role) VALUES(#{data.id}, #{data.username}, #{data.password}, #{data.role})")
    void insertUser(@Param("data") UserVO data);
}
