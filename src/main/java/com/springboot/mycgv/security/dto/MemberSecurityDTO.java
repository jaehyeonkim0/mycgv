package com.springboot.mycgv.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
@Log4j2
public class MemberSecurityDTO extends User implements OAuth2User {

    private String mid;
    private String mpw;
    private String email;
    private String name;
    private String pnumber;
    private boolean del;
    private boolean social;

    private Map<String, Object> props; //소셜 로그인 정보

    public MemberSecurityDTO(String username,
                             String password,
                             String email,
                             String name,
                             String pnumber,
                             boolean del,
                             boolean social,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.mid = username;
        this.mpw = password;
        this.email = email;
        this.name = name;
        this.pnumber = pnumber;
        this.del = del;
        this.social = social;
    }

    public Map<String, Object> getAttributes() {
        return this.getProps();
    }


}
