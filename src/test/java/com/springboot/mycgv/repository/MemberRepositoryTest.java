package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void insert() {
        Member member = Member.builder()
                .id("test")
                .password(bCryptPasswordEncoder.encode("123"))
                .name("테스트")
                .email("test@naver.name")
                .build();

        memberRepository.save(member);
    }

}