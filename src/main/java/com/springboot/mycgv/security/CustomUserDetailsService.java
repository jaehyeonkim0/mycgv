package com.springboot.mycgv.security;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import com.springboot.mycgv.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final HttpServletRequest request;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String id) {
//
//        Member member = memberRepository.findById(id).orElseThrow(() ->
//                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));
//
//        HttpSession session = request.getSession();
//
//        if(member != null) {
//            session.setAttribute(SessionUser.LOGIN_USER, new UserSessionDto(member));
//
//            return new CustomUserDetails(member.getId(), member.getPassword());
//        }else {
//            session.setAttribute(SessionUser.LOGIN_USER, null); // 세션에 null 값을 설정
//            return null;
//        }
//
//    }

    @Override
    public UserDetails loadUserByUsername(String id) {

        Member member = memberRepository.getWithRoles(id).orElseThrow(
                () -> new UsernameNotFoundException("username not found : " + id));

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getId(),
                        member.getPassword(),
                        member.getEmail(),
                        member.getName(),
                        member.getPnumber(),
                        member.isDel(),
                        member.isSocial(),
                        member.getRoleSet()
                                .stream().map(memberRole ->
                                        new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info("UserDetailsService");
        log.info("memberSecurityDTO = {}",memberSecurityDTO);

        return memberSecurityDTO;

    }

}
