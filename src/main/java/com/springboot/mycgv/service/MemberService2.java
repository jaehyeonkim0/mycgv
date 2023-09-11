package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.repository.Membermapper2;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService2 {

    private Membermapper2 membermapper2;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public int insert(MemberDto memberDto) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        int result = membermapper2.save(memberDto);
        return result;
    }
}
