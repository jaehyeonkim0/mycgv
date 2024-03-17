package com.springboot.mycgv.filter;

import com.springboot.mycgv.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (!uri.startsWith("/api")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if(authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            log.info("token is null or too short or does not START with BEARER ");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Authorization 헤더 인증 완료");

        // get Access Token without Bearer
        String access_token = getAccessTokenExceptHeader(authorizationHeader);

        // extract user id from access_token
        String userId = tokenProvider.getUserId(access_token);

        // if there is user id in access_token and the user is NOT authenticated yet
        // filter must check whether user id exists in DB or not
        // and ALSO token expired or not

        // TODO try to convert UserDetails to Member Entity
        if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
            log.info(userDetails.getUsername());

            if(tokenProvider.isTokenValid(access_token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("토큰으로 로그인 성공");
                log.info("authorities = {}", userDetails.getAuthorities());
                log.info(userDetails);
            }
        }
        filterChain.doFilter(request, response);

//        String token = getAccessToken(authorizationHeader);

//        if (tokenProvider.validToken(token)) {
//            Authentication authentication = tokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

//        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    private String getAccessTokenExceptHeader(String header) {
        return header.substring(TOKEN_PREFIX.length());
    }
}
