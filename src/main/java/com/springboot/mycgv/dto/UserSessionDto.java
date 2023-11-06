package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private String id;
    private String password;
    private String name;
    private String email;
    private String pnumber;

    public UserSessionDto(Member member) {
        this.id = member.getId();
        this.password = member.getPassword();
        this.name = member.getName();
        this.email = member.getEmail();
        this.pnumber = member.getPnumber();
    }


}
