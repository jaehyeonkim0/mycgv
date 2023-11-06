package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.UserSessionDto;
import com.springboot.mycgv.model.CustomUserDetails;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.SessionUser;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String id) {

        Member member = memberRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));

        HttpSession session = request.getSession();

        if(member != null) {
            session.setAttribute(SessionUser.LOGIN_USER, new UserSessionDto(member));
            return new CustomUserDetails(member);
        }else {
            session.setAttribute(SessionUser.LOGIN_USER, null); // 세션에 null 값을 설정
            return null;
        }

    }

}
