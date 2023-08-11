package com.springboot.mycgv.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

    int rno;

    String id, pass, name, gender, email1, email2, addr1, addr2, tel,
            phone1, phone2, phone3, intro, grade;
    String[] hobby;

    String email, addr, pnumber, hobbyList, mdate;

    public String getEmail() {
        if(email1 != null) {
            email = email1 + "@" + email2;
        }
        return email;
    }
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
