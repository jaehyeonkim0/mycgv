package com.springboot.mycgv.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomUserDetails implements UserDetails {

    private String id;
    private String password;

    private Member member;

    @Builder
    public CustomUserDetails(String id, String password) {
        this.id = id;
        this.password = password;
    }

    /* 유저의 권한 목록 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    /** 계정 만료 여부
     *  true : 만료 안됨
     *  false : 만료
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 계정 잠김 여부
     *  true : 잠기지 않음
     *  false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** 비밀번호 만료 여부
     *  true : 만료 안됨
     *  false : 만료
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 사용자 활성화 여부
     *  true : 만료 안됨
     *  false : 만료
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
