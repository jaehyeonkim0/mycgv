package com.springboot.mycgv.security.handler;

import com.springboot.mycgv.security.dto.MemberSecurityDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 로그인 성공");
        log.info(authentication.getPrincipal());

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String oAuthType = token.getAuthorizedClientRegistrationId();

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();

        if(!memberSecurityDTO.getMid().equals("guest")) {
            response.sendRedirect("/");
        }else {
            HttpSession session = request.getSession(false);
            session.setAttribute("oauthId", memberSecurityDTO.getEmail());
            session.setAttribute("name", memberSecurityDTO.getName());
            session.setAttribute("pnumber", memberSecurityDTO.getPnumber());
            session.setAttribute("social", memberSecurityDTO.isSocial());
            response.sendRedirect("/join");
        }

    }
}
