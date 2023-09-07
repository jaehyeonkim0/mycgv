package com.springboot.mycgv.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidService {

    @Transactional(readOnly = true)
    public Map<String, String> validateHandler(Errors errors) {
        Map<String, String> validateResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            //errors.getFieldErrors() : Get all errors associated with a field.
            String validKeyName = "valid_" + error.getField(); //Field : 검사에 실패한 필드의 이름
            validateResult.put(validKeyName, error.getDefaultMessage());
            //defaultMessage : 유효성 검사 오류에 대한 기본 오류 메시지를 나타냅니다.
            // 이 메시지는 보통 프로퍼티 파일에서 가져와서 사용자에게 표시
        }


        return validateResult;
    }
}
