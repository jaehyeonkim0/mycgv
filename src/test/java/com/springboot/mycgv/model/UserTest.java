package com.springboot.mycgv.model;

import com.springboot.mycgv.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("JPA 회원가입 테스트")
    void save() {
        Member user = Member.builder()
                .id("test")
                .password(bCryptPasswordEncoder.encode("123"))
                .name("테스트")
                .gender("M")
                .email("test@naver.com")
                .addr("test")
                .tel("SKT")
                .build();

        memberRepository.save(user); // 엔티티를 저장합니다.

        Member savedUser = memberRepository.findById("test").get();

        Assertions.assertThat(savedUser.getId()).isEqualTo("test");
        Assertions.assertThat(savedUser.getName()).isEqualTo("테스트");

//        Assertions.assertEquals(savedUser.getId(), "yoon");
    }

    @Test
    @DisplayName("JPA 게시물 조회 테스트")
    void findAll() {

    }
}