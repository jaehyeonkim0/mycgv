package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

//    int save(MemberDto memberDto);

    @Select("SELECT * FROM MYCGV_MEMBER WHERE ID = #{id}")
    Member findOneById(String id);

    int idCheck(String id);


}
