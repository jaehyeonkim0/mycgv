package com.springboot.mycgv.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;    //UserDetailsService 란? Spring Security에서 유저의 정보를 가져오는 인터페이스

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* @formatter:off */
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**", "/upload/**").permitAll()
                .antMatchers("/","/signup","/join", "/login","/board_list/**", "/board_content2/**", "/comment/save").permitAll()
                .anyRequest().authenticated()
                .and()
        .formLogin()                            //폼 기반 로그인을 사용하도록 설정
                .permitAll()
                .loginPage("/login")            // 기본 로그인 페이지, 사용자 정의 로그인 페이지의 URL을 설정합니다. /login 경로는 사용자 정의 로그인 페이지를 생성하는 데 사용
                .usernameParameter("id")
                .defaultSuccessUrl("/")
                .and()
        .logout()
                .permitAll()
                // .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
                // .logoutSuccessUrl("/login?logout") // 로그아웃 성공 URL (기본 값 : "/login?logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                .clearAuthentication(true); // 로그아웃 시 권한 제거

        return http.build();
        /* @formatter:on */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //AuthenticationmanagerBuilder에  패스워드 암호화를 위해
    // Spring Security에서 제공하는 BCryptPasswordEncoder를 추가 후 UserDetailsService를 추가하여
    // 로그인 시 UserDetailsService를 구현한 CustomUserDetailsService에서 사용자 확인 및 권한을 넣어줄 수 있도록 한다.

    //userDetailsService 메서드는 사용자 정보를 데이터베이스나 다른 저장소에서 로드하는 데 사용되는 메서드
    //UserDetailsService 인터페이스를 구현한 빈(Bean)이 주입되어야 합니다

    //이 빈은 사용자 정보를 제공하고 인증에 사용됩니다.
    //예를 들어, 사용자 이름과 비밀번호를 사용하여 사용자를 인증할 때
    //이 메서드에서 주입한 userDetailsService를 사용하여 사용자 정보를 검색합니다.
}

