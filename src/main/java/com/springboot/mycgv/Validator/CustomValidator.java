package com.springboot.mycgv.Validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public abstract class CustomValidator<T> implements Validator {

    //Validator 인터페이스의 메소드로 CustomValidator가 지원하는 클래스를 지정한다.
    //현재 구현에서는 모든 클래스를 지원하도록 true를 리턴하고 있다.
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    //Validator 인터페이스의 validate 메소드를 구현한다. 유효성 검사를 수행하는 메인 메소드다.
    //target은 유효성 검사를 수행할 객체(보통 DTO나 엔터티), errors는 유효성 검사 중 발생한 오류를 저장하는 객체다.
    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (RuntimeException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }

    //추상 메서드로, 실제 유효성 검사 로직이 구현되어야 할 메소드다.
    //이 메서드는 자식 클래스에서 반드시 구현되어야한다.
    //dto는 유효성 검사를 수행할 DTO나 엔터티 객체를 나타내고, errors는 검사 중 발생한 오류를 저장하는 객체다.
    protected abstract void doValidate(final T dto, final Errors errors);

}
