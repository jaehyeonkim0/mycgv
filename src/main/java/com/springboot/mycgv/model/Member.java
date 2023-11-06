package com.springboot.mycgv.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/***
 * 비주인 엔터티는 주인 엔터티의 변경 사항을 읽기만 할 수 있습니다.
 */

@Getter
@Entity
@Table(name = "MYCGV_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //해당 클래스의 기본 생성자를 생성해 주는 어노테이션
public class Member extends Time {

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

    @Builder
    public Member (String id, String password, String name, String gender,
                String email, String addr, String tel, String pnumber,
                String hobbyList, String intro, String grade) {
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
    }

}
