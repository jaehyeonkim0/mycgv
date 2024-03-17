package com.springboot.mycgv.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
public abstract class LoginSuccessHandler {
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected void redirectPrevPage(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        clearSession(request);
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        log.info("요청한 페이지 = {}", savedRequest);

        //권한이 필요없는 URI
        String noAuthUri = "http://localhost:9005/board_list?page=0";

        /**
         * prevPage가 존재하는 경우 = 사용자가 직접 /login 경로로 로그인 요청
         * 기존 Session의 prevPage attribute 제거
         */
        String prevPage = (String) request.getSession().getAttribute("prevPage");
        log.info("prevPage = {}", prevPage);


        /**
         * @savedRequest : 인증을 위해 로그인 페이지로 리다이렉트 되기 전 사용자가 요청한 페이지
         *
         * 인증이 필요한 페이지(mypage, board_write, board_content..) 이동 후
         * header 게시판 메뉴(인증 필요없는 페이지) 이동 시, savedRequest가 저장되어 같이 움직임
         * header 게시판 메뉴(인증 필요없는 페이지) 이동 시, savedRequest null 처리
         *
         * 단, savedRequest에 /auth가 포함되어 있다면 null 처리 안하고 유지
         *
         * 인증이 필요한 페이지 -> 인증 필요없는 페이지 -> 직접 '로그인' 페이지로 이동 시,
         * savedRequest 삭제해야 로그인 후 인증 필요 없는 페이지 이동
         *
         * 삭제 안할 시, 로그인 후 savedRequest로 이동
         * 인증이 필요한 페이지 A -> 인증 필요없는 페이지 B -> 직접 로그인 페이지 -> 로그인 완료 -> 인증 필요없는 A로 이동
         * B 페이지가 로그인 페이지 이전 페이지이기 때문에 B로 이동해야하지만 savedRequest가 null 처리되지 않아 A로 이동
         */
        if(savedRequest != null) {
            if (prevPage.equals(noAuthUri) && !savedRequest.toString().contains("auth")) {
                savedRequest = null;
//                request.getSession().removeAttribute("prevPage");
            }
        }
//        if (prevPage != null) {
//            request.getSession().removeAttribute("prevPage");
//        }

        // 기본 URI
        String uri = "/";

        /**
         * savedRequest 존재하는 경우 = 인증 권한이 없는 페이지 접근
         * Security Filter가 인터셉트하여 savedRequest에 세션 저장
         */
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        } else if (prevPage != null && !prevPage.equals("")) {
            // 회원가입 - 로그인으로 넘어온 경우 "/"로 redirect
            if (prevPage.contains("/join")) {
                uri = "/";
            } else {
                uri = prevPage;
            }
        }
        redirectStrategy.sendRedirect(request, response, uri);
    }

    protected abstract void getToken();

    /**
     * Spring Security에서 로그인하는 과정에서 로그인이 실패한 경우에 세션에 관련 에러를 저장한다
     * 로그인이 실패한 상황이 한번이라도 발생했으면 에러가 세션에 저장된다.
     * 이런 상태에서 로그인이 성공한다면 세션에 있는 에러를  clearAuthenticationAttributes 메소드가 지우는 역할을 한다
     * @apiNote
     * 세션을 받아와서 WebAttributes.AUTHENTICATION_EXCEPTION 변수에 정의된 이름으로 된 세션 값을 지우고 있다.
     * 이 변수에 저장된 값은 SPRING_SECURITY_LAST_EXCEPTION 이란 문자열로써
     * 정리하면 Spring Security는 에러 발생시 SPRING_SECURITY_LAST_EXCEPTION이란 key 값으로 저장함을 알 수가 있다.
     */
    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
