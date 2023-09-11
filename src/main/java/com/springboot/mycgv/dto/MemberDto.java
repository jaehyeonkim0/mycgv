package com.springboot.mycgv.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberDto{

    private int rno;

    @NotBlank(message = "아이디는 필수 입력 항목입니다")
    @Size(max = 10, message = "아이디는 10글자 이하로 작성해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영문 또는 영문+숫자로만 입력하세요")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다")
    @Pattern(regexp = "^[가-힣]*$", message = "한글로만 입력하세요")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 항목입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    private String gender, addr1, addr2, tel,
            phone1, phone2, phone3, intro, grade;
    private String[] hobby;

    private String addr, pnumber, hobbyList, mdate;

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
