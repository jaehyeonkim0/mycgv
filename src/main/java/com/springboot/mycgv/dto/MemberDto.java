package com.springboot.mycgv.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

    int rno;

    @NotBlank(message = "아이디는 필수 입력 항목입니다")
    String id;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다")
    String pass;

    @NotBlank(message = "이름은 필수 입력 항목입니다")
    String name;

    @NotBlank(message = "이메일은 필수 입력 항목입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    String email;

    String gender, addr1, addr2, tel,
            phone1, phone2, phone3, intro, grade;
    String[] hobby;

    String addr, pnumber, hobbyList, mdate;

    public String getAddr() {
        if (addr1 != null) {
            addr = addr1 + addr2;
        }
        return addr;
    }
    public String getPnumber() {
        if(phone1 != null) {
            pnumber = phone1 +"-"+ phone2 +"-"+ phone3;
        }
        return pnumber;
    }
    public String getHobbyList() {
        if(hobby != null) {
            hobbyList = String.join(",", hobby);
        }
        return hobbyList;
    }
}
