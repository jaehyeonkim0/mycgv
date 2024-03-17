package com.springboot.mycgv.model;

import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.board.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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

    //mappedBy의 값은 반대쪽에 자신이 매핑되어 있는 필드명
    @OneToMany(mappedBy = "member")
    private List<Board> lists = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet = new HashSet<>();

    private boolean del; //탈퇴 여부

    private boolean social; //소셜 로그인 자동 회원 가입 여부

    //2024-02-09 영화 예매 엔티티 추가 후
    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }


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

    public void addBoard(Board board) {
        this.lists.add(board);
    }


    public void encrytPass(String password) {
        this.password = password;
    }

    /**
     * 제공된 개체가 동일한 사용자 이름 값을 가진 User 인스턴스인 경우 true를 반환합니다.
     * 즉, 개체의 사용자 이름이 동일하고 동일한 기본값을 나타내는 경우 개체는 동일합니다.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member) {
            return this.id.equals(((Member) obj).id);
        }
        return false;
    }

    /**
     * 객체를 식별할 수 있는 유니크한 값을 말한다.
     * 메모리에 생성된 객체의 주소를 정수로 변환한 형태를 얘기하는데,
     * 이 정수는 중복되지 않는 고유의 값이다
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
