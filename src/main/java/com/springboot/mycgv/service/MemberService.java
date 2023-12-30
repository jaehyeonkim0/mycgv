package com.springboot.mycgv.service;

import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.mapper.MemberMapper;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;
    private final ModelMapStruct modelMapStruct;
//    private TokenProvider tokenProvider;

//    public SessionDto login(MemberDto memberDto) {
//        return memberMapper.login(memberDto);
//    }

//    public int insert(MemberDto memberDto){
//        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
//        return memberMapper.save(memberDto);
//    }

    public void save(MemberDto memberDto) {

//        Member member = modelMapper.map(memberDto, Member.class);

//        member.encrytPass(bCryptPasswordEncoder.encode(memberDto.getPassword()));
//        member.addRole(MemberRole.USER);
//
//        log.info("=======================");
//        log.info(member);
//        log.info(member.getRoleSet());
//
//        memberRepository.save(member);

        Member member = memberDto.toEntity();
        member.encrytPass(bCryptPasswordEncoder.encode(member.getPassword()));
        member.addRole(MemberRole.USER);

        log.info(member.getRoleSet());

        memberRepository.save(member);

//        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
    }

//    public String login(String id, String password) {
//
//        Member member = memberRepository.findById(id).orElseThrow(() ->
//                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));
//
//        if(!bCryptPasswordEncoder.matches(password, member.getPassword())) {
//            throw new UsernameNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다");
//        }
//
//        return tokenProvider.generateToken(id, (1 * 1000 * 60));
//    }


    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }

}
