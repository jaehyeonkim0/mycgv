package com.springboot.mycgv.service;

import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapStruct modelMapStruct;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("convert Test")
    void convert() {

        MemberDto memberDto = MemberDto.builder()
                .id("test3")
                .password("1111")
                .name("테슷흐")
                .email("test3@naver.com")
                .build();

        Member member1 = modelMapStruct.toMemberEntity(memberDto);
        member1.encrytPass(bCryptPasswordEncoder.encode(member1.getPassword()));
        member1.addRole(MemberRole.USER);

        memberRepository.save(member1);
    }

    @Test
    @DisplayName("save Test")
    void save() {

        IntStream.rangeClosed(1,20).forEach(i -> {
            Member member = Member.builder()
                    .id("test" + i)
                    .password(bCryptPasswordEncoder.encode("123"))
                    .name("테스트")
                    .email("test" + i + "@naver.com")
                    .build();

            member.addRole(MemberRole.USER);

            if(i>=19) {
                member.addRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

    @Test
    void testRead() {

        Member member = memberRepository.getWithRoles("test19").orElseThrow(
                () -> new UsernameNotFoundException("not found username"));

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));

    }

    @Test
    @DisplayName("멤버 저장")
    void saveMember() {
        Member member = Member.builder()
                .id("test")
                .password(bCryptPasswordEncoder.encode("123"))
                .name("테스트")
                .email("test@naver.com")
                .build();
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
    }

    @Test
    @DisplayName("멤버 조회")
    void getMember() {
        String id = "test1";
        Member member = em.find(Member.class, id);

        Long bid = member.getLists().get(0).getBid();
    }
}