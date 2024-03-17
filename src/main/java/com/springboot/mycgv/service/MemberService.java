package com.springboot.mycgv.service;

import com.springboot.mycgv.config.jwt.TokenProvider;
import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final ModelMapStruct modelMapStruct;
//    private final ModelMapper modelMapper;
//    private final MemberMapper memberMapper;

//    public SessionDto login(MemberDto memberDto) {
//        return memberMapper.login(memberDto);
//    }

//    public int insert(MemberDto memberDto){
//        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
//        return memberMapper.save(memberDto);
//    }

    public void save(MemberDto memberDto) {
        Member member = modelMapStruct.toMemberEntity(memberDto);
        member.encrytPass(bCryptPasswordEncoder.encode(member.getPassword()));
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
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


//    public int idCheck(String id) {
//        return memberMapper.idCheck(id);
//    }


    public void register(MemberDto memberDto) {
        Member member = modelMapStruct.toMemberEntity(memberDto);
        member.encrytPass(bCryptPasswordEncoder.encode(member.getPassword()));
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
        String access_token = tokenProvider.generateToken(member.getId());

        Cookie cookie = new Cookie("ac", access_token);
        cookie.setHttpOnly(true);

        log.info(cookie.getName());
        log.info(cookie.getValue());
    }

}
