package com.springboot.mycgv.security.handler;

import com.springboot.mycgv.config.jwt.TokenProvider;
import com.springboot.mycgv.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler extends LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 로그인 성공");
        log.info("요청 URL = {}", request.getRequestURI());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String oAuthType = token.getAuthorizedClientRegistrationId();

        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        if(!memberSecurityDTO.getMid().equals("guest")) {
            // 소셜 로그인 (회원가입 완료)
            redirectPrevPage(request, response, authentication);
        }else {
            // 최초 소셜 로그인 (회원가입 미완료)
            HttpSession session = request.getSession(false);
            session.setAttribute("oauthId", memberSecurityDTO.getEmail());
            session.setAttribute("name", memberSecurityDTO.getName());
            session.setAttribute("pnumber", memberSecurityDTO.getPnumber());
            session.setAttribute("social", memberSecurityDTO.isSocial());
            response.sendRedirect("/join");
        }
    }
    @Override
    protected void getToken() {
        log.info("소셜 로그인 시 Token 발행");
        //        Gson gson = new Gson();
//        String accessToken = tokenProvider.generateToken(memberSecurityDTO.getMid());
//        Map<String, String> map = Map.of("access_token", accessToken);
//
//        String jsonStr = gson.toJson(map);
//        response.getWriter().println(jsonStr);
    }
}
