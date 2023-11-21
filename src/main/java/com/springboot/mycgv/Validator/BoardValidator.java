package com.springboot.mycgv.Validator;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class BoardValidator extends CustomValidator<BoardDto> {

    private final BoardRepository boardRepository;
    @Override
    protected void doValidate(BoardDto boardDto, Errors errors) {

//        if(boardRepository.existsByEmail(memberDto.getEmail())) {
//            errors.rejectValue("email", "이메일 중복 오류", "이미 등록된 이메일 입니다.");
//        }

    }
}
