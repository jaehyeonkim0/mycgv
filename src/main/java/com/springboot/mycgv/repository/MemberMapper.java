package com.springboot.mycgv.repository;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.SessionDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    SessionDto login(MemberDto memberDto);

    int join(MemberDto memberDto);

    int idCheck(String id);


}
