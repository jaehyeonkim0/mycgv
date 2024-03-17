package com.springboot.mycgv.repository;

import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void insert() {
        Member member = Member.builder()
                .id("test1")
                .password(bCryptPasswordEncoder.encode("123"))
                .name("테스트1")
                .email("test1@naver.name")
                .build();
        member.addRole(MemberRole.USER);
        memberRepository.save(member);
    }

    @Test
    void getMember() {
        String id = "test";
        Optional<Member> member = memberRepository.findById(id);

    }


}