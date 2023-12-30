package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @NotBlank(message = "아이디는 필수 입력 항목입니다")
    @Size(max = 30, message = "아이디는 10글자 이하로 작성해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9.@]*$", message = "영문 또는 영문+숫자로만 입력하세요")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력 항목입니다")
    @Pattern(regexp = "^[가-힣]*$", message = "한글로만 입력하세요")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 항목입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;

    private String gender;
    private String addr;
    private String pnumber;
    private String hobbyList;

    private String memberCreatedTime;
    private String memberUpdatedTime;

    private String grade;

    private int rno;
    private String addr1, addr2;
    private String tel, phone1, phone2, phone3;
    private String intro;
    private String[] hobby;
    private boolean social;


    public String getAddr() {
        if (addr1 != null) {
            addr = addr1 + addr2;
        }
        return addr;
    }

    public String getPnumber() {
        if(phone1 != null) {
            pnumber = phone1 + phone2 + phone3;
        }
        return pnumber;
    }
    public String getHobbyList() {
        if(hobby != null) {
            hobbyList = String.join(",", hobby);
        }
        return hobbyList;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .gender(gender)
                .email(email)
                .addr(getAddr())
                .tel(tel)
                .pnumber(getPnumber())
                .hobbyList(getHobbyList())
                .intro(intro)
                .grade(grade)
                .social(social)
                .build();
    }

    @Builder
    public MemberDto(String id, String password, String name, String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
