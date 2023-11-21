package com.springboot.mycgv.Validator;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class JoinValidator extends CustomValidator<MemberDto> {

    private final MemberRepository memberRepository;
    @Override
    protected void doValidate(MemberDto memberDto, Errors errors) {
        if (memberRepository.existsById(memberDto.getId())) {
            errors.rejectValue("id", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
        if (memberRepository.existsByEmail(memberDto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 주소입니다.");
        }
    }

}
