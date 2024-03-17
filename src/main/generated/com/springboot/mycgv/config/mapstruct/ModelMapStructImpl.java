package com.springboot.mycgv.config.mapstruct;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.board.Board;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T20:06:56+0900",
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

    @Override
    public BoardDto toBoardDtoList(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDto boardDto = new BoardDto();

        boardDto.setBid( board.getBid() );
        boardDto.setMember( board.getMember() );
        boardDto.setBtitle( board.getBtitle() );
        boardDto.setBcontent( board.getBcontent() );
        boardDto.setBhits( board.getBhits() );
        boardDto.setFileAttached( board.getFileAttached() );

        return boardDto;
    }
}
