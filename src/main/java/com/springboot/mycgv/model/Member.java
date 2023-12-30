package com.springboot.mycgv.model;

import com.springboot.mycgv.enums.MemberRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/***
 * 비주인 엔터티는 주인 엔터티의 변경 사항을 읽기만 할 수 있습니다.
 */

@Getter
@Entity
@Table(name = "mycgv_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //해당 클래스의 기본 생성자를 생성해 주는 어노테이션
public class Member extends Time implements UserDetails {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ADDR")
    private String addr;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "PNUMBER")
    private String pnumber;

    @Column(name = "HOBBYLIST")
    private String hobbyList;

    @Column(name = "INTRO")
    private String intro;

    @Column(name = "GRADE")
    private String grade;

    @OneToMany(mappedBy = "member")
    private List<Board> lists = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    private boolean del; //탈퇴 여부

    private boolean social; //소셜 로그인 자동 회원 가입 여부

    @Builder
    public Member (String id, String password, String name, String gender,
                String email, String addr, String tel, String pnumber,
                String hobbyList, String intro, String grade, boolean del, boolean social) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.addr = addr;
        this.tel = tel;
        this.pnumber = pnumber;
        this.hobbyList = hobbyList;
        this.intro = intro;
        this.grade = grade;
        this.del = del;
        this.social = social;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void encrytPass(String password) {
        this.password = password;
    }
}
