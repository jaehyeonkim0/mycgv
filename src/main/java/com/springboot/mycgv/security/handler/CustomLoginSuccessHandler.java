package com.springboot.mycgv.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j2
public class CustomLoginSuccessHandler extends LoginSuccessHandler implements AuthenticationSuccessHandler {

    //    private final RequestCache requestCache = new HttpSessionRequestCache();
//    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        redirectPrevPage(request, response, authentication);
    }

    @Override
    protected void getToken() {
        log.info("일반 로그인 시 토큰 발행");
    }
}
