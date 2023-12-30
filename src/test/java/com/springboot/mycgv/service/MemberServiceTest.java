package com.springboot.mycgv.service;

import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ModelMapStruct modelMapStruct;

    @Test
    @DisplayName("convert Test")
    void convert() {

        MemberDto memberDto = MemberDto.builder()
                .id("test2")
                .password("1111")
                .name("테슷흐")
                .email("test2@naver.com")
                .build();

        Member member = modelMapper.map(memberDto, Member.class);

        Member member1 = modelMapStruct.toMemberEntity(memberDto);

        log.info(memberDto);
        log.info(memberDto.getClass());
        log.info("----------------------------------------");
        log.info(member);
        log.info(member.getClass());
        log.info(member1);
        log.info(member1.getClass());

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
    void testRead2() {

        Optional<Member> member = memberRepository.findByEmail("test19@naver.com");

        Member member1 = member.orElseThrow();

        log.info(member1);

        member1.getRoleSet().forEach(memberRole -> {
                log.info("---------------------");
                log.info(memberRole.name());
            }
        );
    }
}