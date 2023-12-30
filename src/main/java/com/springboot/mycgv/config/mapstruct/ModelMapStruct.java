package com.springboot.mycgv.config.mapstruct;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapStruct {

    public Member toMemberEntity(MemberDto memberDto);
}
