package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.repository.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberMapper memberMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SessionDto login(MemberDto memberDto) {
        return memberMapper.login(memberDto);
    }

    public int join(MemberDto memberDto){
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        return memberMapper.join(memberDto);
    }

    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }
}
