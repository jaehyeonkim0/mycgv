package com.springboot.mycgv.config.mapstruct;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.model.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T15:23:31+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.19 (Oracle Corporation)"
)
@Component
public class ModelMapStructImpl implements ModelMapStruct {

    @Override
    public Member toMemberEntity(MemberDto memberDto) {
        if ( memberDto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.id( memberDto.getId() );
        member.password( memberDto.getPassword() );
        member.name( memberDto.getName() );
        member.gender( memberDto.getGender() );
        member.email( memberDto.getEmail() );
        member.addr( memberDto.getAddr() );
        member.tel( memberDto.getTel() );
        member.pnumber( memberDto.getPnumber() );
        member.hobbyList( memberDto.getHobbyList() );
        member.intro( memberDto.getIntro() );
        member.grade( memberDto.getGrade() );
        member.social( memberDto.isSocial() );

        return member.build();
    }
}
