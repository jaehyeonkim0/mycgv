package com.springboot.mycgv.config.mapstruct;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.board.Board;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapStruct {

    public Member toMemberEntity(MemberDto memberDto);

    BoardDto toBoardDtoList(Board board);
}
