package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public SessionDto login(MemberDto memberDto) {
        return memberMapper.login(memberDto);
    }

    public int join(MemberDto memberDto){
        return memberMapper.join(memberDto);
    }


    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }
}
