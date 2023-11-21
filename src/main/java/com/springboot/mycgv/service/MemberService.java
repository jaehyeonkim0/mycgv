package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.mapper.MemberMapper;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberMapper memberMapper;
    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    public SessionDto login(MemberDto memberDto) {
//        return memberMapper.login(memberDto);
//    }

//    public int insert(MemberDto memberDto){
//        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
//        return memberMapper.save(memberDto);
//    }

    public void save(MemberDto memberDto) {
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        memberRepository.save(memberDto.toEntity());
    }

    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }
}
