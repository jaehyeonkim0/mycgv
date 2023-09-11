package com.springboot.mycgv.repository;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Membermapper2 {

    @Insert("INSERT INTO MYCGV_MEMBER (ID, PASSWORD, NAME) VALUES (#{id}, #{password}, #{name})")
    int save(MemberDto memberDto);

    @Select("SELECT * FROM MYCGV_MEMBER WHERE ID = #{id}")
    User findOneById(String id);
}
